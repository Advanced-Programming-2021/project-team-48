import Card.Card;
import Card.MonsterForUser;
import Card.SpellCardForUser;
import Card.TrapCardForUser;

import java.util.*;

import java.util.ArrayList;

public class Game {
    private User user1;
    private User user2;

    public Game(User user1, User user2) {
        setUser1(user1);
        setUser2(user2);
        user1.setLifePoint(3000);
        user2.setLifePoint(3000);
        run();
    }


    public void run() {
        boolean bool = true;
        while (bool) {
            bool = play(user1.getUsername(), user2.getUsername());
            if (bool) {
                bool = play(user2.getUsername(), user1.getUsername());
            }
        }
    }

    private boolean play(String username, String opponentUsername) {
        User user = User.getUserByUsername(username);
        User opponent = User.getUserByUsername(opponentUsername);
        System.out.println(opponent.getNickname()+":"+opponent.getLifePoint());
        for (Object object:)
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
