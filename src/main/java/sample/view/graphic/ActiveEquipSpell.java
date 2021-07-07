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

public class ActiveEquipSpell extends Application {
    private static Stage stage;

    @FXML
    private AnchorPane showPart;
    @FXML
    private AnchorPane cards;
    @FXML
    Text error;

    public static SpellCardForUser spellToActive;
    public static User user = UserLogined.user;
    public static User opponent = UserLogined.opponent;

    public MonsterForUser selectedMonster;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("ActiveEquipSpell.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        if (GameGraphic1.showCardSpellHand != null) spellToActive = GameGraphic1.showCardSpellHand;
        if (GameGraphic1.showSpellFromZone != null) spellToActive = GameGraphic1.showSpellFromZone;
        creatBoard();
    }

    public void creatBoard() {

        ImageView card = new ImageView();
        card.setImage(new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + spellToActive.getName().replace(" ", "").replace("-", "").replace("'", "") + ".jpg")))));
        card.setTranslateX(68);
        card.setTranslateY(48);
        card.setFitWidth(275);
        card.setFitHeight(407);
        showPart.getChildren().add(card);


        ImageView arrow = new ImageView(new Image(String.valueOf((getClass().getResource("Assets/580b57fcd9996e24bc43c43d.png")))));
        arrow.setTranslateX(367);
        arrow.setTranslateY(116);
        arrow.setFitWidth(178);
        arrow.setFitHeight(257);
        showPart.getChildren().add(arrow);


        Button active = new Button();
        active.setText("Equip");
        active.setTranslateX(366);
        active.setTranslateY(309);
        active.setPrefHeight(26);
        active.setPrefWidth(178);
        active.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    activeClicked();
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
    }

    public ArrayList<ShopCard> creatOpponent() {
        ArrayList<ShopCard> allCards = new ArrayList<>();
        int x = 750;
        for (int j = 0; j < user.monsterZone.length; j++) {
            ShopCard card;
            if (user.monsterZone[j] != null) {
                boolean canContinue = true;
                if (spellToActive.getName().equals("Sword Of dark destruction")) {
                    if (!(user.monsterZone[j].monsterType.equals(MonsterType.SPELLCASTER) || user.monsterZone[j].monsterType.equals(MonsterType.FIEND))) {
                        canContinue = false;
                    }
                }
                if (canContinue) {
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
        }
        return allCards;
    }

    public void activeClicked() throws Exception {
        if (selectedMonster == null) {
            error.setText("select a card");
        } else {

            if (selectedMonster.spell == null) {

                boolean isEmpty = true;
                if (user.spellZone[selectedMonster.address] != null) {
                    isEmpty = false;

                    for (int a = 0; a < 5; a++) {
                        if (user.spellZone[a] == null && user.trapZone[a] == null) {
                            if (a != selectedMonster.address) {

                                isEmpty = true;
                                user.spellZone[a] = user.spellZone[selectedMonster.address];
                                user.spellZone[selectedMonster.address] = null;
                            }
                        }
                    }
                }
                if (isEmpty) {
                    selectedMonster.spell = spellToActive;
                    user.spellZone[selectedMonster.address] = spellToActive;
                    user.spellZone[selectedMonster.address].position = Position.ATTACK;
                    user.spellZone[selectedMonster.address].address = selectedMonster.address;
                    user.spellZone[selectedMonster.address].field = Field.valueOf("GAME");
                    user.handSpell.remove(spellToActive);

                    if (spellToActive.getName().equals("Sword Of dark destruction")) {
                        if (selectedMonster.monsterType.equals(MonsterType.FIEND) || selectedMonster.monsterType.equals(MonsterType.SPELLCASTER)) {
                            selectedMonster.setATK(selectedMonster.getATK() + 400);
                            selectedMonster.setDEF(selectedMonster.getDEF() - 200);
                        }
                    } else if (spellToActive.getName().equals("Black Pendant")) {
                        selectedMonster.setATK(selectedMonster.getATK() + 500);
                    } else if (spellToActive.getName().equals("United We Stand")) {
                        int x = 0;
                        for (int a = 0; a < 5; a++) {
                            if (user.monsterZone[a] != null) {
                                if (!user.monsterZone[a].position.equals(Position.HIDDEN)) {
                                    x++;
                                }
                            }
                        }
                        selectedMonster.setATK(selectedMonster.getATK() + (x * 800));
                    }
                    back();

                }
            }
        }
    }

    public void back() throws Exception {
        selectedMonster = null;
        spellToActive = null;
        new GameGraphic1().start(stage);
    }

}
