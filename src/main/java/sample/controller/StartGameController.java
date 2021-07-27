package sample.controller;


import sample.Game;
import sample.model.User;
import sample.view.graphic.MainMenu;

import javax.print.DocFlavor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class StartGameController {

    public static String Game(User user, int round) throws Exception {

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
                        dataOutputStream.writeUTF("startGame," + UserLogined.user.token + "," + round);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (UserLogined.opponent==null){
                        System.out.println("kkkkkkkkkkkkkkkkkkk");
                    }else System.out.println("jjjjjjjjjjjjjjjjjjjjjjj");

                    User user2 = null;
                    System.out.println("here");
                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        user2 = (User) objectInputStream.readObject();
                        UserLogined.opponent = user2;
                        System.out.println("what");
                        System.out.println(UserLogined.opponent.username);
                        System.out.println("------------------");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("hi");

                    if (user2 != null)
                        //new Game(user, user2, 1, 1);
                        return "done1";

                } else if (round == 3) {
                    new Game(UserLogined.user, UserLogined.opponent, 3, 1);
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
        return "error";

    }
}
