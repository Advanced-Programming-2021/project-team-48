import java.nio.file.Path;
import java.util.TreeMap;
import Card.Position;
import Card.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private User user1;
    private User user2;
    private int numInHand = 1;
    private boolean dasteAval = false;
    private boolean hasSummonInThisRound = false;
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


    private boolean select(User user, User opponent, String input, String phase) {
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
                    monsterSelectedFromHand(monsterForUser, user, phase);
                    break;
                }
            }
            if (!isFind) {
                for (SpellCardForUser spellCardForUser : user.handSpell) {
                    if (spellCardForUser.address == address) {
                        isFind = true;
                        System.out.println("card selected");
                        // spellSelectedFromHand(spellCardForUser, user,phase);
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
                selectedMonsterFromZone(user.monsterZone[address], user, opponent, phase);
            }
        }

        pattern = Pattern.compile("select --spell ([\\d]+)");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            int address = Integer.parseInt(matcher.group(1));
            if (user.spellZone[address] == null && user.trapZone[address] == null) {
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
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
        }

        pattern = Pattern.compile("select --spell --opponent ([\\d]+)");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            int address = Integer.parseInt(matcher.group(1));
            if (opponent.spellZone[address] == null && opponent.trapZone[address] == null) {
                System.out.println("no card found in the given position");
            } else {
                System.out.println("card selected");
                generalSelected()
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
            if (user.fieldZone == null) {
                System.out.println("no card found in the given position");
            } else {
                System.out.println("card selected");
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
        }

        pattern = Pattern.compile("show graveyard");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            showGrave(user);
        }

        pattern = Pattern.compile("select -d");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            System.out.println("no card is selected yet");
        }

        return checker;
    }

    private void attack(MonsterForUser monsterForUser, MonsterForUser opponentMonsterForUser, User user, User opponent) {

    }

   private boolean generalSelected(Card card,String input){
       boolean checker = false;
       Pattern pattern = Pattern.compile("card show --selected");
       Matcher matcher = pattern.matcher(input);
       if (matcher.find()) {
           checker=true;
           ProgramController.CardShow(card.getName());
       }

       return checker;
    }


    private void selectedMonsterFromZone(MonsterForUser monsterForUser, User user, User opponent, String phase) {
        String input = "";
        while (!input.equals("select -d")) {
            input = scanner.nextLine();
            boolean checker = false;
            Pattern pattern = Pattern.compile("set -- position attack");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    positionAttack(monsterForUser);
                } else {
                    System.out.println("you can’t do this action in this phase");
                }
            }
//in ke har dast ye bar avaz mishe!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            pattern = Pattern.compile("set -- position defense");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    positionDefend(monsterForUser);
                } else {
                    System.out.println("you can’t do this action in this phase");
                }
            }

            pattern = Pattern.compile("flip-summon");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    flipSummon(monsterForUser);
                } else {
                    System.out.println("you can’t do this action in this phase");
                }
            }

            pattern = Pattern.compile("attack direct");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("battle")) {
                    boolean checkIfOpponentMonsterZoneEmpty = true;
                    for (int a : checkIfEmpty) {
                        if (opponent.monsterZone[a] != null) {
                            checkIfOpponentMonsterZoneEmpty = false;
                            break;
                        }
                    }
                    if (checkIfOpponentMonsterZoneEmpty) {
                        opponent.lifePoint -= monsterForUser.ATK;
                        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        System.out.println("you opponent receives " + monsterForUser.ATK + " battale damage");
                    } else {
                        System.out.println("you can’t attack the opponent directly");
                    }
                } else {
                    System.out.println("you can’t do this action in this phase");
                }
            }

            pattern = Pattern.compile("card show --selected");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                ProgramController.ShowMonster(monsterForUser.getName());
            }
        }
    }


    private void monsterSelectedFromHand(MonsterForUser monsterForUser, User user, String phase) {
        String input = "";
        while (!input.equals("select -d")) {
            input = scanner.nextLine();
            boolean checker = false;
            Pattern pattern = Pattern.compile("summon");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    if (hasSummonInThisRound) {
                        System.out.println("you already summoned/set on this turn");
                    } else {
                        checker = true;
                        summonControler(monsterForUser, user);

                    }
                } else {
                    System.out.println("action not allowed in this phase");
                }
            }

            pattern = Pattern.compile("set");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    if (hasSummonInThisRound) {
                        System.out.println("you already summoned/set on this turn");
                    } else {
                        checker = true;
                        setController(monsterForUser, user);
                    }
                } else System.out.println("action not allowed in this phase");
            }

            pattern = Pattern.compile("card show --selected");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                ProgramController.ShowMonster(monsterForUser.getName());
            }
        }
    }


    private void setController(MonsterForUser monsterForUser, User user) {
        if (monsterForUser.level <= 4) {
            set(monsterForUser, user);
        } else {
            if (tribute(monsterForUser, user)) {
                set(monsterForUser, user);
            }
        }
    }

    private void set(MonsterForUser monsterForUser, User user) {
        boolean hasEmpty = false;
        for (int a : checkIfEmpty) {
            if (user.monsterZone[a] == null) {
                hasEmpty = true;
                monsterForUser.field = Field.valueOf("GAME");
                monsterForUser.address = a;
                monsterForUser.position = Position.valueOf("HIDDEN");
                user.monsterZone[a] = monsterForUser;
                user.handMonster.remove(monsterForUser);
                System.out.println("set successfully");
                break;
            }
        }
        if (!hasEmpty) {
            System.out.println("monster card zone is full");
        }
    }

    private void summonControler(MonsterForUser monsterForUser, User user) {
        if (monsterForUser.level <= 4) {
            summon(monsterForUser, user);

        } else {
            if (tribute(monsterForUser, user)) {
                summon(monsterForUser, user);
            }
        }
    }

    private void summon(MonsterForUser monsterForUser, User user) {
        boolean hasEmpty = false;
        for (int a : checkIfEmpty) {
            if (user.monsterZone[a] == null) {
                hasEmpty = true;
                monsterForUser.field = Field.valueOf("GAME");
                monsterForUser.address = a;
                monsterForUser.position = Position.valueOf("ATTACK");
                user.monsterZone[a] = monsterForUser;
                user.handMonster.remove(monsterForUser);
                System.out.println("summoned successfully");
                break;
            }
        }
        if (!hasEmpty) {
            System.out.println("monster card zone is full");
        }
    }

    private boolean tribute(MonsterForUser monsterForUser, User user) {
        if (monsterForUser.level == 5 || monsterForUser.level == 6) {
            boolean hasAnyCard = false;
            for (int a : checkIfEmpty) {
                if (user.handMonster != null) {
                    hasAnyCard = true;

                }
            }

            if (hasAnyCard) {
                System.out.println("enter an address to tribute");
                String temp = scanner.nextLine();
                int address = Integer.parseInt(temp);
                if (user.monsterZone[address] == null) {
                    System.out.println("there no monsters one this address");
                } else {
                    user.monsterZone[address].field = Field.valueOf("GRAVE");
                    user.monsterGrave.add(user.monsterZone[address]);
                    user.NumOfGrave++;
                    user.monsterZone[address].address = user.NumOfGrave;
                    user.monsterZone[address] = null;
                    return true;
                }
            } else {
                System.out.println("there are not enough cards for tribute");
                return false;
            }
        }


        if (monsterForUser.level > 6) {
            int checker = 0;
            for (int a : checkIfEmpty) {
                if (user.handMonster != null) {
                    checker++;
                }
            }

            if (checker >= 2) {
                System.out.println("enter an address to tribute");
                String temp = scanner.nextLine();
                int address = Integer.parseInt(temp);
                if (user.monsterZone[address] == null) {
                    System.out.println("there no monsters one this address");
                } else {
                    System.out.println("enter an address to tribute");
                    temp = scanner.nextLine();
                    int address1 = Integer.parseInt(temp);
                    if (user.monsterZone[address] == null) {
                        System.out.println("there no monsters one this address");
                    } else {
                        user.monsterZone[address].field = Field.valueOf("GRAVE");
                        user.monsterGrave.add(user.monsterZone[address]);
                        user.NumOfGrave++;
                        user.monsterZone[address].address = user.NumOfGrave;
                        user.monsterZone[address] = null;

                        user.monsterZone[address1].field = Field.valueOf("GRAVE");
                        user.monsterGrave.add(user.monsterZone[address1]);
                        user.NumOfGrave++;
                        user.monsterZone[address1].address = user.NumOfGrave;
                        user.monsterZone[address1] = null;

                        return true;
                    }
                }
            } else {
                System.out.println("there are not enough cards for tribute");
                return false;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return false;
    }


    private void positionAttack(MonsterForUser monsterForUser) {
        if (monsterForUser.position.equals(Position.valueOf("ATTACK"))) {
            System.out.println("this card is already in the wanted position");
        } else {
            if (monsterForUser.position.equals(Position.valueOf("DEFEND"))) {
                monsterForUser.position = Position.valueOf("ATTACK");
                System.out.println("monster card position changed successfully");
            }
        }
    }

    private void positionDefend(MonsterForUser monsterForUser) {
        if (monsterForUser.position.equals(Position.valueOf("DEFEND"))) {
            System.out.println("this card is already in the wanted position");
        } else {
            if (monsterForUser.position.equals(Position.valueOf("ATTACK"))) {
                monsterForUser.position = Position.valueOf("DEFEND");
                System.out.println("monster card position changed successfully");
            }
        }
    }

    private void flipSummon(MonsterForUser monsterForUser) {
        if (monsterForUser.position.equals(Position.valueOf("HIDDEN"))) {
            monsterForUser.position = Position.valueOf("ATTACK");
        } else {
            System.out.println("you can’t flip summon this card");
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!age taze gozashte bashe nmishe flip krd!!!!!!!!!!!!!!!
        }
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
}
