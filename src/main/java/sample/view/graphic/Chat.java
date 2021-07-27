package sample.view.graphic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.controller.UserLogined;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Chat extends Application {
    private static Stage stage;

    private Timeline timer;

    @FXML
    private TextField massage;
    @FXML
    private AnchorPane chatBox;
    ArrayList<String> allMessage = new ArrayList<>();


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
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
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            dataOutputStream.writeUTF("startChat," + UserLogined.user.token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            allMessage = (ArrayList<String>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        creatChatBox();
        startClient();

    }

    public void startClient() {
        Read();
        Send();
    }


    public void Send() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }


    public void Read() {

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }


    public void creatChatBox() {
        int i = 0;
        for (String msg : allMessage) {
            i++;
        }
        chatBox.setPrefHeight(i * 180);
        ArrayList<Text> allTexts = new ArrayList<>();
        i = 30;
        String usernamrTem = "";
        for (String message : allMessage) {
            String[] newMeg = message.split(",");

            if (!usernamrTem.equals(newMeg[0])) {
                Text text = new Text();
                text.setText(newMeg[0] + ": ");
                text.setX(0);
                text.setY(i - 20);

                allTexts.add(text);
                usernamrTem = newMeg[0];
            }
            Text textMsg = new Text();
            textMsg.setText(newMeg[1]);
            textMsg.setX(50);
            textMsg.setY(i);
            i += 50;
            allTexts.add(textMsg);
        }
        chatBox.getChildren().
                addAll(allTexts);
    }


    public void SendClicked() {
        if (!massage.getText().equals("")) {
            allMessage.add(massage.getText());
        }
        massage.clear();
        chatBox.getChildren().clear();
        creatChatBox();
    }

    public void Back() throws Exception {
        new MainMenu().start(stage);
    }


}
