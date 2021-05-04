import java.util.ArrayList;

public class MonsterCard extends Card{

    private String type;
    private int level;
    private int ATK;
    private int DEF;
    private String description;
    private MonsterType cardType;
    private Attribute attribute;
    public static ArrayList<MonsterCard> allMonsterCards = new ArrayList<>();

    public boolean hasEffect;
    public Position activeEffectPosition;

    public enum Position {

    }

//-----------------------------------------------------------------

    public MonsterCard(String name, String description, int price, int level, String type, int ATK, int DEF, MonsterType monsterType) {
        super(name,description,price,"MONSTER");
        setATK(ATK);
        setDEF(DEF);
        setLevel(level);
        setType(type);
        setCardType(monsterType);
        allMonsterCards.add(this);
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

    public void setType(String type) {
        this.type = type;
    }

    public void setCardType(MonsterType cardType) {
        this.cardType = cardType;
    }

    public MonsterCard getCardByName(String name) {
        for (MonsterCard card : allMonsterCards) {
            if (super.name.equals(name)) {
                return card;
            }
        }
        return null;
    }
}