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
import sample.Game;
import sample.MonsterControllerInGame;
import sample.controller.DeleteDeck;
import sample.controller.Game.DrawCard;
import sample.controller.Game.GameController;
import sample.controller.LoginController;
import sample.controller.UserLogined;
import sample.model.Card.MonsterForUser;
import sample.model.Card.Position;
import sample.model.Card.SpellCardForUser;
import sample.model.Card.TrapCardForUser;
import sample.model.Deck;
import sample.model.User;

import java.util.ArrayList;

public class GameGraphic1 extends Application {
    private static Stage stage;

    public static Deck deckTempUser=new Deck(UserLogined.user.getActiveDeck().user,"komaki1");
    public static Deck deckTempOpponent=new Deck(UserLogined.opponent.getActiveDeck().user,"komaki2");

    public static MonsterForUser showCardMonsterHand;
    private static MonsterForUser showCardMonsterOpponentHand;
    public static MonsterForUser showMonsterFromZone;

    private static SpellCardForUser showCardSpellHand;
    private static SpellCardForUser showCardSpellOpponentHand;

    private static TrapCardForUser showCardTrapHand;
    private static TrapCardForUser showCardTrapOpponentHand;

    @FXML
    public Text nickName1;
    @FXML
    public Text lifePoint1;
    @FXML
    public Text opponentNickName1;
    @FXML
    public Text opponentLifePoint1;
    @FXML
    private Text error1;
    @FXML
    public Text phase1;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private Button nextPhase;

    @FXML
    private AnchorPane hand;
    @FXML
    private AnchorPane opponentHand;
    @FXML
    private AnchorPane show;
    @FXML
    private AnchorPane field;

    public String phase = "draw";
    public User user = UserLogined.user;
    public User opoonent = UserLogined.opponent;
    public boolean dasteAval = false;
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        anchorPane1 = FXMLLoader.load(getClass().getResource("User1.fxml"));
        Scene scene = new Scene(anchorPane1);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        nickName1.setText(UserLogined.user.getNickname());
        opponentNickName1.setText(UserLogined.opponent.getNickname());
        creatBoard();
        phase1.setText("Start");
        phase = "start";
    }


    public ShopCard showMonsterCard(User user, MonsterForUser monsterForUser) {
        if (showCardMonsterHand != null || showCardMonsterOpponentHand != null || showMonsterFromZone != null) {
            ShopCard card = new ShopCard(0, 0, 362, 242, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + monsterForUser.getName().replace(" ", "").replace("-", "") + ".jpg")))));
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (showCardMonsterHand != null) {

                        user.handMonster.add(monsterForUser);
                        showCardMonsterHand = null;
                        hand.getChildren().clear();

                    } else if (showCardMonsterOpponentHand != null) {

                        opoonent.handMonster.add(monsterForUser);
                        showCardMonsterOpponentHand = null;
                        opponentHand.getChildren().clear();

                    } else if (showMonsterFromZone != null) {

                        user.monsterZone[showMonsterFromZone.address] = showMonsterFromZone;
                        showMonsterFromZone = null;
                        field.getChildren().clear();

                    }

                    show.getChildren().clear();
                    creatBoard();
                }
            });
            return card;
        }
        return null;
    }

    public void creatBoard() {
        if (showCardMonsterHand != null) {
            show.getChildren().add(showMonsterCard(user, showCardMonsterHand));
            Button summon = new Button();
            summon.setText("Summon");
            summon.setTranslateX(250);
            summon.setTranslateY(30);
            summon.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String nextStep = "";
                    if (showCardMonsterHand.level <= 4) {
                        nextStep = MonsterControllerInGame.summon(showCardMonsterHand, user);
                    } else {
                        TributePart.user = user;
                        try {
                            new TributePart().start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (nextStep.equals("summoned successfully")) {
                        showCardMonsterHand = null;
                        show.getChildren().clear();
                        field.getChildren().clear();
                        creatBoard();
                    }
                }
            });
            show.getChildren().add(summon);

            Button set = new Button();
            set.setText("Set");
            set.setTranslateX(250);
            set.setTranslateY(60);

            show.getChildren().add(set);

        } else if (showCardMonsterOpponentHand != null) {

            show.getChildren().clear();
            show.getChildren().add(showMonsterCard(opoonent, showCardMonsterOpponentHand));

        } else if (showMonsterFromZone != null) {
            show.getChildren().clear();
            show.getChildren().add(showMonsterCard(user, showMonsterFromZone));
            Button attackDirect = new Button();
            attackDirect.setText("Attack direct");
            attackDirect.setTranslateX(250);
            attackDirect.setTranslateY(30);
            attackDirect.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        directAttack(user, opoonent, showMonsterFromZone);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            show.getChildren().add(attackDirect);

            Button attack = new Button();
            attack.setText("Attack");
            attack.setTranslateX(250);
            attack.setTranslateY(60);

            show.getChildren().add(attack);
        }

        field.getChildren().addAll(creatUserField(user, field));
        hand.getChildren().addAll(creatUserHand(UserLogined.user, hand, "user"));
        opponentHand.getChildren().addAll(creatUserHand(UserLogined.opponent, opponentHand, "opponent"));
        if (user.NumOfGrave != 0) {
            anchorPane1.getChildren().add(creatGrave(user, "user"));
        }
        if (opoonent.NumOfGrave != 0) {
            anchorPane1.getChildren().add(creatGrave(opoonent, "opponent"));
        }

    }

    public void directAttack(User user, User opoonent, MonsterForUser monsterForUser) throws Exception {
        if (dasteAval) {
            error1.setText("it is daste aval kako");
        } else if (phase.equals("battle")) {
            boolean checkIfOpponentMonsterZoneEmpty = true;
            for (int a = 0; a < 5; a++) {
                if (opoonent.monsterZone[a] != null) {
                    checkIfOpponentMonsterZoneEmpty = false;
                    break;
                }
            }
            if (checkIfOpponentMonsterZoneEmpty) {
                if (monsterForUser.position.equals(Position.valueOf("ATTACK"))) {
                    opoonent.lifePoint -= monsterForUser.ATK;
                    if (opoonent.lifePoint < 0) opoonent.lifePoint = 0;
                    if (opoonent.lifePoint == 0) {
                        endGame();
                    }
                    error1.setText("you opponent receives " + monsterForUser.ATK + " battale damage");
                    opponentLifePoint1.setText(opoonent.lifePoint + "");


                } else if (monsterForUser.position.equals(Position.valueOf("DEFEND"))) {
                    opoonent.lifePoint -= monsterForUser.DEF;
                    if (opoonent.lifePoint < 0) opoonent.lifePoint = 0;
                    if (opoonent.lifePoint == 0) {
                        endGame();
                    }
                    error1.setText("you opponent receives " + monsterForUser.DEF + " battale damage");
                    opponentLifePoint1.setText(opoonent.lifePoint + "");
                }// age hidden bashe chi mishe?flip summon?
            } else {
                error1.setText("you can’t attack the opponent directly");
            }
        } else {
            error1.setText("you can’t do this action in this phase");
        }
    }

    public void endGame() throws Exception {
        clearSelectedCard();
        user.getActiveDeck().copyDeck(deckTempUser);
        opoonent.getActiveDeck().copyDeck(deckTempOpponent);
        DeleteDeck.deleteDeck(deckTempUser,user);
        DeleteDeck.deleteDeck(deckTempOpponent,user);
        user.handMonster.clear();
        opoonent.handMonster.clear();
        user.handTrap.clear();
        opoonent.handTrap.clear();
        user.handSpell.clear();
        opoonent.handSpell.clear();
        user.fieldZone = null;
        opoonent.fieldZone = null;
        for (int i = 0; i < 5; i++) {
            user.monsterZone[i] = null;
            opoonent.monsterZone[i] = null;
            user.spellZone[i] = null;
            opoonent.spellZone[i] = null;
            user.trapZone[i] = null;
            opoonent.trapZone[i] = null;
        }
        user.monsterGrave.clear();
        opoonent.monsterGrave.clear();
        user.spellGrave.clear();
        opoonent.spellGrave.clear();
        user.spellGrave.clear();
        opoonent.spellGrave.clear();
        user.NumOfGrave = 0;
        new MainMenu().start(stage);
    }

    public ArrayList<ShopCard> creatUserField(User user, AnchorPane anchorPane) {
        ArrayList<ShopCard> allCards = new ArrayList<>();
        int x = 133;
        for (int f = 0; f < user.monsterZone.length; f++) {
            if (user.monsterZone[f] != null) {
                ShopCard card = new ShopCard(x, 393, 130, 90, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + user.monsterZone[f].getName().replace(" ", "").replace("-", "") + ".jpg")))));
                x += 95;
                int finalF = f;
                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        clearSelectedCard();
                        showMonsterFromZone = user.monsterZone[finalF];
                        user.monsterZone[finalF] = null;
                        field.getChildren().clear();
                        creatBoard();
                    }
                });
                allCards.add(card);
            }
        }
        return allCards;
    }


    public void back() throws Exception {
        endGame();
    }

    public void nextPhase() throws Exception {
        if (phase.equals("start")) {
            for (int y = 0; y < 4; y++) {
                DrawCard.draw(user);
                DrawCard.draw(opoonent);
            }
            creatBoard();
            phase="draw";
            phase1.setText("draw phase");
        } else if (phase.equals("draw")) {
            phase1.setText("Standby Phase");
            phase = "standby";
        } else if (phase.equals("standby")) {
            phase1.setText("Main Phase 1");
            phase = "phase1";
        } else if (phase.equals("phase1")) {
            phase1.setText("Battle Phase");
            phase = "battle";
        } else if (phase.equals("battle")) {
            phase1.setText("Main Phase 2");
            phase = "phase2";
        } else if (phase.equals("phase2")) {
            phase1.setText("End Phase");
            phase = "end";
        } else if (phase.equals("end")) {
            System.out.println("Fuck,what next?");
            User user =UserLogined.user;
            UserLogined.user=UserLogined.opponent;
            UserLogined.opponent=user;
            new GameGraphic1().start(stage);
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!dasteAval=false;
        }
    }

    public ArrayList<ShopCard> creatUserHand(User user, AnchorPane anchorPane, String who) {
        ArrayList<ShopCard> allCards = new ArrayList<>();
        int x = 547;
        for (MonsterForUser monsterForUser : user.handMonster) {
            ShopCard card = new ShopCard(x, 8, 156, 103, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + monsterForUser.getName().replace(" ", "").replace("-", "") + ".jpg")))));
            x -= 110;
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (who.equals("user")) {
                        clearSelectedCard();
                        showCardMonsterHand = monsterForUser;
                    } else if (who.equals("opponent")) {
                        clearSelectedCard();
                        showCardMonsterOpponentHand = monsterForUser;
                    }

                    user.handMonster.remove(monsterForUser);
                    anchorPane.getChildren().clear();
                    creatBoard();
                }
            });
            allCards.add(card);
        }
        return allCards;
    }


    public void clearSelectedCard() {

        if (showCardMonsterHand != null) {
            user.handMonster.add(showCardMonsterHand);
            showCardMonsterHand = null;
        }

        if (showCardMonsterOpponentHand != null) {
            opoonent.handMonster.add(showCardMonsterOpponentHand);
            showCardMonsterOpponentHand = null;
        }

        if (showMonsterFromZone != null) {
            user.monsterZone[showMonsterFromZone.address] = showMonsterFromZone;
            showMonsterFromZone = null;
        }

        if (showCardSpellHand != null) {
            UserLogined.user.handSpell.add(showCardSpellHand);
            showCardSpellHand = null;
        }

        if (showCardSpellOpponentHand != null) {
            UserLogined.opponent.handSpell.add(showCardSpellOpponentHand);
            showCardSpellOpponentHand = null;
        }

        if (showCardTrapHand != null) {
            UserLogined.user.handTrap.add(showCardTrapHand);
            showCardTrapHand = null;
        }

        if (showCardTrapOpponentHand != null) {
            UserLogined.opponent.handTrap.add(showCardTrapOpponentHand);
            showCardTrapOpponentHand = null;
        }
    }


    public ShopCard creatGrave(User user, String who) {
        int x = 0, y = 0, height = 0, weight = 0;
        if (who.equals("user")) {
            x = 37;
            y = 472;
            height = 150;
            weight = 105;
        } else if (who.equals("opponent")) {
            x = 52;
            y = 164;
            weight = 105;
            height = 52;
        }
        if (user.NumOfGrave > 0) {
            ShopCard card = new ShopCard(x, y, height, weight, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/Unknown.jpg")))));
            card.setRotate(90);
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Grave.user = user;
                    try {
                        new Grave().start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return card;
        }
        return null;
    }

}
