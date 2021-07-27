package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.SignupController;
import sample.model.User;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class Signup extends Application {
    private static Stage stage;

    @FXML
    private TextField usernameVorodi;
    @FXML
    private PasswordField passwordVorodi;
    @FXML
    private TextField nickname;
    @FXML
    private Text error;
    @FXML
    private Text done;
    @FXML
    private ImageView avatar;
    private String imageAddress = "C:\\Users\\amirhossein\\Desktop\\project-team-48-komak\\src\\main\\resources\\sample\\view\\graphic\\Assets\\Characters\\Chara001.dds1.png";
    private int i = 1;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void Back() throws Exception {
        new Start().start(stage);
    }

    public void Signup() throws Exception {
        String nextStep = SignupController.creatUser(usernameVorodi.getText(), nickname.getText(), passwordVorodi.getText(), imageAddress);

        if (nextStep.equals("user created successfully!")) {

            new User(usernameVorodi.getText(), nickname.getText(), passwordVorodi.getText(), imageAddress);

            Start.errorOut = "user created successfully!";
            new Start().start(stage);
        } else {
            error.setText(nextStep);
        }
    }

    public void NextAvatar() {
        if (i != 38) {
            i++;

            avatar.setImage(new Image(new File("C:\\Users\\amirhossein\\Desktop\\project-team-48-komak\\src\\main\\resources\\sample\\view\\graphic\\Assets\\Characters\\Chara001.dds" + i + ".png").toURI().toString()));

            imageAddress = "C:\\Users\\amirhossein\\Desktop\\project-team-48-komak\\src\\main\\resources\\sample\\view\\graphic\\Assets\\Characters\\Chara001.dds" + i + ".png";
        }
    }

    public void preAvatar() {
        if (i != 1) {
            i--;
            avatar.setImage(new Image(new File("C:\\Users\\amirhossein\\Desktop\\project-team-48-komak\\src\\main\\resources\\sample\\view\\graphic\\Assets\\Characters\\Chara001.dds" + i + ".png").toURI().toString()));


            imageAddress = "C:\\Users\\amirhossein\\Desktop\\project-team-48-komak\\src\\main\\resources\\sample\\view\\graphic\\Assets\\Characters\\Chara001.dds" + i + ".png";
        }
    }
}
