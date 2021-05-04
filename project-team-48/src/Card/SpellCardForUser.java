package Card;

public class SpellCardForUser<User, Deck> extends SpellCard{
    public User user;
    public Position position;
    public int address;
    public Field field;
    public boolean isInDeck;
    public Deck deck;
    public SpellCardForUser(SpellCard spellCard,User user) {
        super(spellCard.name, spellCard.description, spellCard.price, spellCard.property);
        this.user=user;
    }
}
