import Card.Card;

import java.util.ArrayList;

public class Game {
    private User user1;
    private User user2;
    private ArrayList<MonsterForUser> monsterGrave;
    static {

    }
    public ArrayList<SpellCardForUser> spellGrave;
    public Game(User user1,User user2){
        setUser1(user1);
        setUser2(user2);
        run();
    }
    public static void run(){

    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
