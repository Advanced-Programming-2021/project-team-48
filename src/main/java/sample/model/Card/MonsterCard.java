package sample.model.Card;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ArrayList;

public class MonsterCard extends Card {

    public String type;
    public int level;
    public int ATK;
    public int DEF;
    public String description;
    public MonsterType monsterTYpe;
    protected Attribute attribute;
    private static ArrayList<MonsterCard> allMonsterCards;

    static {
        allMonsterCards = new ArrayList<>();
    }

    public boolean hasEffect;
    public Position activeEffectPosition;

//-----------------------------------------------------------------

    public MonsterCard(String name, String description, int price, int level, String type, int ATK, int DEF, MonsterType monsterType, Attribute attribute) {
        super(name, description, price, CardType.valueOf("MONSTER"));
        InputStream is;
        setATK(ATK);
        setDEF(DEF);
        setLevel(level);
        setType(type);
        setMonsterTYpe(monsterType);
        setAttribute(attribute);
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

    public void setMonsterTYpe(MonsterType monsterTYpe) {
        this.monsterTYpe = monsterTYpe;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public int getATK() {
        return ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public int getLevel() {
        return level;
    }

    public MonsterType getMonsterTYpe() {
        return monsterTYpe;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public String getType() {
        return type;
    }

    public Position getActiveEffectPosition() {
        return activeEffectPosition;
    }

    public static MonsterCard getMonsterCardByName(String name) {
        for (MonsterCard monsterCard : allMonsterCards) {
            if (monsterCard.name.equals(name)) {
                return monsterCard;
            }
        }
        return null;
    }

    public static ArrayList<MonsterCard> getAllMonsterCards() {
        return allMonsterCards;
    }
}

