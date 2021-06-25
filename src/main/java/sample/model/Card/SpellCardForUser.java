package sample.model.Card;

public class SpellCardForUser<User, Deck> extends SpellCard{
    public static int numberOfCard=0;
    public int cardNumber;
    public User user;
    public Position position;
    public int address;
    public Field field;
    public boolean isInDeck;
    public Deck deck;
    public SpellCardForUser(SpellCard spellCard,User user) {
        super(spellCard.name, spellCard.description, spellCard.price, spellCard.getProperty(),spellCard.getStatus());
        this.user=user;
        cardNumber=numberOfCard;
        numberOfCard++;
    }
}
