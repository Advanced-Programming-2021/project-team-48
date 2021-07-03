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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Game;
import sample.controller.UserLogined;
import sample.model.Card.MonsterForUser;
import sample.model.Card.Position;
import sample.model.User;

import java.util.ArrayList;

public class AttackCard extends Application {
    private static Stage stage;
    @FXML
    AnchorPane opponentCards;
    @FXML
    AnchorPane attackPart;
    private MonsterForUser selectedMonster;
    public static User user;
    public static User opponent;
    public static MonsterForUser showMonsterFromZone;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("AttackCard.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        creatBoard();
    }

    public void creatBoard() {
        ImageView card = new ImageView();
        card.setImage(new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + showMonsterFromZone.getName().replace(" ", "").replace("-", "") + ".jpg")))));
        card.setTranslateX(68);
        card.setTranslateY(48);
        card.setFitWidth(275);
        card.setFitHeight(407);
        attackPart.getChildren().add(card);


        ImageView arrow = new ImageView(new Image(String.valueOf((getClass().getResource("Assets/580b57fcd9996e24bc43c43d.png")))));
        arrow.setTranslateX(367);
        arrow.setTranslateY(116);
        arrow.setFitWidth(178);
        arrow.setFitHeight(257);
        attackPart.getChildren().add(arrow);

        Button attack = new Button();
        attack.setText("Attack");
        attack.setTranslateX(366);
        attack.setTranslateY(309);
        attack.setPrefHeight(26);
        attack.setPrefWidth(178);
        attack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    attackClicked();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        attackPart.getChildren().add(attack);

        opponentCards.getChildren().addAll(creatOpponent(opponent));

        if (selectedMonster != null) {
            ShopCard selected = new ShopCard(573, 47, 407, 275, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + selectedMonster.getName().replace(" ", "").replace("-", "") + ".jpg")))));
            selected.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    selectedMonster=null;
                    attackPart.getChildren().clear();
                    creatBoard();
                }
            });
            attackPart.getChildren().add(selected);
        }
    }

    public ArrayList<ShopCard> creatOpponent(User user) {
        ArrayList<ShopCard> allCards = new ArrayList<>();
        int x = 750;
        for (int j = 0; j < user.monsterZone.length; j++) {
            if (user.monsterZone[j] != null) {
                ShopCard card = new ShopCard(x, 8, 156, 103, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + user.monsterZone[j].getName().replace(" ", "").replace("-", "") + ".jpg")))));
                if (user.monsterZone[j].position.equals(Position.valueOf("HIDDEN"))){
                    card.setRotate(90);
                }
                if (user.monsterZone[j].position.equals(Position.valueOf("DEFEND"))){
                    card.setRotate(90);
                }
                x -= 145;
                int finalJ = j;
                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        selectedMonster = user.monsterZone[finalJ];
                        attackPart.getChildren().clear();
                        creatBoard();
                    }
                });
                allCards.add(card);
            }
        }
        return allCards;
    }

    public void attackClicked() throws Exception {
        if (selectedMonster!=null){
            Game.attack(showMonsterFromZone,selectedMonster,user,opponent);
            showMonsterFromZone.canAttack=false;
            new GameGraphic1().start(stage);
        }
    }
    public void back() throws Exception {
        new GameGraphic1().start(stage);
    }
}
