package Card;

public class TrapCardForUser<User, Deck> extends SpellCard{
    public User user;
    public Position position;
    public int address;
    public Field field;
    public boolean isInDeck;
    public Deck deck;

    public TrapCardForUser(TrapCard trapCard,User user) {
        super(trapCard.name, trapCard.description, trapCard.price, trapCard.property);
        this.user=user;
    }
}
