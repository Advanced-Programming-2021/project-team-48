package sample.view.graphic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.ProgramController;
import sample.controller.SortUserList;
import sample.model.User;

import java.util.ArrayList;

public class ScoreboardMenu extends Application {
    private static Stage stage;
    @FXML
    private AnchorPane innerAnchorPane;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent parent = FXMLLoader.load(getClass().getResource("ScoreboardMenu.fxml"));

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        innerAnchorPane.getChildren().addAll(creatScorePane());
    }

    public ArrayList<ScoreboardAnchorPane> creatScorePane(){
        SortUserList.Sort();
        int i=1;
        for (int j=0;j<20;j++){
            if (j==User.getListOfUsers().size())break;
            new ScoreboardAnchorPane(i,User.getListOfUsers().get(j));
            i++;
        }
        return ScoreboardAnchorPane.getAllScorePane();
    }


    public void BackClicked() throws Exception {
        new MainMenu().start(stage);
    }
}
