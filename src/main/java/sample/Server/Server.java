package sample.Server;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sample.controller.BuyCard;
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
    public static String oneRoundedReq1 = "";
    public static String oneRoundedReq2 = null;
    public static User threeRoundedReq = null;


    String allChats = "";
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
            System.out.println("--------------------------------");
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

        pattern = Pattern.compile("^buy,([^,]+),([^,]+)$");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String cardName = matcher.group(1);
            System.out.println(",,,,,,,,,,,,,,,,,,,,,,,");
            System.out.println(cardName);
            System.out.println(",,,,,,,,,,,,,,,,,,,,,,,");
            String username=matcher.group(2);
            User user=User.getUserByUsername(username);
            return BuyCard.BuyCard(cardName,user);
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

        pattern = Pattern.compile("save");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
                ObjectInputStream objectInputStream = null;
                try {
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                User temp = null;
                try {
                    temp = (User) objectInputStream.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
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

        if (input.equals("getChats")) {
            return allChats;
        }

        pattern = Pattern.compile("^newMsg,(.+)$");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String massage = matcher.group(1);
            if (!massage.equals("")) {
                if (allChats.equals(""))
                    allChats += massage;
                else
                    allChats += "/" + (massage);
            }


        }
        pattern = Pattern.compile("startGame,([\\S]+),([1-3])");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String token = matcher.group(1);
            User user = logginedUsers.get(token);
            int round = Integer.parseInt(matcher.group(2));

            boolean isDone = false;
            if (round == 1) {
                if (oneRoundedReq1 == null) {
                    oneRoundedReq1 = user.username;
                    while (true) {
                        if (oneRoundedReq2 == null) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            String temp = oneRoundedReq2;
                            if (isDone) {
                                oneRoundedReq2 = null;
                                oneRoundedReq1 = null;
                            } else
                                isDone = true;
                            return temp;

                        }
                    }

                } else {
                    oneRoundedReq2 = user.username;
                    String temp=oneRoundedReq1;
                    if (isDone) {
                        oneRoundedReq2 = null;
                        oneRoundedReq1 = null;
                    } else
                        isDone = true;
                    return temp;
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



}

