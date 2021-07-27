package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.LoginController;
import sample.controller.UserLogined;
import sample.model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Start extends Application {
    private static Stage stage;

    @FXML
    private TextField usernameVorodi;
    @FXML
    private TextField passwordVorodi;
    @FXML
    private Text error;
    public static String errorOut = "";
    // private Media media = new Media(String.valueOf((getClass().getResource("menu.mp3"))));
    //public MediaPlayer mediaPlayer = new MediaPlayer(media);

    @Override
    public void start(Stage stage) throws Exception {
        //mediaPlayer.setAutoPlay(true);
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("Start.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        error.setText(errorOut);
        errorOut = "";
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void Login() throws Exception {
        String nextStep = LoginController.login(usernameVorodi.getText(), passwordVorodi.getText());

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
        Pattern pattern = Pattern.compile("OK,(.*)");
        Matcher matcher1 = pattern.matcher(nextStep);
        if (matcher1.find()) {
            String token=matcher1.group(1);
            try {
                dataOutputStream.writeUTF("sendUser,"+usernameVorodi.getText());
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
                User user= (User) objectInputStream.readObject();
                UserLogined.user=user;
                user.token=token;
                new MainMenu().start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*boolean isFind = false;
            for (User user : UserKeeper.getListOfUsers()) {
                if (user.getUsername().equals(usernameVorodi.getText())) {
                    isFind = true;
                    user.token = matcher1.group(1);
                    UserLogined.user = user;
                    break;
                }
            }
            /*if (!isFind) {
                String nickName = "";
                try {
                    dataOutputStream.writeUTF("getNickname," + usernameVorodi.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    nickName = SetupConnection.dataInputStream.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                User vorodiJadid = new User(usernameVorodi.getText(), nickName,"1","sda");
                String imageAddress = "";
                try {
                    dataOutputStream.writeUTF("getImage," + usernameVorodi.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    imageAddress = SetupConnection.dataInputStream.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                vorodiJadid.setAvatar(new Image(String.valueOf(getClass().getResource(imageAddress))));


                vorodiJadid.token = matcher1.group(1);
                UserLogined.user = vorodiJadid;

             */
            } else {
                error.setText(nextStep);
            }
        }


    public void Signup() throws Exception {
        new Signup().start(stage);
    }


    public void Exit() {
        System.exit(0);
    }
}
