package sample.view.graphic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.controller.SortUserList;
import sample.model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;



public class ScoreboardMenu extends Application {
    private static Stage stage;
    private Timeline timer;
    @FXML
    private AnchorPane innerAnchorPane;
    @FXML
    private Text users;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("ScoreboardMenu.fxml"));

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
        DataInputStream dataInputStream = null;
        try {
            dataInputStream=new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataOutputStream finalDataOutputStream = dataOutputStream;
        DataInputStream finalDataInputStream = dataInputStream;
        KeyFrame frame = new KeyFrame(Duration.seconds(2), event -> {
            innerAnchorPane.getChildren().clear();
            innerAnchorPane.getChildren().addAll(creatScorePane());
            String numOfUsers = "";

            try {
                finalDataOutputStream.writeUTF("getNumOfOnlineUsers");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                finalDataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                numOfUsers = finalDataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            users.setText(numOfUsers);
        });
        this.timer = new Timeline(frame);
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public ArrayList<ScoreboardAnchorPane> creatScorePane() {

/*

        String usernameAndScores = "";
        try {
            dataOutputStream.writeUTF("scores");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            usernameAndScores = SetupConnection.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] splitedUsernameAndScore = usernameAndScores.split("/");

        int b = 0;
        for (String a : splitedUsernameAndScore) {
            b++;
            if (b == splitedUsernameAndScore.length+1) break;
            String[] usernameAndScoreOneByOne = a.split(",");
            boolean isFind = false;
            for (User user : User.getListOfUsers()) {
                if (user.getUsername().equals(usernameAndScoreOneByOne[0])) {
                    isFind = true;
                    user.setScore(Integer.parseInt(usernameAndScoreOneByOne[1]));
                }
            }
        }


 */
        int i = 1;
        SortUserList.Sort();
        for (int j = 0; j < 20; j++) {
            if (j == User.getListOfUsers().size()) break;
            new ScoreboardAnchorPane(i, User.getListOfUsers().get(j));
            i++;
        }
        return ScoreboardAnchorPane.getAllScorePane();
    }


    public void BackClicked() throws Exception {
        timer.stop();
        new MainMenu().start(stage);
    }
}
