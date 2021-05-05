package Card;

import java.util.ArrayList;

    public class MonsterCard extends Card {

        public String type;
        public int level;
        public int ATK;
        public int DEF;
        public String description;
        public MonsterType cardType;
        private Attribute attribute;

        public boolean hasEffect;
        public Position activeEffectPosition;

//-----------------------------------------------------------------

        public MonsterCard(String name, String description, int price, int level, String type, int ATK, int DEF, MonsterType monsterType) {
            super(name,description,price,"MONSTER");
            setATK(ATK);
            setDEF(DEF);
            setLevel(level);
            setType(type);
            setCardType(monsterType);

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



    }

