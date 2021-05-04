import java.util.ArrayList;
import java.util.Calendar;

public class Card {
    public String name;
    public String type;
    public int level;
    public int ATK;
    public int DEF;
    public String description;
    public CardType cardType;

    public enum CardType {

    }

    public int numberOfCard;
    public int price;
    public static ArrayList<Card> allCards;

    static {
        allCards = new ArrayList<>();
    }

    public boolean hasEffect;
    public Position activeEffectPostion;

    public enum Position {

    }

    public MonsterType monsterType;

    public enum MonsterType {

    }

    public enum SpellType {

    }

    public SpellType spellType;


//-----------------------------------------------------------------

    public Card(String name, int level, String type, int ATK, int DEF, String description, CardType cardType, int price) {
        setName(name);
        setATK(ATK);
        setDEF(DEF);
        setLevel(level);
        setDescription(description);
        setType(type);
        setPrice(price);
        setCardType(cardType);
        allCards.add(this);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public static Card getCardByName(String name) {
        for (Card card : allCards) {
            if (card.name.equals(name)) {
                return card;
            }
        }
        return null;
    }


}