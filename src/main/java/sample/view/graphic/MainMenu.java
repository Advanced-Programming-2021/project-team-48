package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Game;

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
        new Start().start(stage);
    }

    public void DeckClicked() throws Exception {
        new DeckMenu().start(stage);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    public void dueClicked() throws Exception {
        new DuelMenu().start(stage);
    }

    public void ExitClicked() {
        System.exit(0);
    }
}
