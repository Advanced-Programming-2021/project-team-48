package sample.model.Card;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class TrapCard extends Card {
    private Property property;
    private Status status;
    private static ArrayList<TrapCard> allTrapCard ;
    static {
        allTrapCard=new ArrayList<>();
    }

    public TrapCard(String name, String description, int price, Property property, Status status) {
        super(name, description, price, CardType.valueOf("TRAP"));
        setProperty(property);
        setStatus(status);
        allTrapCard.add(this);
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Property getProperty() {
        return property;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public static TrapCard getTrapCardByName(String name){
        for (TrapCard trapCard:allTrapCard){
            if (trapCard.name.equals(name)){
                return trapCard;
            }
        }
    return null;
    }

    public static ArrayList<TrapCard> getAllTrapCard() {
        return allTrapCard;
    }
}