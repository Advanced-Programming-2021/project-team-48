import Card.Card;
import Card.SpellCardForUser;
import Card.Position;

import Card.CardType;

public class Game {
    private User user1;
    private User user2;
    private int[] harif = {3, 1, 0, 2, 4};
    private int[] khodm = {4, 2, 0, 1, 3};

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
        showField(user,opponent);
        //!!!!!!!!!!!!!!!!!!!!!!!
        return false;
    }

    private void showField(User user, User opponent) {
        System.out.println(opponent.getNickname() + ":" + opponent.getLifePoint());
        int i = 0;
        for (Object ignored : opponent.handMonster) {
            i++;
        }
        for (Object ignored : opponent.handSpell) {
            i++;
        }
        for (Object ignored : opponent.handTrap) {
            i++;
        }
        System.out.println("    " + i + "   " + i + "   " + i + "   " + i + "   " + i + "   " + i);
        System.out.println(opponent.getActiveDeck().numberOfCardsInMain);
        System.out.print("    ");
        for (int a : harif) {
            if (opponent.spellZone[a].equals(null) && opponent.trapZone[a].equals(null)) {
                System.out.print("    E");
            } else {
                if (opponent.spellZone[a].equals(null)) {
                    if (opponent.trapZone[a].position.equals(Position.valueOf("HIDDEN"))) {
                        System.out.print("    H");
                    } else System.out.print("    O");
                } else {
                    if (opponent.spellZone[a].position.equals(Position.valueOf("HIDDEN"))) {
                        System.out.print("    H");
                    } else System.out.print("    O");
                }
            }
        }
        System.out.println("");
        for (int a : harif) {
            if (opponent.monsterZone[a].equals(null)) {
                System.out.print("    E");
            } else {
                if (opponent.monsterZone[a].position.equals(Position.valueOf("HIDDEN"))) {
                    System.out.print("    DH");
                }
                if (opponent.monsterZone[a].position.equals(Position.valueOf("ATTACK"))) {
                    System.out.print("    OO");
                }
                if (opponent.monsterZone[a].position.equals(Position.valueOf("DEFEND"))) {
                    System.out.print("    DO");
                }
            }
        }
        System.out.println("");
        System.out.println(opponent.NumOfGrave + "                       ");
        if (opponent.fieldZone.equals(null)) {
            System.out.println("E");
        } else System.out.println("O");

        System.out.println("");
        System.out.println("");
        System.out.println("--------------------------");
        System.out.println("");
        System.out.println("");

        if (user.fieldZone.equals(null)) {
            System.out.println("E" + "                       ");
        } else System.out.println("O" + "                       ");
        System.out.println(user.NumOfGrave);
        System.out.println();

        for (int a : harif) {
            if (user.monsterZone[a].equals(null)) {
                System.out.print("    E");
            } else {
                if (user.monsterZone[a].position.equals(Position.valueOf("HIDDEN"))) {
                    System.out.print("    DH");
                }
                if (user.monsterZone[a].position.equals(Position.valueOf("ATTACK"))) {
                    System.out.print("    OO");
                }
                if (user.monsterZone[a].position.equals(Position.valueOf("DEFEND"))) {
                    System.out.print("    DO");
                }
            }
        }

        for (int a : harif) {
            if (opponent.spellZone[a].equals(null) && opponent.trapZone[a].equals(null)) {
                System.out.print("    E");
            } else {
                if (opponent.spellZone[a].equals(null)) {
                    if (opponent.trapZone[a].position.equals(Position.valueOf("HIDDEN"))) {
                        System.out.print("    H");
                    } else System.out.print("    O");
                } else {
                    if (opponent.spellZone[a].position.equals(Position.valueOf("HIDDEN"))) {
                        System.out.print("    H");
                    } else System.out.print("    O");
                }
            }
        }
        System.out.println("                         "+user.getActiveDeck().numberOfCardsInMain);
        i = 0;
        for (Object ignored : opponent.handMonster) {
            i++;
        }
        for (Object ignored : opponent.handSpell) {
            i++;
        }
        for (Object ignored : opponent.handTrap) {
            i++;
        }
        System.out.println("    " + i + "   " + i + "   " + i + "   " + i + "   " + i + "   " + i);
        System.out.println(user.getNickname()+":"+user.getLifePoint());
    }


    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
