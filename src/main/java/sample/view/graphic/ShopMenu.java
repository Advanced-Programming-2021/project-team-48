package sample.view.graphic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.BuyCard;
import sample.controller.HowManyCard;
import sample.controller.SortCards;
import sample.controller.UserLogined;
import sample.model.Card.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ShopMenu extends Application {
    private static Stage stage;
    @FXML
    private AnchorPane innerAnchorPane;
    @FXML
    private Text money;
    @FXML
    private Text username;
    private ArrayList<Text> allHowMany=new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("ShopMenu.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        innerAnchorPane.getChildren().addAll(creatCard());

        int i = 10, j = 520;
        int b = 540, c = 560;
        ArrayList<String> allCards = new ArrayList<>();
        allCards.addAll(SortCards.MonsterSort());
        allCards.addAll(SortCards.SpellSort());
        allCards.addAll(SortCards.TrapSort());
        for (String name : allCards) {
            if (i == 1155 + 385 + 10) {
                i = 10;
                j += 100 + 460;
                b += 100 + 460;
            }
            Text money = new Text();
            money.setText("Price : " + Card.getCardByName(name).getPrice());
            money.setX(i);
            money.setY(j);
            innerAnchorPane.getChildren().add(money);

            Text howMany = new Text();
            howMany.setText("You Have " + HowManyCard.YouHave(UserLogined.user, name) + " Of This Card");
            howMany.setId("numberOf" + name);
            allHowMany.add(howMany);
            howMany.setX(i);
            howMany.setY(b);
            innerAnchorPane.getChildren().add(howMany);
            i += 385;
        }

        money.setText(UserLogined.user.getMoney() + "");
        username.setText(UserLogined.user.getUsername());
    }


    public ArrayList<ShopCard> creatCard() {
        ArrayList<String> allMonsters = SortCards.MonsterSort();
        ArrayList<String> allSpell = SortCards.SpellSort();
        ArrayList<String> allTrap = SortCards.TrapSort();
        ArrayList<ShopCard> allCards = new ArrayList<>();

        int i = 0, j = 35, c = 560;
        for (String monsterCard : allMonsters) {
            if (i == 1155 + 385) {
                i = 0;
                j += 100 + 460;
                c += 100 + 460;
            }
           allCards.add( new ShopCard(i, j,460,325, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + monsterCard.replace(" ", "").replace("-", "") + ".jpg"))))));
            Button button = new Button();
            button.setText("Buy");
            button.setId(monsterCard);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    BuyClicked(monsterCard);
                }
            });
            button.setLayoutX(i + 10);
            button.setLayoutY(c);
            innerAnchorPane.getChildren().add(button);
            i += 385;
        }




        for (String spellCard : allSpell) {
            if (i == 1155 + 385) {
                i = 0;
                j += 100 + 460;
                c += 100 + 460;
            }
            allCards.add(new ShopCard(i, j,460,325, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + spellCard.replace(" ", "").replace("-", "").replace("'", "") + ".jpg"))))));
            Button button = new Button();
            button.setText("Buy");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    BuyClicked(spellCard);
                }
            });
            button.setLayoutX(i + 10);
            button.setLayoutY(c);
            innerAnchorPane.getChildren().add(button);
            i += 385;
        }

        for (String trapCard : allTrap) {
            if (i == 1155 + 385) {
                i = 0;
                j += 100 + 460;
                c += 100 + 460;
            }
            if (trapCard.equals("Magic Jammer")) {
                allCards.add(new ShopCard(i, j,460,325, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + trapCard.replace(" ", "").replace("-", "") + ".png"))))));
                Button button = new Button();
                button.setText("Buy");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        BuyClicked(trapCard);
                    }
                });
                button.setLayoutX(i + 10);
                button.setLayoutY(c);
                innerAnchorPane.getChildren().add(button);
            } else {
                allCards.add(new ShopCard(i, j,460,325, new Image(String.valueOf((getClass().getResource("Assets/Cards/SpellTrap/" + trapCard.replace(" ", "").replace("-", "").replace("'", "") + ".jpg"))))));
                Button button = new Button();
                button.setText("Buy");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        BuyClicked(trapCard);
                    }
                });
                button.setLayoutX(i + 10);
                button.setLayoutY(c);
                innerAnchorPane.getChildren().add(button);
            }
            i += 385;
        }
        return allCards;
    }


    public void backToMainMenuClicked() throws Exception {
        new MainMenu().start(stage);
    }

    public void BuyClicked(String cardName) {
        String nextStep=BuyCard.BuyCard(cardName,UserLogined.user);
        if (nextStep.equals("done")){
            money.setText(UserLogined.user.getMoney()+"");
            for (Text newHowMany:allHowMany){
                if (newHowMany.getId().equals("numberOf" + cardName)){
                    newHowMany.setText("You Have " + HowManyCard.YouHave(UserLogined.user, cardName) + " Of This Card");
                    break;
                }
            }
        }
    }
}