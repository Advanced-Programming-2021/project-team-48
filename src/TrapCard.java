import java.util.ArrayList;

public class TrapCard extends Card{
    private Property property;
    private static ArrayList<TrapCard> allTrapCard = new ArrayList<>();

    public TrapCard(String name, String description, int price, Property property) {
        super(name, description, price, "TRAP");
        setProperty(property);
        allTrapCard.add(this);
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}