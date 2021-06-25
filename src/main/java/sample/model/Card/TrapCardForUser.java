package sample.model.Card;

public class TrapCardForUser<User, Deck> extends TrapCard{
    public static int numberOfCard=0;
    public int cardNumber;
    public User user;
    public Position position;
    public int address;
    public Field field;
    public boolean isInDeck;
    public Deck deck;

    public TrapCardForUser(TrapCard trapCard,User user) {
        super(trapCard.name, trapCard.description, trapCard.price, trapCard.getProperty(),trapCard.getStatus());
        this.user=user;
        cardNumber=numberOfCard;
        numberOfCard++;
    }
}
