import java.lang.reflect.Field;

class CardForUser extends Card {
        public User user;
        public Position position;
        public  int address;
        public enum Field{
       GRAVE ,HAND,GAME,DECK
        }
        public Field field;

        public CardForUser(Card card,User user) {
            super(card.name, card.level, card.type, card.ATK, card.DEF, card.description, card.cardType, card.price);
            this.user=user;
        }

    }

