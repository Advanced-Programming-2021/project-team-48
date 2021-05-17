import java.nio.file.Path;
import java.util.TreeMap;

import Card.Position;
import Card.*;
import Card.Field;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private User user1;
    private User user2;
    private int numInHand = 1;
    private boolean dasteAval = false;
    public static boolean hasSummonInThisRound = false;
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
            drawPhase(user, opponent);
        }
        standbyPhase(user, opponent);
        hasSummonInThisRound = false;
        mainPhase1(user, opponent);
        battlePhase(user, opponent);
        //mainPhase2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        endPhase(user, opponent);

        //!!!!!!!!!!!!!!!!!!!!!!!
        return false;
    }


    private void mainPhase1(User user, User opponent) {
        System.out.println("phase: Main Phase 1");
        String input = "";
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        while (!input.equals("next phase")) {
            input = scanner.nextLine();
            boolean checker = false;
            checker = select(user, opponent, input, "phase1");

        }
    }


    private void battlePhase(User user, User opponent) {
        System.out.println("phase: End Phase");
        String input = "";
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        while (!input.equals("next phase")) {
            input = scanner.nextLine();
            boolean checker = false;
            checker = select(user, opponent, input, "battle");

            if (!checker) {
                Pattern pattern = Pattern.compile("");
                Matcher matcher = pattern.matcher(input);
                if (matcher.find()) {
                    checker = true;
                }
            }
        }
    }

    private void endPhase(User user, User opponent) {
        System.out.println("phase: End Phase");


        System.out.println("its " + opponent.getNickname() + "’s turn");
    }


    private void standbyPhase(User user, User opponent) {
        System.out.println("phase: standby phase");
    }

    private void drawPhase(User user, User opponent) {
        System.out.println("phase: draw phase");
        String input = "";
        //in ke bishtar 6 kart nadashte bashe!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
            input = scanner.nextLine();
            boolean checker = false;
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            checker = select(user, opponent, input, "draw");

        }
    }


//-------------------------------------------------------------------------------------------------------


    public boolean select(User user, User opponent, String input, String phase) {
        boolean checker = false;
        Pattern pattern = Pattern.compile("select --hand ([\\d]+)");
        //what dose it do in other phase than phase1 and phase2???????????????????????????????
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
                    MonsterControlerInGame.monsterSelectedFromHand(monsterForUser, user, phase);
                    break;
                }
            }
            if (!isFind) {
                for (SpellCardForUser spellCardForUser : user.handSpell) {
                    if (spellCardForUser.address == address) {
                        isFind = true;
                        System.out.println("card selected");
                        spellControlerInGame.spellSelectedFromHand(spellCardForUser, user,opponent, phase);
                        break;
                    }
                }
            }
            if (isFind) {
                for (TrapCardForUser trapCardForUser : user.handTrap) {
                    if (trapCardForUser.address == address) {
                        isFind = true;
                        System.out.println("card selected");
                        //trapSelectedFromHand(trapCardForUser,user,phase);
                        break;
                    }
                }
            }
        }

        pattern = Pattern.compile("select --monster ([\\d]+)");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            int address = Integer.parseInt(matcher.group(1));
            if (user.monsterZone[address] == null) {
                System.out.println("no card found in the given position");
            } else {
                System.out.println("card selected");
                MonsterControlerInGame.selectedMonsterFromZone(user.monsterZone[address], user, opponent, phase);
            }
        }

        pattern = Pattern.compile("select --spell ([\\d]+)");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            int address = Integer.parseInt(matcher.group(1));
            if (user.spellZone[address] == null || user.trapZone[address] == null) {
                System.out.println("no card found in the given position");
            } else {
                System.out.println("card selected");
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
        }

        pattern = Pattern.compile("select --monster --opponent ([\\d]+)");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            int address = Integer.parseInt(matcher.group(1));
            if (opponent.monsterZone[address] == null) {
                System.out.println("no card found in the given position");
            } else {
                System.out.println("card selected");
                generalSelected(opponent.monsterZone[address]);
            }
        }

        pattern = Pattern.compile("select --spell --opponent ([\\d]+)");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            int address = Integer.parseInt(matcher.group(1));
            if (opponent.spellZone[address] == null || opponent.trapZone[address] == null) {
                System.out.println("no card found in the given position");
            } else {
                System.out.println("card selected");
                if (opponent.spellZone[address] == null) {
                    generalSelected(opponent.trapZone[address]);
                } else generalSelected(opponent.spellZone[address]);
            }
        }

        pattern = Pattern.compile("select --field ");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            int address = Integer.parseInt(matcher.group(1));
            if (user.fieldZone == null) {
                System.out.println("no card found in the given position");
            } else {
                System.out.println("card selected");
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
        }

        pattern = Pattern.compile("select --field --opponent ");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            int address = Integer.parseInt(matcher.group(1));
            if (opponent.fieldZone == null) {
                System.out.println("no card found in the given position");
            } else {
                System.out.println("card selected");
                generalSelected(opponent.fieldZone);
            }
        }

        pattern = Pattern.compile("show graveyard");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            showGrave(user);
        }


        if (input.equals("select -d")||input.equals("activate effect")) {
            checker = true;
            System.out.println("no card is selected yet");
        }

        return checker;
    }



    private void showGrave(User user) {
        boolean isAnyCardInGrave = false;
        HashMap<Integer, Card> grave = new HashMap<>();
        for (MonsterForUser monsterForUser : user.monsterGrave) {
            grave.put(monsterForUser.address, Card.getCardByName(monsterForUser.getName()));
            isAnyCardInGrave = true;
        }
        for (SpellCardForUser spellCardForUser : user.spellGrave) {
            grave.put(spellCardForUser.address, Card.getCardByName(spellCardForUser.getName()));
            isAnyCardInGrave = true;
        }
        for (TrapCardForUser trapCardForUser : user.trapGrave) {
            grave.put(trapCardForUser.address, Card.getCardByName(trapCardForUser.getName()));
            isAnyCardInGrave = true;
        }

        if (isAnyCardInGrave) {
            TreeMap<Integer, Card> sorted = new TreeMap<>();
            sorted.putAll(grave);

            int i = 1;
            for (Card card : sorted.values()) {
                System.out.println(i + ". " + card.getName() + ":" + card.getDescription());
                i++;
            }
        } else {
            System.out.println("graveyard empty");
        }
        //byd back bzne ke bargarde!!!!!!!!!!!!!!
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
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
            if (opponent.spellZone[a] == null && opponent.trapZone[a] == null) {
                System.out.print("    E");
            } else {
                if (opponent.spellZone[a] == null) {
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
            if (opponent.monsterZone[a] == null) {
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
        if (opponent.fieldZone == null) {
            System.out.println("E");
        } else System.out.println("O");

        System.out.println("");
        System.out.println("");
        System.out.println("--------------------------");
        System.out.println("");
        System.out.println("");

        if (user.fieldZone == null) {
            System.out.println("E" + "                       ");
        } else System.out.println("O" + "                       ");
        System.out.println(user.NumOfGrave);
        System.out.println();

        for (int a : harif) {
            if (user.monsterZone[a] == null) {
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
            if (opponent.spellZone[a] == null && opponent.trapZone[a] == null) {
                System.out.print("    E");
            } else {
                if (opponent.spellZone[a] == null) {
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


    public static void attack(MonsterForUser monsterForUser, MonsterForUser opponentMonsterForUser, User user, User opponent) {
        if (monsterForUser.ATK > opponentMonsterForUser.ATK || opponentMonsterForUser.getPosition().equals(Position.valueOf("ATTACK"))) {
            int damage = monsterForUser.ATK - opponentMonsterForUser.ATK;
            opponent.decreaseLP(damage);
            opponentMonsterForUser.setField(Field.valueOf("GRAVE"));
            System.out.println("your opponent's monster is destroyed and your opponent receives " + damage + " battle damage");
        } else if (monsterForUser.ATK == opponentMonsterForUser.ATK || opponentMonsterForUser.getPosition().equals(Position.valueOf("ATTACK"))) {
            opponentMonsterForUser.setField(Field.GRAVE);
            monsterForUser.setField(Field.GRAVE);
            System.out.println("both you and your opponent monster cards are destroyed and no one receives damage");
        } else if (monsterForUser.ATK < opponentMonsterForUser.ATK || opponentMonsterForUser.getPosition().equals(Position.valueOf("ATTACK"))) {
            int damage = opponentMonsterForUser.ATK - monsterForUser.ATK;
            user.decreaseLP(damage);
            monsterForUser.setField(Field.GRAVE);
            System.out.println("you monster card is destroyed and you receives " + damage + " battle damage");
        } else if (monsterForUser.ATK > opponentMonsterForUser.DEF || opponentMonsterForUser.getPosition().equals(Position.valueOf("DEFEND"))) {
            opponentMonsterForUser.setField(Field.GRAVE);
            System.out.println("the defense position monster is destroyed");
        } else if (monsterForUser.ATK == opponentMonsterForUser.DEF || opponentMonsterForUser.getPosition().equals(Position.valueOf("DEFEND"))) {
            System.out.println("no card is destroyed");
        } else if (monsterForUser.ATK < opponentMonsterForUser.DEF || opponentMonsterForUser.getPosition().equals(Position.valueOf("DEFEND"))) {
            int damage = opponentMonsterForUser.DEF - monsterForUser.ATK;
            user.decreaseLP(damage);
            System.out.println("no card is destroyed and you received " + damage + " battle damage");
        } else if (monsterForUser.ATK > opponentMonsterForUser.DEF || opponentMonsterForUser.getPosition().equals(Position.valueOf("HIDDEN"))) {
            opponentMonsterForUser.setField(Field.GRAVE);
            System.out.println("opponent's monster card was " + opponentMonsterForUser.getName() + " and the defense position monster is destroyed");
        } else if (monsterForUser.ATK == opponentMonsterForUser.DEF || opponentMonsterForUser.getPosition().equals(Position.valueOf("HIDDEN"))) {
            System.out.println("opponent's monster card was " + opponentMonsterForUser.getName() + " no card is destroyed");
        } else if (monsterForUser.ATK < opponentMonsterForUser.DEF || opponentMonsterForUser.getPosition().equals(Position.valueOf("HIDDEN"))) {
            int damage = opponentMonsterForUser.DEF - monsterForUser.ATK;
            user.decreaseLP(damage);
            System.out.println("opponent's monster card was " + opponentMonsterForUser.getName() + " no card is destroyed and you received " + damage + " battle damage");
        }
    }

    public static boolean generalSelected(Card card) {
        Scanner scanner=new Scanner(System.in);
        boolean checker = false;
        String input = "";
        while (!input.equals("select -d")) {
            input = scanner.nextLine();
            Pattern pattern = Pattern.compile("card show --selected");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                ProgramController.CardShow(card.getName());
            }
        }

        return checker;
    }


}
