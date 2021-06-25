package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.SignupController;
import sample.controller.UserLogined;
import sample.model.User;


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
    private String imageAddress = "Assets/Characters/Chara001.dds1.png";
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
        String nextStep = SignupController.creatUser(usernameVorodi.getText(), nickname.getText(), passwordVorodi.getText());
        if (nextStep.equals("user created successfully!")) {
            User.getUserByUsername(usernameVorodi.getText()).setAvatar(new Image(String.valueOf(getClass().getResource(imageAddress))));
            new Start().start(stage);
        } else {
            error.setText(nextStep);
        }
    }

    public void NextAvatar() {
        if (i != 38) {
            i++;
            avatar.setImage(new Image(String.valueOf((getClass().getResource("Assets/Characters/Chara001.dds" + i + ".png")))));
            imageAddress="Assets/Characters/Chara001.dds" + i + ".png";
        }
    }

    public void preAvatar() {
        if (i != 1) {
            i--;
            avatar.setImage(new Image(String.valueOf((getClass().getResource("Assets/Characters/Chara001.dds" + i + ".png")))));
            imageAddress="Assets/Characters/Chara001.dds" + i + ".png";
        }
    }
}
