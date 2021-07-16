package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Main;
import sample.controller.UserLogined;
import sample.model.Card.Attribute;
import sample.model.Card.MonsterCard;
import sample.model.Card.MonsterType;

public class CreatCard extends Application {
    private static Stage stage;
    @FXML
    private TextField name;
    @FXML
    private TextField attack;
    @FXML
    private TextField deffence;
    @FXML
    private TextField level;
    @FXML
    private TextArea description;
    @FXML
    private Text error;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("CreatCard.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }



    public void creatCardClicked() {
        int attack1 = Integer.parseInt(attack.getText());
        int deffence1 = Integer.parseInt(deffence.getText());
        new MonsterCard(name.getText(), description.getText(), deffence1 + attack1, Integer.parseInt(level.getText()), "added", attack1, deffence1, MonsterType.BEAST, Attribute.DARK);
        UserLogined.user.setMoney(UserLogined.user.getMoney()*(1-(deffence1+attack1)/100));
        name.clear();
        attack.clear();
        deffence.clear();
        description.clear();
        error.setText("creat card created and it costs "+deffence1+attack1+" and "+(1-(deffence1+attack1)/100)+"deacresed from "+UserLogined.user.getNickname());
    }

    public void backCLicked() throws Exception {
        new MainMenu().start(stage);
    }
}
