package sample.view.graphic;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.UserLogined;
import sample.model.User;

import java.io.*;
import java.net.Socket;

public class ShowUser extends Application {
    private static Stage stage;
    public static String user="";

    @FXML
    ImageView avatar;
    @FXML
    Text username;
    @FXML
    Text nickname;
    @FXML
    Text socre;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("ShowUser.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
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
        try {
            dataOutputStream.writeUTF("sendUser,"+user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
            User user= null;
            try {
                user = (User) objectInputStream.readObject();
                username.setText(user.getUsername());
                nickname.setText(user.getNickname());
                avatar.setImage(new Image(new File(user.imageAddress).toURI().toString()));
                socre.setText(user.getScore()+"");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void back() throws Exception {
        new Chat().start(stage);
    }
}
