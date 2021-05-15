import Card.Position;
import Card.*;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private User user1;
    private User user2;
    private int numInHand = 1;
    private boolean dasteAval = false;
    private int[] harif = {3, 1, 0, 2, 4};
    private int[] khodm = {4, 2, 0, 1, 3};
    private int[] checkIfEmpty = {2, 3, 1, 4, 0};

    public Game(User user1, User user2) {
        setUser1(user1);
        setUser2(user2);
        user1.setLifePoint(8000);
        user2.setLifePoint(8000);
        run();
    }


    public void run() {
        boolean bool = true;
        while (bool) {
            bool = play(user1, user2);
            if (bool) {
                bool = play(user2, user1);
            }
        }
        user1.setActiveDeck(user1.getDeckByName(user1.getActiveDeck().getName()));
        user2.setActiveDeck(user2.getDeckByName(user2.getActiveDeck().getName()));
    }

    private boolean play(User user, User opponent) {
        Scanner scanner = new Scanner(System.in);

        showField(user, opponent);
        if (user.getActiveDeck().numberOfCardsInMain == 0) {
            return false;
        }
        if (!dasteAval) {
            dasteAval = true;
        } else {
            drawPhase(user);
        }
        standbyPhase(user, opponent);
        mainPhase1(user, opponent);
        battlePhase(user, opponent);
        endPhase(user, opponent);

        //!!!!!!!!!!!!!!!!!!!!!!!
        return false;
    }


    private void mainPhase1(User user, User opponent) {
        System.out.println("phase: Main Phase 1");
        String input = "";
        while (true) {
            input = scanner.nextLine();
            boolean checker = false;
            Pattern pattern = Pattern.compile("select --hand ([\\d]+)");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String temp = matcher.group(1);
                int address = Integer.parseInt(temp);
                boolean isFind = false;
                for (MonsterForUser monsterForUser : user.handMonster) {
                    if (monsterForUser.address == address) {
                        isFind = true;
                        System.out.println("card selected");
                        monsterSelectedFromHand(monsterForUser, user);
                        break;
                    }
                }
                if (!isFind) {
                    for (SpellCardForUser spellCardForUser : user.handSpell) {
                        if (spellCardForUser.address == address) {
                            isFind = true;
                            System.out.println("card selected");
                            spellSelectedFromHand(spellCardForUser, user);
                            break;
                        }
                    }

                }
            }
        }
    }


    private void battlePhase(User user, User opponent) {
        System.out.println("phase: End Phase");
    }

    private void endPhase(User user, User opponent) {
        System.out.println("phase: End Phase");


        System.out.println("its " + opponent.getNickname() + "’s turn");
    }


    private void monsterSelectedFromHand(MonsterForUser monsterForUser, User user) {
        String input = "";
        while (!input.equals("select -d")) {
            input = scanner.nextLine();
            boolean checker = false;
            Pattern pattern = Pattern.compile("summon");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                summon(monsterForUser, user);
            }
        }
    }


    private void summon(MonsterForUser monsterForUser, User user) {
        if (monsterForUser.level <= 4) {
            boolean hasEmpty=false;
            for (int a : checkIfEmpty) {
                if (user.monsterZone[a]==null) {
                    hasEmpty=true;
                    monsterForUser.field=Field.valueOf("GAME");
                    monsterForUser.address=a;
                    monsterForUser.position=Position.valueOf("ATTACK");
                    user.monsterZone[a]=monsterForUser;
                    user.handMonster.remove(monsterForUser);
                }
            }
        }
        else {

        }
    }


    private void standbyPhase(User user, User opponent) {
        System.out.println("phase: standby phase");
    }

    private void drawPhase(User user) {
        System.out.println("phase: draw phase");
        String input = "";
        Random random = new Random();
        int r = random.nextInt(3);
        if (r == 0) {
            Collections.shuffle(user.getActiveDeck().allMonsterForUserMain);
            user.handMonster.add(user.getActiveDeck().allMonsterForUserMain.get(0));
            user.getActiveDeck().allMonsterForUserMain.get(0).field = Field.valueOf("HAND");
            user.getActiveDeck().allMonsterForUserMain.get(0).address = numInHand;
            numInHand++;
            user.getActiveDeck().numberOfCardsInMain--;
            System.out.println("new card added to the hand : " + user.getActiveDeck().allMonsterForUserMain.get(0).getName());
            user.getActiveDeck().allMonsterForUserMain.remove(0);

        }
        if (r == 1) {
            Collections.shuffle(user.getActiveDeck().allSpellCardsForUserSide);
            user.handSpell.add(user.getActiveDeck().allSpellCardsForUserMain.get(0));
            user.getActiveDeck().allSpellCardsForUserMain.get(0).field = Field.valueOf("HAND");
            user.getActiveDeck().allSpellCardsForUserMain.get(0).address = numInHand;
            numInHand++;
            user.getActiveDeck().numberOfCardsInMain--;
            System.out.println("new card added to the hand : " + user.getActiveDeck().allSpellCardsForUserMain.get(0).getName());
            user.getActiveDeck().allSpellCardsForUserMain.remove(0);
        }
        if (r == 2) {
            Collections.shuffle(user.getActiveDeck().allTrapCardsForUserMain);
            user.handTrap.add(user.getActiveDeck().allTrapCardsForUserMain.get(0));
            user.getActiveDeck().allTrapCardsForUserMain.get(0).field = Field.valueOf("HAND");
            user.getActiveDeck().allTrapCardsForUserMain.get(0).address = numInHand;
            numInHand++;
            user.getActiveDeck().numberOfCardsInMain--;
            System.out.println("new card added to the hand : " + user.getActiveDeck().allTrapCardsForUserMain.get(0).getName());
            user.getActiveDeck().allTrapCardsForUserSide.remove(0);
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        while (!input.equals("next phase")) {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            boolean checker = false;
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            Pattern pattern = Pattern.compile("");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
            }


        }
    }


//-------------------------------------------------------------------------------------------------------


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
        System.out.println("                         " + user.getActiveDeck().numberOfCardsInMain);
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
        System.out.println(user.getNickname() + ":" + user.getLifePoint());
    }


    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
