import Card.Card;
import Card.MonsterForUser;
import Card.SpellCardForUser;
import Card.TrapCardForUser;

import java.util.ArrayList;
import java.util.HashMap;
import Card.CardType;
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
        user1.setActiveDeck(user1.getDeckByName(user1.getActiveDeck().getName()));
        user2.setActiveDeck(user2.getDeckByName(user2.getActiveDeck().getName()));
    }

    private boolean play(String username, String opponentUsername) {
        User user = User.getUserByUsername(username);
        User opponent = User.getUserByUsername(opponentUsername);
        System.out.println(opponent.getNickname()+":"+opponent.getLifePoint());
        int i=0;
        for (Object object:opponent.hand){
            i++;
        }
        System.out.println("    "+i+"   "+i+"   "+i+"   "+i+"   "+i+"   "+i);
        System.out.println(opponent.getActiveDeck().numberOfCardsInMain);
        System.out.print("    ");
        if (opponent.spellAndTrapZone[3].equals(null)){
            System.out.print("E");
        }else {
            tashkhisHalateSpellVaTrap(opponent.spellAndTrapZone[3]);
        }




     //!!!!!!!!!!!!!!!!!!!!!!!
    return false;}
private String tashkhisHalateSpellVaTrap(Card card){

        if (card.getCardType().equals(CardType.valueOf("SPELL"))){
          SpellCardForUser.getSpellCardByName(card.getName());

        }
        if (card.getCardType().equals(CardType.valueOf("TRAP"))){

        }

}
    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
