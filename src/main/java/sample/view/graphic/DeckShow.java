package sample.view.graphic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.MonsterAdderToDeck;
import sample.controller.SpellAdderToDeck;
import sample.controller.TrapAdderToDeck;
import sample.controller.UserLogined;
import sample.model.Card.*;
import sample.model.User;

import java.util.ArrayList;

public class DeckShow extends Application {
    private static Stage stage;
    @FXML
    AnchorPane mainDeck;
    @FXML
    AnchorPane sideDeck;
    @FXML
    AnchorPane allCards;
    @FXML
    Text deckName;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        AnchorPane parent = FXMLLoader.load(getClass().getResource("DeckShow.fxml"));
        Scene scene = new Scene(parent);
        CheckBox active = new CheckBox();
        active.setText("Active");
        active.setTranslateX(1420);
        active.setTranslateY(7);
        if (UserLogined.user.hasActiveDeck) {
            if (UserLogined.user.getActiveDeck().getName().equals(UserLogined.deck.getName())) {
                active.setSelected(true);

                active.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        active.setSelected(false);
                        UserLogined.user.hasActiveDeck = false;
                        UserLogined.user.setActiveDeck(null);
                    }
                });
            }
        }else {
            active.setSelected(false);

            active.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    active.setSelected(true);
                    UserLogined.user.hasActiveDeck = true;
                    UserLogined.user.setActiveDeck(UserLogined.deck);
                }
            });
        }
        parent.getChildren().add(active);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        deckName.setText(UserLogined.deck.getName());
        allCards.getChildren().addAll(creatAllCards());
        mainDeck.getChildren().addAll(creatMainDeck());
        sideDeck.getChildren().addAll(creatSideDeck());
    }


    public ArrayList<ShopCard> creatMainDeck() {
        int i = 0, j = 25;
        ArrayList<ShopCard> allcards = new ArrayList<>();
        for (MonsterForUser monsterCard : UserLogined.deck.allMonsterForUserMain) {
            ShopCard card = new ShopCard(i, j, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + monsterCard.getName().replace(" ", "").replace("-", "") + ".jpg")))));
            allcards.add(card);
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String nextStep = MonsterAdderToDeck.AddMonsterToSideDeck(UserLogined.deck, monsterCard);
                    if (nextStep.equals("done")) {
                        sideDeck.getChildren().addAll(creatSideDeck());
                        mainDeck.getChildren().clear();
                        mainDeck.getChildren().addAll(creatMainDeck());
                    }
                }
            });
            i += 200;
            if (i == 1000) {
                i = 0;
                j += 220;
            }
        }

        for (SpellCardForUser spellCard : UserLogined.deck.allSpellCardsForUserMain) {
            ShopCard card = new ShopCard(i, j, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + spellCard.getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));
            allcards.add(card);
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String nextStep = SpellAdderToDeck.AddSpelllToSideDeck(UserLogined.deck, spellCard);
                    if (nextStep.equals("done")) {
                        sideDeck.getChildren().addAll(creatSideDeck());
                        mainDeck.getChildren().clear();
                        mainDeck.getChildren().addAll(creatMainDeck());
                    }
                }
            });
            i += 200;
            if (i == 1000) {
                i = 0;
                j += 220;
            }
        }

        for (TrapCardForUser trapCard : UserLogined.deck.allTrapCardsForUserMain) {
            if (trapCard.getName().equals("Magic Jammer")) {
                ShopCard card = new ShopCard(i, j, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + trapCard.getName().replace(" ", "").replace("-", "") + ".png")))));
                allcards.add(card);
                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        String nextStep = TrapAdderToDeck.AddTrapToSideDeck(UserLogined.deck, trapCard);
                        if (nextStep.equals("done")) {
                            sideDeck.getChildren().addAll(creatSideDeck());
                            mainDeck.getChildren().clear();
                            mainDeck.getChildren().addAll(creatMainDeck());
                        }
                    }
                });
            } else {
                ShopCard card = new ShopCard(i, j, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + trapCard.getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));
                allcards.add(card);
                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        String nextStep = TrapAdderToDeck.AddTrapToSideDeck(UserLogined.deck, trapCard);
                        if (nextStep.equals("done")) {
                            sideDeck.getChildren().addAll(creatSideDeck());
                            mainDeck.getChildren().clear();
                            mainDeck.getChildren().addAll(creatMainDeck());
                        }
                    }
                });
            }
            i += 200;
            if (i == 1000) {
                i = 0;
                j += 220;
            }
        }
        return allcards;
    }

    //-----------------------------------------------------------------------------------------------------------
    public ArrayList<ShopCard> creatSideDeck() {
        int i = 0, j = 25;
        ArrayList<ShopCard> allcards = new ArrayList<>();
        for (MonsterForUser monsterCard : UserLogined.deck.allMonsterForUserSide) {
            ShopCard card = new ShopCard(i, j, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + monsterCard.getName().replace(" ", "").replace("-", "") + ".jpg")))));
            allcards.add(card);
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String nextStep = MonsterAdderToDeck.AddMonsterToUser(UserLogined.deck, monsterCard);
                    if (nextStep.equals("done")) {
                        allCards.getChildren().addAll(creatAllCards());
                        sideDeck.getChildren().clear();
                        sideDeck.getChildren().addAll(creatSideDeck());
                    }
                }
            });

            i += 200;
            if (i == 600) {
                i = 0;
                j += 220;
            }
        }

        for (SpellCardForUser spellCard : UserLogined.deck.allSpellCardsForUserSide) {
            ShopCard card = new ShopCard(i, j, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + spellCard.getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));
            allcards.add(card);

            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String nextStep = SpellAdderToDeck.AddMonsterToUser(UserLogined.deck, spellCard);
                    if (nextStep.equals("done")) {
                        allCards.getChildren().addAll(creatAllCards());
                        sideDeck.getChildren().clear();
                        sideDeck.getChildren().addAll(creatSideDeck());
                    }
                }
            });

            i += 200;
            if (i == 600) {
                i = 0;
                j += 220;
            }
        }

        for (TrapCardForUser trapCard : UserLogined.deck.allTrapCardsForUserSide) {
            if (trapCard.getName().equals("Magic Jammer")) {
                ShopCard card = new ShopCard(i, j, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + trapCard.getName().replace(" ", "").replace("-", "") + ".png")))));
                allcards.add(card);
                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        String nextStep = TrapAdderToDeck.AddTrapToUser(UserLogined.deck, trapCard);
                        if (nextStep.equals("done")) {
                            allCards.getChildren().addAll(creatAllCards());
                            sideDeck.getChildren().clear();
                            sideDeck.getChildren().addAll(creatSideDeck());
                        }
                    }
                });
            } else {
                ShopCard card = new ShopCard(i, j, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + trapCard.getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));
                allcards.add(card);
                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        String nextStep = TrapAdderToDeck.AddTrapToUser(UserLogined.deck, trapCard);
                        if (nextStep.equals("done")) {
                            allCards.getChildren().addAll(creatAllCards());
                            sideDeck.getChildren().clear();
                            sideDeck.getChildren().addAll(creatSideDeck());
                        }
                    }
                });
            }
            i += 200;
            if (i == 600) {
                i = 0;
                j += 220;
            }
        }
        return allcards;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<ShopCard> creatAllCards() {
        int i = 0;
        ArrayList<ShopCard> allcards = new ArrayList<>();
        for (MonsterForUser monsterCard : UserLogined.user.allMonsters) {
            ShopCard card = new ShopCard(i, 0, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + monsterCard.getName().replace(" ", "").replace("-", "") + ".jpg")))));
            allcards.add(card);
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String nextStep = MonsterAdderToDeck.AddMonsterToMain(UserLogined.deck, monsterCard);
                    if (nextStep.equals("done")) {
                        mainDeck.getChildren().addAll(creatMainDeck());
                        allCards.getChildren().clear();
                        allCards.getChildren().addAll(creatAllCards());
                    }
                }
            });
            i += 200;
        }

        for (SpellCardForUser spellCard : UserLogined.user.allSpells) {
            ShopCard card = new ShopCard(i, 0, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + spellCard.getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));
            allcards.add(card);
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String nextStep = SpellAdderToDeck.AddSpellToMain(UserLogined.deck, spellCard);
                    if (nextStep.equals("done")) {
                        mainDeck.getChildren().addAll(creatMainDeck());
                        allCards.getChildren().clear();
                        allCards.getChildren().addAll(creatAllCards());
                    }
                }
            });
            i += 200;
        }

        for (TrapCardForUser trapCard : UserLogined.user.allTraps) {
            if (trapCard.getName().equals("Magic Jammer")) {
                ShopCard card = new ShopCard(i, 0, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + trapCard.getName().replace(" ", "").replace("-", "") + ".png")))));
                allcards.add(card);
                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        String nextStep = TrapAdderToDeck.AddTrapToMain(UserLogined.deck, trapCard);
                        if (nextStep.equals("done")) {
                            mainDeck.getChildren().addAll(creatMainDeck());
                            allCards.getChildren().clear();
                            allCards.getChildren().addAll(creatAllCards());
                        }
                    }
                });
            } else {

                ShopCard card = new ShopCard(i, 0, 195, 180, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + trapCard.getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));
                allcards.add(card);
                card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        String nextStep = TrapAdderToDeck.AddTrapToMain(UserLogined.deck, trapCard);
                        if (nextStep.equals("done")) {
                            mainDeck.getChildren().addAll(creatMainDeck());
                            allCards.getChildren().clear();
                            allCards.getChildren().addAll(creatAllCards());
                        }
                    }
                });
            }
            i += 200;
        }
        return allcards;
    }

    public void backToMainMenuClicked() throws Exception {
        new DeckMenu().start(stage);
    }
}
