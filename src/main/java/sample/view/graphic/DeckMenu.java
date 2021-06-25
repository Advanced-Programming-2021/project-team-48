package sample.view.graphic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.DeckCreat;
import sample.controller.DeleteDeck;
import sample.controller.UserLogined;
import sample.model.Deck;
import sample.model.User;

import java.util.ArrayList;

public class DeckMenu extends Application {
    private static Stage stage;
    @FXML
    public AnchorPane innerAnchorPane;
    @FXML
    private Text username;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("DeckMenu.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        username.setText(UserLogined.user.getUsername());
        int i = 0;
        for (Deck deck : UserLogined.user.allDecks) {
            i++;
        }

        if (i>13){
        innerAnchorPane.setPrefHeight(innerAnchorPane.getPrefHeight() + 50 * (i-13));}

        innerAnchorPane.getChildren().addAll(creatScorePane());
    }

    public ArrayList<DeckMenuAnchorPane> creatScorePane() {
        ArrayList<DeckMenuAnchorPane> allDecks = new ArrayList<>();
        int i = 1;
        for (Deck deck : UserLogined.user.allDecks) {
            DeckMenuAnchorPane deckMenuAnchorPane = new DeckMenuAnchorPane(i, deck);
            Button delete = new Button();
            delete.setText("Delete");
            delete.setTranslateX(1398);
            delete.setTranslateY(0);
            delete.setPrefWidth(50);
            delete.setPrefHeight(38);
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    DeleteDeck.deleteDeck(deck, UserLogined.user);
                    try {
                        deleteDeckClicked();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            deckMenuAnchorPane.getChildren().add(delete);

            i++;


            allDecks.add(deckMenuAnchorPane);
        }
        return allDecks;
    }

    public void AddNewDeckClicked() throws Exception {
       new AddDeck().start(stage);
    }

    public void deleteDeckClicked() {
        innerAnchorPane.getChildren().clear();
        innerAnchorPane.getChildren().addAll(creatScorePane());
    }

    public static void goToDeckShow() throws Exception {
        new DeckShow().start(stage);
    }


    public void backToMainMenuClicked() throws Exception {
        new MainMenu().start(stage);
    }
}
