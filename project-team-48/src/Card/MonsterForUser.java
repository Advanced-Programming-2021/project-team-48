package Card;

import Card.MonsterCard;

class MonsterForUser<User> extends MonsterCard {
    public User user;
    public Position position;
    public int address;

    public enum Field {
        GRAVE, HAND, GAME, DECK
    }

    public Field field;

    public MonsterForUser(MonsterCard monsterCard, User user) {
        super(monsterCard.name, monsterCard.description, monsterCard.price, monsterCard.level, monsterCard.type, monsterCard.ATK, monsterCard.DEF, monsterCard.cardType);
        this.user = user;
    }

}
