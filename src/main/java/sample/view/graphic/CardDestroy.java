package sample.view.graphic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.UserLogined;
import sample.model.Card.*;
import sample.model.User;

import java.util.ArrayList;

public class CardDestroy extends Application {
    private static Stage stage;
    @FXML
    private AnchorPane showPart;
    @FXML
    private AnchorPane cards;
    @FXML
    Text error;

    public MonsterForUser selectedMonster;


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("CardDestroy.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        creatBoard();
    }

    public void creatBoard() {


        Button active = new Button();
        active.setText("Destroy");
        active.setTranslateX(366);
        active.setTranslateY(309);
        active.setPrefHeight(26);
        active.setPrefWidth(178);
        active.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    destroyClicked();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        showPart.getChildren().add(active);

        cards.getChildren().addAll(creatOpponent());

        if (selectedMonster != null) {
            ShopCard selected = new ShopCard(573, 47, 407, 275, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + selectedMonster.getName().replace(" ", "").replace("-", "") + ".jpg")))));

            selected.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    selectedMonster = null;
                    showPart.getChildren().clear();
                    creatBoard();
                }
            });
            showPart.getChildren().add(selected);
        }

        showPart.getChildren().addAll(creatOpponent());
    }

    public ArrayList<ShopCard> creatOpponent() {
        User user = UserLogined.opponent;
        ArrayList<ShopCard> allCards = new ArrayList<>();
        int x = 750;
        for (int j = 0; j < user.monsterZone.length; j++) {
            ShopCard card;
            if (user.monsterZone[j] != null) {
                if (user.monsterZone[j].position.equals(Position.valueOf("HIDDEN"))) {
                    card = new ShopCard(x, 8, 156, 103, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/Unknown.jpg")))));
                    card.setRotate(90);
                } else {
                    card = new ShopCard(x, 8, 156, 103, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + user.monsterZone[j].getName().replace(" ", "").replace("-", "") + ".jpg")))));
                    if (user.monsterZone[j].position.equals(Position.valueOf("DEFEND"))) {
                        card.setRotate(90);
                    }
                }
                x -= 145;
                int finalJ = j;
                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        selectedMonster = user.monsterZone[finalJ];
                        showPart.getChildren().clear();
                        creatBoard();
                    }
                });
                allCards.add(card);
            }
        }

        return allCards;
    }

    public void destroyClicked() throws Exception {
        User user = UserLogined.opponent;
        selectedMonster.setField(Field.GRAVE);
        user.monsterZone[selectedMonster.address] = null;
        selectedMonster.address = user.NumOfGrave;
        user.NumOfGrave++;
        user.monsterGrave.add(selectedMonster);
        new GameGraphic1().start(stage);
    }

    public void back() throws Exception {
        new GameGraphic1().start(stage);
    }
}
