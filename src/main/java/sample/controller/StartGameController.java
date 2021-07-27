package sample.controller;


import sample.Game;
import sample.model.User;
import sample.view.graphic.MainMenu;

import javax.print.DocFlavor;
import java.io.*;
import java.net.Socket;

public class StartGameController {

    public static String Game(User user, int round)  {

        if (user.hasActiveDeck) {
            if (user.getActiveDeck().isValid().equals("valid")) {
                if (round == 1) {
                    Socket socket = null;
                    try {
                        socket = new Socket("localhost", 7001);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    DataOutputStream dataOutputStream = null;
                    try {
                        dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        dataOutputStream.writeUTF("save");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ObjectOutputStream objectOutputStream=null;
                    try {
                        objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        objectOutputStream.writeObject(UserLogined.user);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        objectOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DataInputStream dataInputStream=null;
                    try {
                        dataInputStream= new DataInputStream(socket.getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        dataInputStream.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                    try {
                        dataOutputStream.writeUTF("startGame," + UserLogined.user.token + "," + round);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String harif="";
                    try {
                       harif= dataInputStream.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    try {
                        dataOutputStream.writeUTF("sendUser,"+harif);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    User opp=null;
                    try {
                        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
                        try {
                            opp= (User) objectInputStream.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        objectInputStream.close();
                        UserLogined.opponent=opp;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                        return "done1";

                } else if (round == 3) {
                    return "khuck";
                } else {
                    return "number of rounds is not supported";
                }
            } else {
                return (user.getUsername() + "’s deck is invalid");
            }
        } else {
            return (user.getUsername() + " has no active deck");
        }

    }
}
