package sample.model.Card;

import java.util.ArrayList;

public class Card {
    protected String name;
    protected String description;
    protected int price;
    protected CardType cardType;
    protected static ArrayList<Card> allCards;
    static {
        allCards=new ArrayList<>();
    }


    public Card(String name, String description, int price, CardType cardType) {
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

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public static Card getCardByName(String name) {
        for (Card card : allCards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }
        return null;
    }

    public CardType getCardType() {
        return cardType;
    }

    public static ArrayList<Card> getAllCards() {
        return allCards;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}