package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.LoginController;
import sample.controller.UserLogined;
import sample.model.Deck;
import sample.model.User;

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
        if (nextStep.equals("OK")) {
            UserLogined.user = User.getUserByUsername(usernameVorodi.getText());
            new MainMenu().start(stage);
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
