package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.StartGameController;
import sample.controller.UserLogined;

public class DuelMenu extends Application {
    private static Stage stage;

    @FXML
    private TextField opponent;
    @FXML
    private Text error;
    @FXML
    private CheckBox one;
    @FXML
    private CheckBox three;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("DuelMenu.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        one.setSelected(true);
    }

    public void setThree() {
        one.setSelected(false);
        three.setSelected(true);
    }

    public void setOne() {
        one.setSelected(true);
        three.setSelected(false);
    }

    public void StartClicked() throws Exception {
        int rounds = 0;
        if (one.isSelected()) rounds = 1;
        if (three.isSelected()) rounds = 3;
        String nextStep = StartGameController.Game(opponent.getText(), UserLogined.user, rounds);
        error.setText(nextStep);
        if (nextStep.equals("done1")){
            new GameGraphic().start(stage);
        }
    }

    public void Back() throws Exception {
        new MainMenu().start(stage);
    }
}
