import java.util.ArrayList;

public class Card {
    protected String name;
    protected String description;
    protected int price;
    protected String cardType;
    protected static ArrayList<Card> allCards = new ArrayList<>();


    public Card(String name, String description, int price, String cardType) {
        setName(name);
        setDescription(description);
        setPrice(price);
        setCardType(cardType);
        allCards.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}