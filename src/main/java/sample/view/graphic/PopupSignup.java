package sample.view.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupSignup {
    private Stage stage2;
    Stage stage1 = new Stage();
    @ FXML
    private Button OK;
    public void done(Stage stage) throws Exception {
        stage2=stage;
        Parent parent = FXMLLoader.load(getClass().getResource("SignupDonePopup.fxml"));
        Scene scene = new Scene(parent);
        stage1.setScene(scene);
        stage1.show();
    }
    public void OkClicked() throws Exception {
        stage1.close();
    }


}
