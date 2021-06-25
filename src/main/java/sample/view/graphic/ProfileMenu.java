package sample.view.graphic;

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
import sample.controller.LoginController;
import sample.controller.NicknameChangeController;
import sample.controller.PasswordChangeController;
import sample.controller.UserLogined;

public class ProfileMenu extends Application {
    private static Stage stage;
    private String imageAddress;
    int i=1;
    @FXML
    private ImageView avatar;
    @FXML
    private Text username;
    @FXML
    private Text nickname;
    @FXML
    private TextField oldPass;
    @FXML
    private TextField newPass;
    @FXML
    private TextField newNickname;
    @FXML
    private Text passwordError;
    @FXML
    private Text nicknameError;
    @FXML
    private Text avatarChange;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("ProfileMenu.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        avatar.setImage(UserLogined.user.getAvatar());
        username.setText(UserLogined.user.getUsername());
        nickname.setText(UserLogined.user.getNickname());
    }
        public void BackToMainMenu() throws Exception {
        new MainMenu().start(stage);
    }

    public void ChangePasswordClicked(){
        String nextStep=PasswordChangeController.changePassword(oldPass.getText(),newPass.getText(),UserLogined.user);
        passwordError.setText(nextStep);
        if (nextStep.equals("password changed successfully!")){
        oldPass.clear();
        newPass.clear();}
    }

    public void ChangeNickname(){
        String nextStep =NicknameChangeController.changeNickname(newNickname.getText(),UserLogined.user);
        nicknameError.setText(nextStep);
        if (nextStep.equals("nickname changed successfully!")){
            newNickname.clear();
        }
    }

    public void ChangeAvatarClicked(){
        UserLogined.user.setAvatar(new Image(String.valueOf((getClass().getResource(imageAddress)))));
        avatarChange.setText("avatar changed successfully!");
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
