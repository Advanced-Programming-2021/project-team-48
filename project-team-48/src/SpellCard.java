import java.util.ArrayList;

public class SpellCard extends Card{
    private Property property;
    private static ArrayList<SpellCard> allSpellCard = new ArrayList<>();

    public SpellCard(String name, String description, int price, Property property) {
        super(name, description, price, "Spell");
        setProperty(property);
        allSpellCard.add(this);
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
