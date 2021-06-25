package sample.view.graphic;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.model.User;

import java.util.ArrayList;

public class ScoreboardAnchorPane extends AnchorPane {
    private static ArrayList<ScoreboardAnchorPane> allScorePane=new ArrayList<>();
    public ScoreboardAnchorPane(int number, User user){



        Label label=new Label();
        label.setTranslateX(15);
        label.setTranslateY(15);
        label.setText(number+"");
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Cambria", 32));
        this.getChildren().add(label);


        ImageView imageView=new ImageView();
        imageView.setTranslateX(60);
        imageView.setTranslateY(0);
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setImage(user.getAvatar());
        this.getChildren().add(imageView);

        Label nickname=new Label();
        nickname.setText(user.getNickname());
        nickname.setTranslateX(160);
        nickname.setTranslateY(15);
        nickname.setFont(new Font("Cambria", 32));
        nickname.setTextFill(Color.web("#83d0c9",0.8));
        this.getChildren().add(nickname);

        this.setPrefWidth(1500);
        this.setPrefHeight(80);
        this.setTranslateX(0);
        this.setTranslateY((number-1)*90+15);
        this.setStyle("-fx-background-color: #451e3e");

        allScorePane.add(this);
    }

    public static ArrayList<ScoreboardAnchorPane> getAllScorePane() {
        return allScorePane;
    }
}

