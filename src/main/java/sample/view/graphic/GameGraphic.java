package sample.view.graphic;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.Game.GameController;
import sample.controller.UserLogined;
import sample.model.Card.MonsterForUser;

import java.util.ArrayList;

public class GameGraphic extends Application {
    private static Stage stage;
    @FXML
    private Text error;
    @FXML
    public Text phase;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        anchorPane = FXMLLoader.load(getClass().getResource("User1.fxml"));
        Scene scene = new Scene(anchorPane);
        anchorPane.getChildren().addAll(creatUserHand());
        stage.setScene(scene);
        stage.show();

        Parent parent1 = FXMLLoader.load(getClass().getResource("User2.fxml"));
        Scene scene1 = new Scene(parent1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    }

    public void initialize() {
        GameController gameController = new GameController();
        gameController.run();
    }

    public ArrayList<ShopCard> creatUserHand() {
        ArrayList<ShopCard> allCards = new ArrayList<>();
        int x = 684;
        for (MonsterForUser monsterForUser : UserLogined.user.handMonster) {
            ShopCard card = new ShopCard(x, 600, 150, 200, new Image(String.valueOf((getClass().getResource("Assets/Cards/Monsters/" + monsterForUser.getName().replace(" ", "").replace("-", "") + ".jpg")))));
            x -= 103;
            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    card.setTranslateX(375);
                    card.setTranslateY(222);
                    card.xProperty().set(375);
                    card.yProperty().set(380);

                    Button summon = new Button();
                    summon.setText("Summon");
                    summon.setTranslateX(675);
                    summon.setTranslateY(315);

                    Button set = new Button();
                    set.setText("Set");
                    set.setTranslateX(675);
                    set.setTranslateY(350);
                }
            });
        allCards.add(card);
        }
        return allCards;
    }

    public void Back() throws Exception {
        new MainMenu().start(stage);
    }
}
