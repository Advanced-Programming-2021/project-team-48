package sample.view.graphic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.MonsterControllerInGame;
import sample.controller.Game.GameController;
import sample.controller.UserLogined;
import sample.model.Card.MonsterForUser;
import sample.model.Card.SpellCardForUser;
import sample.model.Card.TrapCardForUser;
import sample.model.User;

import java.util.ArrayList;

public class TributePart extends Application {
    private static Stage stage;
    @FXML
    AnchorPane main;
    @FXML
    AnchorPane show;
    @FXML
    AnchorPane hand;

    @FXML
    Text nickname;

    public static User user;
    private MonsterForUser tribute1;
    private MonsterForUser tribute2;
    private MonsterForUser showCard;

    public static String setOrSum = "";

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("TributePart.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        nickname.setText(UserLogined.user.getNickname());
        creatBoard();
    }

    public void creatBoard() {
        main.getChildren().add(new ShopCard(1290, 167, 198, 146, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + GameGraphic1.showCardMonsterHand.getName().replace(" ", "").replace("-", "") + ".jpg"))))));
        hand.getChildren().addAll(creatHand());
        if (showCard != null) {
            show.getChildren().add(creatShowCard());
            if (showCard != tribute1) {
                Button tribute = new Button();
                tribute.setText("tribute");
                tribute.setTranslateX(250);
                tribute.setTranslateY(30);
                tribute.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        if (GameGraphic1.showCardMonsterHand.level == 6 || GameGraphic1.showCardMonsterHand.level == 5) {
                            MonsterControllerInGame.tributeGraphic(user, showCard);
                            if (setOrSum.equals("sum")) {
                                MonsterControllerInGame.summon(GameGraphic1.showCardMonsterHand, user);
                            } else if (setOrSum.equals("set")){
                                MonsterControllerInGame.set(GameGraphic1.showCardMonsterHand,user);
                            }
                                GameGraphic1.showCardMonsterHand = null;

                            try {
                                new GameGraphic1().start(stage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (GameGraphic1.showCardMonsterHand.level > 6) {
                            if (tribute1 != null) {
                                MonsterControllerInGame.tributeGraphic(user, showCard);
                                MonsterControllerInGame.tributeGraphic(user, tribute1);
                                if (setOrSum.equals("sum")) {
                                    MonsterControllerInGame.summon(GameGraphic1.showCardMonsterHand, user);
                                } else if (setOrSum.equals("set")){
                                    MonsterControllerInGame.set(GameGraphic1.showCardMonsterHand,user);
                                }
                                GameGraphic1.showCardMonsterHand = null;
                                try {
                                    new GameGraphic1().start(stage);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                tribute1 = showCard;
                                showCard = null;
                                show.getChildren().clear();
                                creatBoard();
                            }

                        }
                    }
                });
                show.getChildren().add(tribute);
            }
        }
        if (tribute1 != null) {
            main.getChildren().add(creatTribute1());
        }
    }

    private ShopCard creatTribute1() {
        if (tribute1 != null) {
            ShopCard card = new ShopCard(1218, 481, 176, 121, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + tribute1.getName().replace(" ", "").replace("-", "") + ".jpg")))));
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    tribute1 = null;
                    main.getChildren().clear();
                    creatBoard();
                }
            });
            return card;
        }
        return null;
    }

    public ShopCard creatShowCard() {
        if (showCard != null) {
            ShopCard card = new ShopCard(0, 0, 198, 146, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + showCard.getName().replace(" ", "").replace("-", "") + ".jpg")))));
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    showCard = null;
                    show.getChildren().clear();
                    creatBoard();
                }
            });
            return card;
        }
        return null;
    }

    public ArrayList<ShopCard> creatHand() {
        ArrayList<ShopCard> allCards = new ArrayList<>();
        int x = 508;
        for (int f = 0; f < user.monsterZone.length; f++) {
            if (user.monsterZone[f] != null) {
                ShopCard card = new ShopCard(x, 8, 156, 103, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + user.monsterZone[f].getName().replace(" ", "").replace("-", "") + ".jpg")))));
                x -= 110;
                int finalF = f;
                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        showCard = user.monsterZone[finalF];
                        creatBoard();
                    }
                });
                allCards.add(card);
            }
        }
        return allCards;
    }

    public void Cancel() throws Exception {
        new GameGraphic1().start(stage);
    }
}
