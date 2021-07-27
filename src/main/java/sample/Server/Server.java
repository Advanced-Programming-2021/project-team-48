package sample.Server;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sample.model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    public static HashMap<String, User> logginedUsers = new HashMap<>();
    public static HashMap<User, String> userAndPassword = new HashMap<>();
    public static User oneRoundedReq1 = null;
    public static User oneRoundedReq2 = null;
    public static User threeRoundedReq = null;
    private ArrayList<UserThread> userThreads = new ArrayList<>();
    ArrayList<String> allChats = new ArrayList<>();
    private Timeline timer;
    private static ArrayList<User> listOfUsers = new ArrayList<>();

    public void runApp() {
        try {
            ServerSocket serverSocket = new ServerSocket(7001);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    startNewThread(serverSocket, socket);
                } catch (SocketException e) {
                    System.out.println("");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startNewThread(ServerSocket serverSocket, Socket socket) {
        new Thread(() -> {

            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                getInputAndProcess(dataInputStream, dataOutputStream, socket);
                dataInputStream.close();
                socket.close();
                serverSocket.close();
            } catch (SocketException e) {
                System.out.println("a client disconnected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void getInputAndProcess(DataInputStream dataInputStream, DataOutputStream dataOutputStream, Socket socket) throws IOException {
        while (true) {

            String input = dataInputStream.readUTF();
            System.out.println(input);
            String result = process(input, socket);
            System.out.println(result);
            if (result.equals("")) break;

            dataOutputStream.writeUTF(result);
            dataOutputStream.flush();
        }
    }

    private String process(String input, Socket socket) {

        boolean checker = false;
        Pattern pattern = Pattern.compile("^login,([\\S]+),([\\S]+)$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String username = matcher.group(1);
            String password = matcher.group(2);

            return login(username, password);
        }

        pattern = Pattern.compile("^creatUser,([\\S]+),([\\S]+),([\\S]+),([\\S]+)$");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String username = matcher.group(1);
            String nickname = matcher.group(2);
            String password = matcher.group(3);
            String imageAddress = matcher.group(4);
            String o = createUser(username, nickname, password, imageAddress);
            return o;
        }


        if (input.equals("sendingNewUser")) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                User temp = (User) objectInputStream.readObject();
                listOfUsers.add(temp);
                userAndPassword.put(temp, temp.password);
                return temp.getUsername() + " added";
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        pattern = Pattern.compile("^changePassword,([\\S]+),([\\S]+),([\\S]+)$");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String token = matcher.group(1);
            String oldPass = matcher.group(2);
            String newPass = matcher.group(3);
            return changePassword(token, oldPass, newPass);
        }

        pattern = Pattern.compile("^logout,([\\S]+)$");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            logginedUsers.remove(matcher.group(1));
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                User temp = (User) objectInputStream.readObject();
                System.out.println(temp.username);
                Iterator<User> deleter = listOfUsers.iterator();
                while (deleter.hasNext()) {
                    User tem = deleter.next();
                    if (tem.username.equals(temp.username)) {
                        userAndPassword.remove(tem);
                        deleter.remove();
                        break;
                    }
                }


                listOfUsers.add(temp);
                userAndPassword.put(temp, temp.password);

                return temp.getUsername() + " added";
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        pattern = Pattern.compile("^sendUser,([\\S]+)$");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String username = matcher.group(1);
            for (User user : getListOfUsersServer()) {
                if (user.getUsername().equals(username)) {
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(user);
                        objectOutputStream.flush();
                        return user.getNickname() + " sent";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (input.equals("userList")) {

            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(listOfUsers);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        pattern = Pattern.compile("^getNickname,([\\S]+)$");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String username = matcher.group(1);
            for (User user : getListOfUsersServer()) {
                if (user.username.equals(username)) return user.nickname;
            }
        }

        pattern = Pattern.compile("^getImage,([\\S]+)$");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String username = matcher.group(1);
            for (User user : getListOfUsersServer()) {
                if (user.username.equals(username)) return user.imageAddress;
            }
        }

        pattern = Pattern.compile("^scores$");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String usernameAndScore = "";
            for (User user : getListOfUsersServer()) {
                usernameAndScore += user.username + "," + user.score + "/";
            }
            return usernameAndScore;
        }

        pattern = Pattern.compile("^startChat,([\\S]+)$");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            User user=logginedUsers.get(matcher.group(1));
            ObjectOutputStream objectOutputStream=null;
            try {
                objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                objectOutputStream.writeObject(allChats);
            } catch (IOException e) {
                e.printStackTrace();
            }

            checker = true;
            ExecutorService pool = Executors.newCachedThreadPool();
            UserThread temp = new UserThread(socket, this,user);
            userThreads.add(temp);
            pool.execute(temp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        pattern = Pattern.compile("startGame,([\\S]+),([1-3])");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String token = matcher.group(1);
            int round = Integer.parseInt(matcher.group(2));
            User user = logginedUsers.get(token);
            System.out.println(user.username);
            boolean isDone = false;
            if (round == 1) {
                if (oneRoundedReq1 == null) {
                    oneRoundedReq1 = user;
                    while (true) {
                        if (oneRoundedReq2 == null) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {


                            ObjectOutputStream objectOutputStream = null;
                            try {
                                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                objectOutputStream.writeObject(oneRoundedReq2);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                objectOutputStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (isDone) {
                                String done = "done " + oneRoundedReq2.username + ">>" + oneRoundedReq1.username;
                                oneRoundedReq2 = null;
                                oneRoundedReq1 = null;
                                return done;
                            } else
                                isDone = true;

                            break;
                        }
                    }


                } else {
                    oneRoundedReq2 = user;
                    while (true) {
                        ObjectOutputStream objectOutputStream = null;
                        try {
                            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            objectOutputStream.writeObject(oneRoundedReq1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            objectOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (isDone) {
                            String done = "done " + oneRoundedReq1.username + ">>" + oneRoundedReq1.username;
                            oneRoundedReq2 = null;
                            oneRoundedReq1 = null;
                            return done;
                        } else
                            isDone = true;
                    }
                }

            }
        }


        if (input.equals("getNumOfOnlineUsers")) {
            int i = 0;
            for (User user : logginedUsers.values()) {
                i++;
            }
            return String.valueOf(i);
        }


        return "error";
    }

    private static String changePassword(String token, String oldPass, String newPass) {
        if (userAndPassword.get(logginedUsers.get(token)).equals(oldPass)) {
            if (!oldPass.equals(newPass)) {
                userAndPassword.replace(logginedUsers.get(token), newPass);
                return "password changed successfully!";
            } else {
                return "please enter a new password";
            }
        } else {
            return "current password is invalid";
        }
    }

    private static String createUser(String username, String nickname, String password, String imageAddress) {
        for (User user : getListOfUsersServer()) {
            if (user.username.equals(username)) {
                return "user with username " + username + " already exists";
            }
        }
        for (User user : getListOfUsersServer()) {
            if (user.nickname.equals(nickname)) {
                return "user with nickname " + nickname + " already exists";
            }
        }
        return "user created successfully!";
    }


    private static String login(String username, String password) {
        for (User user : getListOfUsersServer()) {
            if (user.username.equals(username)) {
                if (!passwordChecker(user, password)) {
                    return "Username and password didn't match!";
                } else {
                    String token = UUID.randomUUID().toString();
                    logginedUsers.put(token, user);
                    return "OK," + token;
                }
            }
        }
        return "Username and password didn't match!";
    }


    public static boolean passwordChecker(User user, String password) {
        return userAndPassword.get(user).equals(password);
    }


    public static ArrayList<User> getListOfUsersServer() {
        return listOfUsers;
    }


    public void SendAll(String message, UserThread userThread) {
        allChats.add(userThread.user.username+","+message);
        for (int i = 0; i < userThreads.size(); i++) {
            userThreads.get(i).Receive(message);
        }
    }

}

