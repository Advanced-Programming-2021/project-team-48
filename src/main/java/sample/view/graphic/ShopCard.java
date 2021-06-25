package sample.view.graphic;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ShopCard extends Rectangle {

    private int x1;
    private int y1;
    public ShopCard(int x, int y,int height,int weight, Image image){
        super(x,y,weight,height);
        this.x1=x;
        this.y1=y;
        this.setFill(new ImagePattern(image));

    }

}
