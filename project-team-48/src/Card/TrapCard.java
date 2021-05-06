package Card;

import java.util.ArrayList;
import java.util.TreeMap;

public class TrapCard extends Card {
    private Property property;
    private Status status;
    private static ArrayList<TrapCard> allTrapCard ;
    static {
        allTrapCard=new ArrayList<>();
    }

    public TrapCard(String name, String description, int price, Property property) {
        super(name, description, price, "TRAP");
        setProperty(property);
        allTrapCard.add(this);
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Property getProperty() {
        return property;
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