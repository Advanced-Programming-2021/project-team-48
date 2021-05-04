package Card;

public class TrapCardForUser<User> extends SpellCard{
    public User user;
    public Card.MonsterCard.Position position;
    public int address;
    public Field field;

    public TrapCardForUser(TrapCard trapCard,User user) {
        super(trapCard.name, trapCard.description, trapCard.price, trapCard.property);
        this.user=user;
    }
}
