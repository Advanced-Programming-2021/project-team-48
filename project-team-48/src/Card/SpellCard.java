package Card;

import java.util.ArrayList;

public class SpellCard extends Card {
    public Property property;
    private static ArrayList<SpellCard> allSpellCard;
    static {
        allSpellCard=new ArrayList<>();
    }

    public SpellCard(String name, String description, int price, Property property) {
        super(name, description, price, "Spell");
        setProperty(property);
        allSpellCard.add(this);
    }

    public void setProperty(Property property) {
        this.property = property;
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
