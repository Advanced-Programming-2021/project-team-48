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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.MonsterControllerInGame;
import sample.SpellControllerInGame;
import sample.controller.DeleteDeck;
import sample.controller.Game.DrawCard;
import sample.controller.UserLogined;
import sample.model.Card.MonsterForUser;
import sample.model.Card.Position;
import sample.model.Card.SpellCardForUser;
import sample.model.Card.TrapCardForUser;
import sample.model.Deck;
import sample.model.User;

import java.util.ArrayList;

public class GameGraphic1 extends Application {
    public static Stage stage;

    public static Deck deckTempUser = new Deck(UserLogined.user.getActiveDeck().user, "komaki1");
    public static Deck deckTempOpponent = new Deck(UserLogined.opponent.getActiveDeck().user, "komaki2");

    public static MonsterForUser showCardMonsterHand;
    private static MonsterForUser showCardMonsterOpponentHand;
    private static MonsterForUser showMonsterFromZone;
    private static MonsterForUser showMonsterFromZoneOpponent;

    public static SpellCardForUser showCardSpellHand;
    private static SpellCardForUser showCardSpellOpponentHand;
    public static SpellCardForUser showSpellFromZone;
    private static SpellCardForUser showSpellFromZoneOpponent;

    private static TrapCardForUser showCardTrapHand;
    private static TrapCardForUser showCardTrapOpponentHand;
    private static TrapCardForUser showTrapFromZone;
    private static TrapCardForUser showTrapFromZoneOpponent;


    public static User winner;
    public static User loser;
    @FXML
    public Text nickName1;
    @FXML
    public Text lifePoint1;
    @FXML
    public Text opponentNickName1;
    @FXML
    public Text opponentLifePoint1;
    @FXML
    public Text asliRemain;
    @FXML
    public Text asliRemainOpponent;
    @FXML
    private Text error1;
    @FXML
    public Text phase1;
    @FXML
    private AnchorPane anchorPane1;


    @FXML
    private AnchorPane hand;
    @FXML
    private AnchorPane opponentHand;
    @FXML
    private AnchorPane show;
    @FXML
    private AnchorPane field;

    public static String phase = "start";
    public static String error = "";
    public User user = UserLogined.user;
    public User opponent = UserLogined.opponent;
    public static boolean dasteAval = true;
    public static boolean hasSummon = false;


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
        lifePoint1.setText(user.lifePoint + "");
        opponentLifePoint1.setText(opponent.lifePoint + "");
        opponentNickName1.setText(UserLogined.opponent.getNickname());
        phase1.setText(phase);
        error1.setText(error);
        asliRemain.setText(user.getActiveDeck().numberOfCardsInMain + "");
        asliRemainOpponent.setText(opponent.getActiveDeck().numberOfCardsInMain + "");

        creatBoard();
    }


    public void creatBoard() {
        if (showCardMonsterHand != null) {
            show.getChildren().clear();
            show.getChildren().add(showMonsterCard(showCardMonsterHand));
            show.getChildren().addAll(creatHandMonsterButton());
        } else if (showCardMonsterOpponentHand != null) {
            show.getChildren().clear();
            show.getChildren().add(showMonsterCard(showCardMonsterOpponentHand));
        } else if (showMonsterFromZone != null) {
            show.getChildren().clear();
            show.getChildren().add(showMonsterCard(showMonsterFromZone));
            show.getChildren().addAll(creatMonsterZoneButtons());
        } else if (showMonsterFromZoneOpponent != null) {
            show.getChildren().clear();
            show.getChildren().add(showMonsterCard(showMonsterFromZoneOpponent));
        } else if (showCardSpellHand != null) {
            show.getChildren().clear();
            show.getChildren().add(creatShowSpell(showCardSpellHand));
            show.getChildren().addAll(creatSpellHandButton());
        } else if (showCardSpellOpponentHand != null) {
            show.getChildren().clear();
            show.getChildren().add(creatShowSpell(showCardSpellOpponentHand));
        } else if (showSpellFromZone != null) {
            show.getChildren().clear();
            show.getChildren().add(creatShowSpell(showSpellFromZone));
            show.getChildren().addAll(creatSpellHandButton());
        } else if (showSpellFromZoneOpponent != null) {
            show.getChildren().clear();
            show.getChildren().add(creatShowSpell(showSpellFromZoneOpponent));
        }


        field.getChildren().addAll(creatMonsterField(user, "user"));
        field.getChildren().addAll(creatMonsterField(opponent, "opponent"));
        field.getChildren().addAll(creatSpellField(user, "user"));
        field.getChildren().addAll(creatSpellField(opponent, "opponent"));

        hand.getChildren().addAll(creatUserHand(user, hand, "user"));
        opponentHand.getChildren().addAll(creatUserHand(opponent, opponentHand, "opponent"));

        if (user.NumOfGrave != 0) {
            anchorPane1.getChildren().add(creatGrave(user, "user"));
        }
        if (opponent.NumOfGrave != 0) {
            anchorPane1.getChildren().add(creatGrave(opponent, "opponent"));
        }

        asliRemain.setText(user.getActiveDeck().numberOfCardsInMain + "");
        asliRemainOpponent.setText(opponent.getActiveDeck().numberOfCardsInMain + "");
    }


    public ArrayList<Button> creatSpellHandButton() {
        ArrayList<Button> buttons = new ArrayList<>();
        if (showSpellFromZone != null && !showSpellFromZone.position.equals(Position.valueOf("ATTACK"))) {
            Button activeOnSet = new Button();
            activeOnSet.setText("Active Spell");
            activeOnSet.setTranslateX(250);
            activeOnSet.setTranslateY(30);
            activeOnSet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String nextStep = "";
                    try {
                        nextStep = SpellControllerInGame.SpellActiver(showSpellFromZone, user, opponent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (nextStep.equals("spell activated")) {
                        error1.setText(nextStep);
                        show.getChildren().clear();
                        field.getChildren().clear();
                        showSpellFromZone = null;
                        creatBoard();
                    }
                }
            });
            buttons.add(activeOnSet);
            return buttons;
        } else if (showCardSpellHand != null) {
            Button active = new Button();
            active.setText("Active Spell");
            active.setTranslateX(250);
            active.setTranslateY(30);
            active.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String nextStep = "";
                    try {
                        nextStep = SpellControllerInGame.SpellActiver(showCardSpellHand, user, opponent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (nextStep.equals("spell activated")) {
                        error1.setText(nextStep);
                        show.getChildren().clear();
                        field.getChildren().clear();
                        showCardSpellHand = null;
                        creatBoard();
                    }
                }
            });
            buttons.add(active);

            Button set = new Button();
            set.setText("Set Spell");
            set.setTranslateX(250);
            set.setTranslateY(60);
            set.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String nextStep = SpellControllerInGame.SpellSeter(showCardSpellHand, user);
                    if (nextStep.equals("set successfully")) {
                        showCardSpellHand = null;
                        error1.setText(nextStep);
                        show.getChildren().clear();
                        field.getChildren().clear();
                        creatBoard();
                    }
                }
            });
            buttons.add(set);

            return buttons;
        }
        return buttons;
    }


    public ArrayList<ShopCard> creatSpellField(User user1, String who) {
        ArrayList<ShopCard> allCards = new ArrayList<>();
        int x = 163, y = 0;
        if (who.equals("user")) {
            y = 620;
        } else {
            if (who.equals("opponent")) {
                y = 150;
            }
        }
        for (int a = 0; a < 5; a++) {
            if (user1.spellZone[a] != null && user1.trapZone[a] == null) {

                ShopCard card;
                if (user1.spellZone[a].position.equals(Position.valueOf("ATTACK"))) {
                    card = new ShopCard(x, y, 120, 90, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + user1.spellZone[a].getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));
                } else {
                    card = new ShopCard(x, y, 120, 90, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/Unknown.jpg")))));
                }
                int finalA = a;

                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        clearSelectedCard();
                        if (who.equals("user")) {
                            showSpellFromZone = user1.spellZone[finalA];
                            user1.spellZone[finalA] = null;
                        } else {
                            showSpellFromZoneOpponent = user1.spellZone[finalA];
                            user1.spellZone[finalA] = null;
                        }
                        field.getChildren().clear();
                        creatBoard();
                    }
                });
                allCards.add(card);

            } else if (user1.spellZone[a] == null && user1.trapZone[a] != null) {
                ShopCard card = new ShopCard(x, y, 120, 90, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + user1.spellZone[a].getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));


                int finalA = a;
                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        clearSelectedCard();
                        showTrapFromZone = user1.trapZone[finalA];
                        user1.trapZone[finalA] = null;
                        creatBoard();
                    }
                });
                allCards.add(card);

            }
            x += 120;
        }

        return allCards;
    }

    public ShopCard creatShowSpell(SpellCardForUser spellCardForUser) {
        ShopCard card;
        if (showSpellFromZoneOpponent != null && !spellCardForUser.position.equals(Position.valueOf("ATTACK"))) {
            card = new ShopCard(0, 10, 362, 242, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/Unknown.jpg")))));
        } else
            card = new ShopCard(0, 10, 362, 242, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + spellCardForUser.getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));
        card.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearSelectedCard();
                hand.getChildren().clear();
                show.getChildren().clear();
                creatBoard();
            }
        });
        return card;
    }


    public ArrayList<ShopCard> creatMonsterField(User user1, String who) {
        ArrayList<ShopCard> allCards = new ArrayList<>();
        int x = 163, y = 0;
        if (who.equals("user")) {
            y = 471;
        } else {
            if (who.equals("opponent")) {
                y = 290;
            }
        }
        for (int f = 0; f < user1.monsterZone.length; f++) {
            if (user1.monsterZone[f] != null) {
                ShopCard card;
                if (!user1.monsterZone[f].position.equals(Position.valueOf("HIDDEN"))) {
                    card = new ShopCard(x, y, 120, 90, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + user1.monsterZone[f].getName().replace(" ", "").replace("-", "") + ".jpg")))));
                } else
                    card = new ShopCard(x, y, 120, 90, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/Unknown.jpg")))));


                if (user1.monsterZone[f].position.equals(Position.valueOf("DEFEND"))) {
                    Text def = new Text();
                    def.setText("DEF " + user1.monsterZone[f].getDEF());
                    def.setX(x);
                    def.setFill(Color.RED);
                    def.setY(y + 130);
                    field.getChildren().add(def);

                    Text atk = new Text();
                    atk.setText("ATK " + user1.monsterZone[f].ATK);
                    atk.setX(x);
                    atk.setY(y + 145);
                    field.getChildren().add(atk);

                    card.setRotate(90);
                } else if (user1.monsterZone[f].position.equals(Position.ATTACK)) {
                    Text atk = new Text();
                    atk.setText("ATK " + user1.monsterZone[f].getATK());
                    atk.setX(x);
                    atk.setFill(Color.RED);
                    atk.setY(y + 130);
                    field.getChildren().add(atk);

                    Text def = new Text();
                    def.setText("DEF " + user1.monsterZone[f].DEF);
                    def.setX(x);
                    def.setY(y + 145);
                    field.getChildren().add(def);
                } else if (user1.monsterZone[f].position.equals(Position.valueOf("HIDDEN"))) {
                    card.setRotate(90);
                    if (who.equals("user")) {
                        Text def = new Text();
                        def.setText("DEF " + user1.monsterZone[f].getDEF());
                        def.setX(x);
                        def.setFill(Color.RED);
                        def.setY(y + 130);
                        field.getChildren().add(def);

                        Text atk = new Text();
                        atk.setText("ATK " + user1.monsterZone[f].ATK);
                        atk.setX(x);
                        atk.setY(y + 145);
                        field.getChildren().add(atk);
                    }
                }
                int finalF = f;
                if (who.equals("user")) {
                    card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            clearSelectedCard();
                            showMonsterFromZone = user1.monsterZone[finalF];
                            user1.monsterZone[finalF] = null;
                            field.getChildren().clear();
                            creatBoard();
                        }
                    });
                } else if (who.equals("opponent")) {
                    card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            clearSelectedCard();
                            showMonsterFromZoneOpponent = user1.monsterZone[finalF];
                            user1.monsterZone[finalF] = null;
                            field.getChildren().clear();
                            creatBoard();
                        }
                    });
                }
                allCards.add(card);
            }
            x += 120;
        }
        return allCards;
    }


    public void back() throws Exception {
        endGame();
    }

    public void nextPhase() throws Exception {
        if (phase.equals("start")) {
            if (dasteAval) {
                for (int y = 0; y < 4; y++) {
                    DrawCard.draw(user);
                    DrawCard.draw(opponent);
                }
            } else {
                if (user.getActiveDeck().numberOfCardsInMain == 0) {
                    endGame();
                } else
                    DrawCard.draw(user);
            }
            creatBoard();
            phase = "draw";
            phase1.setText(phase);
        } else if (phase.equals("draw")) {
            phase = "standby";
            phase1.setText(phase);
        } else if (phase.equals("standby")) {
            phase = "phase1";
            phase1.setText(phase);
        } else if (phase.equals("phase1")) {
            phase = "battle";
            phase1.setText(phase);
        } else if (phase.equals("battle")) {
            phase = "phase2";
            phase1.setText(phase);
        } else if (phase.equals("phase2")) {
            phase = "end";
            phase1.setText(phase);
        } else if (phase.equals("end")) {
            for (int i = 0; i < user.monsterZone.length; i++) {
                if (user.monsterZone[i] != null) {
                    user.monsterZone[i].canAttack = true;
                    user.monsterZone[i].canChange = true;
                }
            }
            User user = UserLogined.user;
            UserLogined.user = UserLogined.opponent;
            UserLogined.opponent = user;
            dasteAval = false;
            clearSelectedCard();
            hasSummon = false;
            phase = "start";
            error = "";
            phase1.setText(phase);
            new GameGraphic1().start(stage);
        }
    }

    public void clearSelectedCard() {

        if (showCardMonsterHand != null) {
            user.handMonster.add(showCardMonsterHand);
            showCardMonsterHand = null;
        }

        if (showCardMonsterOpponentHand != null) {
            opponent.handMonster.add(showCardMonsterOpponentHand);
            showCardMonsterOpponentHand = null;
        }

        if (showMonsterFromZone != null) {
            user.monsterZone[showMonsterFromZone.address] = showMonsterFromZone;
            showMonsterFromZone = null;
        }


        if (showMonsterFromZoneOpponent != null) {
            opponent.monsterZone[showMonsterFromZoneOpponent.address] = showMonsterFromZoneOpponent;
            showMonsterFromZoneOpponent = null;
        }


        if (showCardSpellHand != null) {
            user.handSpell.add(showCardSpellHand);
            showCardSpellHand = null;
        }

        if (showCardSpellOpponentHand != null) {
            opponent.handSpell.add(showCardSpellOpponentHand);
            showCardSpellOpponentHand = null;
        }

        if (showSpellFromZone != null) {
            user.spellZone[showSpellFromZone.address] = showSpellFromZone;
            showSpellFromZone = null;
        }


        if (showSpellFromZoneOpponent != null) {
            opponent.spellZone[showSpellFromZoneOpponent.address] = showSpellFromZoneOpponent;
            showSpellFromZoneOpponent = null;
        }


        if (showCardTrapHand != null) {
            user.handTrap.add(showCardTrapHand);
            showCardTrapHand = null;
        }

        if (showCardTrapOpponentHand != null) {
            opponent.handTrap.add(showCardTrapOpponentHand);
            showCardTrapOpponentHand = null;
        }


    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public ArrayList<ShopCard> creatUserHand(User user1, AnchorPane anchorPane, String who) {
        ArrayList<ShopCard> allCards = new ArrayList<>();
        int x = 547;
        for (MonsterForUser monsterForUser : user1.handMonster) {
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

                    user1.handMonster.remove(monsterForUser);
                    anchorPane.getChildren().clear();
                    creatBoard();
                }
            });
            allCards.add(card);
        }

        for (SpellCardForUser spellCardForUser : user1.handSpell) {
            ShopCard card = new ShopCard(x, 8, 156, 103, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + spellCardForUser.getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));
            x -= 110;
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (who.equals("user")) {
                        clearSelectedCard();
                        showCardSpellHand = spellCardForUser;
                    } else if (who.equals("opponent")) {
                        clearSelectedCard();
                        showCardSpellOpponentHand = spellCardForUser;
                    }
                    user1.handSpell.remove(spellCardForUser);
                    anchorPane.getChildren().clear();
                    creatBoard();
                }
            });
            allCards.add(card);
        }

        for (TrapCardForUser trapCardForUser : user1.handTrap) {
            ShopCard card = new ShopCard(x, 8, 156, 103, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + trapCardForUser.getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));
            x -= 110;
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (who.equals("user")) {
                        clearSelectedCard();
                        showCardTrapHand = trapCardForUser;
                    } else if (who.equals("opponent")) {
                        clearSelectedCard();
                        showCardTrapOpponentHand = trapCardForUser;
                    }
                    user1.handTrap.remove(trapCardForUser);
                    anchorPane.getChildren().clear();
                    creatBoard();
                }
            });
            allCards.add(card);
        }

        return allCards;
    }

    public ShopCard creatGrave(User user, String who) {
        int x = 0, y = 0, height = 0, weight = 0;
        if (who.equals("user")) {
            x = 159;
            y = 449;

            weight = 105;
            height = 150;
        } else if (who.equals("opponent")) {
            x = 493;
            y = 5;

            weight = 45;
            height = 62;
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

    public void endGame() throws Exception {
        if (user.lifePoint != 0) {
            winner = user;
            loser = opponent;
        } else {
            loser = user;
            winner = opponent;
        }
        winner.setMoney(winner.getMoney() + user.getLifePoint() + 1000);
        loser.setMoney(winner.getMoney() + 100);
        winner.setScore(winner.getScore() + 1000);
        error = "";
        for (int i = 0; i < user.monsterZone.length; i++) {
            if (user.monsterZone[i] != null) {
                user.monsterZone[i].canAttack = false;
                user.monsterZone[i].canChange = false;
            }
        }
        for (int i = 0; i < opponent.monsterZone.length; i++) {
            if (opponent.monsterZone[i] != null) {
                opponent.monsterZone[i].canAttack = false;
                opponent.monsterZone[i].canChange = false;
            }
        }
        phase = "start";
        dasteAval = true;
        clearSelectedCard();
        user.getActiveDeck().copyDeck(deckTempUser);
        opponent.getActiveDeck().copyDeck(deckTempOpponent);
        DeleteDeck.deleteDeck(deckTempUser, user);
        DeleteDeck.deleteDeck(deckTempOpponent, user);
        user.handMonster.clear();
        opponent.handMonster.clear();
        user.handTrap.clear();
        opponent.handTrap.clear();
        user.handSpell.clear();
        opponent.handSpell.clear();
        user.fieldZone = null;
        opponent.fieldZone = null;
        for (int i = 0; i < 5; i++) {
            user.monsterZone[i] = null;
            opponent.monsterZone[i] = null;
            user.spellZone[i] = null;
            opponent.spellZone[i] = null;
            user.trapZone[i] = null;
            opponent.trapZone[i] = null;
        }
        user.monsterGrave.clear();
        opponent.monsterGrave.clear();
        user.spellGrave.clear();
        opponent.spellGrave.clear();
        user.spellGrave.clear();
        opponent.spellGrave.clear();
        user.NumOfGrave = 0;
        new MainMenu().start(stage);
    }


    public ShopCard showMonsterCard(MonsterForUser monsterForUser) {
        if (showCardMonsterHand != null || showCardMonsterOpponentHand != null || showMonsterFromZone != null || showMonsterFromZoneOpponent != null) {
            ShopCard card;
            if (showMonsterFromZoneOpponent != null) {
                if (showMonsterFromZoneOpponent.position.equals(Position.valueOf("HIDDEN"))) {
                    card = new ShopCard(0, 10, 362, 242, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/Unknown.jpg")))));
                } else {
                    card = new ShopCard(0, 10, 362, 242, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + showMonsterFromZoneOpponent.getName().replace(" ", "").replace("-", "") + ".jpg")))));
                }
            } else
                card = new ShopCard(0, 10, 362, 242, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + monsterForUser.getName().replace(" ", "").replace("-", "") + ".jpg")))));

            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (showCardMonsterHand != null) {

                        user.handMonster.add(showCardMonsterHand);
                        showCardMonsterHand = null;
                        hand.getChildren().clear();

                    } else if (showCardMonsterOpponentHand != null) {

                        opponent.handMonster.add(showCardMonsterOpponentHand);
                        showCardMonsterOpponentHand = null;
                        opponentHand.getChildren().clear();

                    } else if (showMonsterFromZone != null) {

                        user.monsterZone[showMonsterFromZone.address] = showMonsterFromZone;
                        showMonsterFromZone = null;
                        field.getChildren().clear();

                    } else if (showMonsterFromZoneOpponent != null) {
                        opponent.monsterZone[showMonsterFromZoneOpponent.address] = showMonsterFromZoneOpponent;
                        showMonsterFromZoneOpponent = null;
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


    public ArrayList<Button> creatMonsterZoneButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        if (showMonsterFromZone.position.equals(Position.valueOf("ATTACK"))) {
            Button attackDirect = new Button();
            attackDirect.setText("Attack direct");
            attackDirect.setTranslateX(250);
            attackDirect.setTranslateY(30);
            attackDirect.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    //if (phase.equal("battle"){
                    try {
                        directAttack(user, opponent, showMonsterFromZone);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //}else error1.setText("phase eshtebah");
            });
            show.getChildren().add(attackDirect);

            Button attack = new Button();
            attack.setText("Attack");
            attack.setTranslateX(250);
            attack.setTranslateY(60);
            attack.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    //if (phase.equal("battle"){
                    if (showMonsterFromZone.canAttack) {
                        AttackCard.user = user;
                        AttackCard.opponent = opponent;
                        AttackCard.showMonsterFromZone = showMonsterFromZone;
                        try {
                            clearSelectedCard();
                            new AttackCard().start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        error1.setText("amo nmitoni ba in attack bzni");
                    }
                    //}else error1.setText("phase eshtebah");
                }
            });
            show.getChildren().add(attack);


            Button change = new Button();
            if (showMonsterFromZone.position.equals(Position.valueOf("ATTACK"))) {
                change.setText("Change To Defense Position");
                change.setTranslateX(250);
                change.setTranslateY(90);
                change.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (showMonsterFromZone.canChange) {
                            String natije = MonsterControllerInGame.positionDefend(showMonsterFromZone);
                            error1.setText(natije);
                            show.getChildren().clear();
                            creatBoard();
                        } else error1.setText("chand bar?ye bar avaz kardi");
                    }
                });
            }

            buttons.add(change);
        } else if (showMonsterFromZone.position.equals(Position.valueOf("DEFEND"))) {
            Button change = new Button();
            change.setText("Change To Attack Position");
            change.setTranslateX(250);
            change.setTranslateY(90);
            change.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (showMonsterFromZone.canChange) {

                        String natije = MonsterControllerInGame.positionAttack(showMonsterFromZone);
                        error1.setText(natije);
                        show.getChildren().clear();
                        creatBoard();
                    } else error1.setText("chand bar?ye bar avaz kardi");
                }
            });
            buttons.add(change);
        } else if (showMonsterFromZone.position.equals(Position.valueOf("HIDDEN"))) {
            Button change = new Button();
            change.setText("Flip Summon");
            change.setTranslateX(250);
            change.setTranslateY(90);
            change.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (showMonsterFromZone.canChange) {
                        String natije = MonsterControllerInGame.flipSummon(showMonsterFromZone);
                        error1.setText(natije);
                        show.getChildren().clear();
                        creatBoard();
                    } else error1.setText("chand bar?ye bar avaz kardi");
                }
            });
            buttons.add(change);
        }
        return buttons;
    }

    public ArrayList<Button> creatHandMonsterButton() {
        ArrayList<Button> buttons = new ArrayList<>();
        Button summon = new Button();
        summon.setText("Summon");
        summon.setTranslateX(250);
        summon.setTranslateY(30);
        summon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //if (phase.equals("phase1") || phase.equals("phase2")) {
                //if (!hasSummon) {
                String nextStep = "";
                if (showCardMonsterHand.level <= 4) {
                    nextStep = MonsterControllerInGame.summon(showCardMonsterHand, user);
                } else {
                    int i = 0;
                    for (int a = 0; a < 5; a++) {
                        if (user.monsterZone[a] != null) {
                            i++;
                        }
                    }
                    boolean canContinue = false;
                    if (showCardMonsterHand.level <= 6 && i > 0) {
                        canContinue = true;
                    }
                    if (showCardMonsterHand.level > 6 && i > 1) {
                        canContinue = true;
                    }
                    if (canContinue) {

                        TributePart.user = user;
                        TributePart.setOrSum = "sum";
                        try {
                            new TributePart().start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else error1.setText("not enough cards to tribute");
                }

                if (nextStep.equals("summoned successfully")) {
                    hasSummon = true;
                    showCardMonsterHand = null;
                    show.getChildren().clear();
                    field.getChildren().clear();
                    creatBoard();
                }
                    /*} else {
                        error1.setText("ye bar gozashti dige");
                    }
                 /*else{
                 ja else ava shod. tanzim she !
                        error1.setText("phase ro eshtebah omadi dadsh");
                    }

                  */
            }
        });
        buttons.add(summon);

        Button set = new Button();
        set.setText("Set");
        set.setTranslateX(250);
        set.setTranslateY(60);
        set.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //if (phase.equals("phase1") || phase.equals("phase2")) {
                if (!hasSummon) {
                    String nextStep = "";
                    if (showCardMonsterHand.level <= 4) {
                        nextStep = MonsterControllerInGame.set(showCardMonsterHand, user);
                    } else {
                        int i = 0;
                        for (int a = 0; a < 5; a++) {
                            if (user.monsterZone[a] != null) {
                                i++;
                            }
                        }
                        boolean canContinue = false;
                        if (showCardMonsterHand.level <= 6 && i > 0) {
                            canContinue = true;
                        }
                        if (showCardMonsterHand.level > 6 && i > 1) {
                            canContinue = true;
                        }
                        if (canContinue) {

                            TributePart.user = user;
                            TributePart.setOrSum = "set";
                            try {
                                new TributePart().start(stage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else error1.setText("not enough cards to tribute");
                    }

                    if (nextStep.equals("set successfully")) {
                        hasSummon = true;
                        showCardMonsterHand = null;
                        show.getChildren().clear();
                        field.getChildren().clear();
                        creatBoard();
                    }
                } else {
                    error1.setText("ye bar gozashti dige");
                }
                 /*else{
                 ja else ava shod. tanzim she !
                        error1.setText("phase ro eshtebah omadi dadsh");
                    }

                  */
            }
        });
        buttons.add(set);
        return buttons;
    }

}
