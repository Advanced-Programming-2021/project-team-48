package sample.model.Card;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class SpellCard extends Card {
    private Property property;
    private Status status;

    private static ArrayList<SpellCard> allSpellCard;
    static {
        allSpellCard=new ArrayList<>();
    }


    public SpellCard(String name, String description, int price, Property property, Status status) {
        super(name, description, price, CardType.valueOf("SPELL"));
        setProperty(property);
        setStatus(status);
        allSpellCard.add(this);
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


    public static SpellCard getSpellCardByName(String name){
        for (SpellCard spellCard:allSpellCard){
            if (spellCard.name.equals(name)){
                return spellCard;
            }
        }
    return  null;
    }


    public static ArrayList<SpellCard> getAllSpellCard() {
        return allSpellCard;
    }
}
