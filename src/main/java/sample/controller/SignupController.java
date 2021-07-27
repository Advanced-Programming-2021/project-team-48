package sample.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class SignupController {
    public static String creatUser(String username, String nickname, String password,String imageAddress) {
        Socket socket = null;
        try {
            socket=new Socket("localhost", 7001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataOutputStream dataOutputStream= null;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataInputStream dataInputStream = null;
        try {
            dataInputStream=new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.writeUTF("creatUser," + username + "," + nickname + "," + password+","+imageAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}

