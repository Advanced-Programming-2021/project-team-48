package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.controller.DeckCreat;
import sample.controller.UserLogined;

public class AddDeck extends Application {
    private static Stage stage;
    @FXML
    private TextField deckName;
    @FXML
    private Text error;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("AddDeck.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void backToDeckMeuClicked() throws Exception {
        new DeckMenu().start(stage);
    }

    public void creatClicked() throws Exception {
        String nextStep=DeckCreat.creat(deckName.getText(), UserLogined.user);
        error.setText(nextStep);
        if (nextStep.equals("deck created successfully!")){
            new DeckMenu().start(stage);
        }
    }
}
