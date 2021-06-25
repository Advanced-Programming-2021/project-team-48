package sample.view.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.controller.DeleteDeck;
import sample.controller.LoginController;
import sample.controller.UserLogined;
import sample.model.Deck;

import java.util.ArrayList;

public class DeckMenuAnchorPane extends AnchorPane {

    public DeckMenuAnchorPane(int number, Deck deck) {

        this.setTranslateY(number * 50);
        this.setTranslateX(0);
        this.setPrefWidth(1500);
        this.setPrefHeight(40);
        this.setStyle("-fx-background-color: #e4dcf1");

        Label name = new Label();
        name.setText(deck.getName());
        name.setTranslateX(30);
        name.setTranslateY(0);
        name.setFont(new Font("Cambria", 32));
        name.setTextFill(Color.web("#594F4F", 0.8));
        this.getChildren().add(name);




        Button edit = new Button();
        edit.setText("Edit");
        edit.setTranslateX(1449);
        edit.setTranslateY(0);
        edit.setId("edit" + deck.getName());
        edit.setPrefWidth(37);
        edit.setPrefHeight(38);
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UserLogined.deck=deck;
                try {
                    DeckMenu.goToDeckShow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.getChildren().add(edit);

    }
}
