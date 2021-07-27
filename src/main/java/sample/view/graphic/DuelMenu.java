package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.*;
import sample.controller.Game.GameController;
import sample.model.Card.CartReader;
import sample.model.Card.MonsterForUser;
import sample.model.Card.SpellCardForUser;
import sample.model.Deck;
import sample.model.User;

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
    @FXML
    private Button req;

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
        error.setText("waiting for opponent...");
        req.setText("cancel");
        String nextStep = StartGameController.Game( UserLogined.user, rounds);
        if (nextStep.equals("done1")) {
            GameGraphic1.deckTempUser.copyDeck(UserLogined.user.getActiveDeck());
            GameGraphic1.deckTempOpponent.copyDeck(UserLogined.opponent.getActiveDeck());
            System.out.println(UserLogined.opponent.username);
            System.out.println(UserLogined.user.username);
            GameGraphic1 gameGraphic1 = new GameGraphic1();
            gameGraphic1.start(stage);
        }else {
            error.setText(nextStep);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void Back() throws Exception {
        new MainMenu().start(stage);
    }
}
