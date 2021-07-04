package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.UserLogined;
import sample.model.Card.Card;
import sample.model.Card.MonsterForUser;
import sample.model.User;

import java.util.ArrayList;

public class Grave extends Application {
    private static Stage stage;
    @FXML
    AnchorPane show;


    @FXML
    Text nickname;

    public static User user;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Grave.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        nickname.setText(user.getNickname());
        show.getChildren().addAll(creatCards());
    }

    public ArrayList<ShopCard> creatCards() {
        ArrayList<ShopCard>allCards = new ArrayList<>();
        int x=10,y=10;
        for (MonsterForUser monsterForUser:user.monsterGrave){
            ShopCard card = new ShopCard(x, y, 362, 242, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + monsterForUser.getName().replace(" ", "").replace("-", "") + ".jpg")))));
            allCards.add(card);
            x+=242+20;
        }
        return allCards;
    }

    public void back() throws Exception {
        new GameGraphic1().start(stage);
    }
}
