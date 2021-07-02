package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.UserLogined;
import sample.model.Card.Card;
import sample.model.Card.MonsterForUser;
import sample.model.User;

public class Grave extends Application {
    private static Stage stage;
    @FXML
    AnchorPane show;


    @FXML
    Text nickname;

    public static User user;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Grave.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        nickname.setText(user.getNickname());
        creatCards();
    }

    public void creatCards() {
    }

    public void back() throws Exception {
        new GameGraphic1().start(stage);
    }
}
