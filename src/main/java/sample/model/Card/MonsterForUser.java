package sample.model.Card;


public class MonsterForUser<User, Deck> extends MonsterCard {
    public static int numberOfCard=0;
    public int cardNumber;
    public User user;
    public Position position;
    public int address;
    public Field field;
    public boolean isInDeck;
    public Deck deck;
    public boolean canAttack=false;
    public boolean canChange=true;
    public  SpellCardForUser spell;

    public MonsterForUser(MonsterCard monsterCard, User user) {
        super(monsterCard.name, monsterCard.description, monsterCard.price, monsterCard.level, monsterCard.type, monsterCard.ATK, monsterCard.DEF, monsterCard.monsterType, monsterCard.attribute);
        this.user = user;
        cardNumber=numberOfCard;
        numberOfCard++;
    }


    public void setField(Field field) {
        this.field = field;
    }

    public Position getPosition() {
        return position;
    }

}
