package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.UserLogined;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class MainMenu extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void Shop() throws Exception {
        new ShopMenu().start(stage);
    }

    public void Scoreboard() throws Exception {
        new ScoreboardMenu().start(stage);
    }

    public void ProfileClicked() throws Exception {
        new ProfileMenu().start(stage);
    }

    public void LogoutClicked() throws Exception {
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
            dataOutputStream.writeUTF("logout,"+ UserLogined.user.token);
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

        new Start().start(stage);
    }

    public void DeckClicked() throws Exception {
        new DeckMenu().start(stage);
    }

    public void dueClicked() throws Exception {
        new DuelMenu().start(stage);
    }

    public void creatClicked() throws Exception {
        new CreatCard().start(stage);
    }


    public void Chat() throws Exception {
        new Chat().start(stage);
    }

    public void ExitClicked() {

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
            dataOutputStream.writeUTF("logout,"+ UserLogined.user.token);
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
        System.exit(0);
    }
}
