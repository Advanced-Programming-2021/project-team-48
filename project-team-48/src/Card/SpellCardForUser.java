package Card;

public class SpellCardForUser<User> extends SpellCard{
    public User user;
    public Card.MonsterCard.Position position;
    public int address;
    public Field field;

    public SpellCardForUser(SpellCard spellCard,User user) {
        super(spellCard.name, spellCard.description, spellCard.price, spellCard.property);
        this.user=user;
    }
}
