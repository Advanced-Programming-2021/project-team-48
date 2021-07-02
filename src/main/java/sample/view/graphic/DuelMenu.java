package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.*;
import sample.controller.Game.GameController;
import sample.model.Card.CartReader;
import sample.model.Card.MonsterForUser;
import sample.model.Deck;
import sample.model.User;

public class DuelMenu extends Application {
    private static Stage stage;

    @FXML
    private TextField opponent;
    @FXML
    private Text error;
    @FXML
    private CheckBox one;
    @FXML
    private CheckBox three;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("DuelMenu.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {

        //<temp>
        new CartReader();

        //<user2>
        User naweed = new User("", "ahmadvand", "321");
        UserLogined.user = naweed;
        BuyCard.BuyCard("Battle Ox", naweed);
        BuyCard.BuyCard("Battle Ox", naweed);
        BuyCard.BuyCard("Battle Ox", naweed);
        BuyCard.BuyCard("Axe Raider", naweed);
        BuyCard.BuyCard("Axe Raider", naweed);
        BuyCard.BuyCard("Axe Raider", naweed);
        BuyCard.BuyCard("Yomi Ship", naweed);
        BuyCard.BuyCard("Yomi Ship", naweed);
        BuyCard.BuyCard("Yomi Ship", naweed);
        BuyCard.BuyCard("Horn Imp", naweed);
        BuyCard.BuyCard("Horn Imp", naweed);
        BuyCard.BuyCard("Horn Imp", naweed);
        BuyCard.BuyCard("Silver Fang", naweed);
        BuyCard.BuyCard("Silver Fang", naweed);
        BuyCard.BuyCard("Silver Fang", naweed);
        BuyCard.BuyCard("Suijin", naweed);
        BuyCard.BuyCard("Suijin", naweed);
        BuyCard.BuyCard("Suijin", naweed);
        BuyCard.BuyCard("Fireyarou", naweed);
        BuyCard.BuyCard("Fireyarou", naweed);
        BuyCard.BuyCard("Fireyarou", naweed);
        BuyCard.BuyCard("Curtain of the dark ones", naweed);
        BuyCard.BuyCard("Curtain of the dark ones", naweed);
        BuyCard.BuyCard("Curtain of the dark ones", naweed);
        BuyCard.BuyCard("Feral Imp", naweed);
        BuyCard.BuyCard("Feral Imp", naweed);
        BuyCard.BuyCard("Feral Imp", naweed);
        BuyCard.BuyCard("Dark magician", naweed);
        BuyCard.BuyCard("Dark magician", naweed);
        BuyCard.BuyCard("Dark magician", naweed);
        BuyCard.BuyCard("Wattkid", naweed);
        BuyCard.BuyCard("Wattkid", naweed);
        BuyCard.BuyCard("Wattkid", naweed);
        BuyCard.BuyCard("Baby dragon", naweed);
        BuyCard.BuyCard("Baby dragon", naweed);
        BuyCard.BuyCard("Baby dragon", naweed);
        BuyCard.BuyCard("Hero of the east", naweed);
        BuyCard.BuyCard("Hero of the east", naweed);
        BuyCard.BuyCard("Hero of the east", naweed);
        BuyCard.BuyCard("Battle warrior", naweed);
        BuyCard.BuyCard("Battle warrior", naweed);
        BuyCard.BuyCard("Battle warrior", naweed);

        Deck deck1 = new Deck(naweed, "naweeds deck");

        for (MonsterForUser monsterForUser : naweed.allMonsters) {
            MonsterAdderToDeck.AddMonsterToMain(deck1, monsterForUser);
        }

        UserLogined.user.hasActiveDeck = true;
        UserLogined.user.setActiveDeck(deck1);
        UserLogined.opponent=naweed;
        //</user2>

        //<user1>
        User arian = new User("arian", "qhzvini", "123");
        UserLogined.user = arian;
        BuyCard.BuyCard("Battle Ox", arian);
        BuyCard.BuyCard("Battle Ox", arian);
        BuyCard.BuyCard("Battle Ox", arian);
        BuyCard.BuyCard("Axe Raider", arian);
        BuyCard.BuyCard("Axe Raider", arian);
        BuyCard.BuyCard("Axe Raider", arian);
        BuyCard.BuyCard("Yomi Ship", arian);
        BuyCard.BuyCard("Yomi Ship", arian);
        BuyCard.BuyCard("Yomi Ship", arian);
        BuyCard.BuyCard("Horn Imp", arian);
        BuyCard.BuyCard("Horn Imp", arian);
        BuyCard.BuyCard("Horn Imp", arian);
        BuyCard.BuyCard("Silver Fang", arian);
        BuyCard.BuyCard("Silver Fang", arian);
        BuyCard.BuyCard("Silver Fang", arian);
        BuyCard.BuyCard("Suijin", arian);
        BuyCard.BuyCard("Suijin", arian);
        BuyCard.BuyCard("Suijin", arian);
        BuyCard.BuyCard("Fireyarou", arian);
        BuyCard.BuyCard("Fireyarou", arian);
        BuyCard.BuyCard("Fireyarou", arian);
        BuyCard.BuyCard("Curtain of the dark ones", arian);
        BuyCard.BuyCard("Curtain of the dark ones", arian);
        BuyCard.BuyCard("Curtain of the dark ones", arian);
        BuyCard.BuyCard("Feral Imp", arian);
        BuyCard.BuyCard("Feral Imp", arian);
        BuyCard.BuyCard("Feral Imp", arian);
        BuyCard.BuyCard("Dark magician", arian);
        BuyCard.BuyCard("Dark magician", arian);
        BuyCard.BuyCard("Dark magician", arian);
        BuyCard.BuyCard("Wattkid", arian);
        BuyCard.BuyCard("Wattkid", arian);
        BuyCard.BuyCard("Wattkid", arian);
        BuyCard.BuyCard("Baby dragon", arian);
        BuyCard.BuyCard("Baby dragon", arian);
        BuyCard.BuyCard("Baby dragon", arian);
        BuyCard.BuyCard("Hero of the east", arian);
        BuyCard.BuyCard("Hero of the east", arian);
        BuyCard.BuyCard("Hero of the east", arian);
        BuyCard.BuyCard("Battle warrior", arian);
        BuyCard.BuyCard("Battle warrior", arian);
        BuyCard.BuyCard("Battle warrior", arian);

        Deck deck = new Deck(arian, "arians deck");

        for (MonsterForUser monsterForUser : arian.allMonsters) {
            MonsterAdderToDeck.AddMonsterToMain(deck, monsterForUser);
        }

       /* arian.handMonster.add(arian.allMonsters.get(0));
        arian.handMonster.add(arian.allMonsters.get(3));
        arian.handMonster.add(arian.allMonsters.get(6));
        arian.handMonster.add(arian.allMonsters.get(9));
        arian.handMonster.add(arian.allMonsters.get(12));
        arian.handMonster.add(arian.allMonsters.get(15));

        naweed.handMonster.add(naweed.allMonsters.get(0));
        naweed.handMonster.add(naweed.allMonsters.get(1));
        naweed.handMonster.add(naweed.allMonsters.get(2));
        naweed.handMonster.add(naweed.allMonsters.get(3));
        naweed.handMonster.add(naweed.allMonsters.get(4));


        */


        UserLogined.user.hasActiveDeck = true;
        UserLogined.user.setActiveDeck(deck);






        //</user1>
        //</temp>


        one.setSelected(true);

    }

    public void setThree() {
        one.setSelected(false);
        three.setSelected(true);
    }

    public void setOne() {
        one.setSelected(true);
        three.setSelected(false);
    }

    public void StartClicked() throws Exception {
        int rounds = 0;
        if (one.isSelected()) rounds = 1;
        if (three.isSelected()) rounds = 3;
        String nextStep = StartGameController.Game(opponent.getText(), UserLogined.user, rounds);
        error.setText(nextStep);
        if (nextStep.equals("done1")) {
            GameGraphic1.deckTempUser.copyDeck(UserLogined.user.getActiveDeck());
            GameGraphic1.deckTempOpponent.copyDeck(UserLogined.opponent.getActiveDeck());
            GameGraphic1 gameGraphic1=new GameGraphic1();
            gameGraphic1.start(stage);
            //GameGraphic2 gameGraphic2=new GameGraphic2();
            //gameGraphic2.start(stage);
           // new GameGraphic1().start(stage);
            // GameController gameController = new GameController(UserLogined.user,UserLogined.opponent);
            //GameGraphic1.gameController=gameController;
            //GameController.gameGraphic1=gameGraphic1;

        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void Back() throws Exception {
        new MainMenu().start(stage);
    }
}
