package sample;

import java.util.TreeMap;

import sample.controller.Game.DrawCard;
import sample.model.Card.Position;
import sample.model.Card.*;
import sample.model.Card.Field;
import sample.model.User;
import sample.view.graphic.GameGraphic1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private User user1;
    private User user2;
    public static boolean dasteAval = true;
    public static boolean hasSummonInThisRound = false;
    public String phase = "";
    private int[] harif = {3, 1, 0, 2, 4};
    private int[] khodm = {4, 2, 0, 1, 3};


    public Game(User user1, User user2) throws Exception {
        setUser1(user1);
        setUser2(user2);
        user1.setLifePoint(8000);
        user2.setLifePoint(8000);
        run();
    }


    public void run() throws Exception {
        for (int i = 0; i < 4; i++) {
            System.out.println("user 1:");
            drawPhase(user1, user2, "aval");
            System.out.println("user 2:");
            drawPhase(user2, user1, "aval");
        }
        boolean bool = true;
        while (bool) {
            bool = play(user1, user2);
            if (bool) {
                bool =  play(user2, user1);
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        user1.setActiveDeck(user1.getDeckByName(user1.getActiveDeck().getName()));
        user2.setActiveDeck(user2.getDeckByName(user2.getActiveDeck().getName()));
        user1.handMonster.clear();
        user2.handMonster.clear();
        user1.handTrap.clear();
        user2.handTrap.clear();
        user1.handSpell.clear();
        user2.handSpell.clear();
        user1.fieldZone=null;
        user2.fieldZone=null;
        for (int i=0;i<5;i++){
            user1.monsterZone[i]=null;
            user2.monsterZone[i]=null;
            user1.spellZone[i]=null;
            user2.spellZone[i]=null;
            user1.trapZone[i]=null;
            user2.trapZone[i]=null;
        }
        user1.monsterGrave.clear();
        user2.monsterGrave.clear();
        user1.spellGrave.clear();
        user2.spellGrave.clear();
        user1.spellGrave.clear();
        user2.spellGrave.clear();
        user1.NumOfGrave=0;
    }

    private boolean play(User user, User opponent) throws Exception {
        hasSummonInThisRound = false;
        showField(user, opponent);
        if (user.getActiveDeck().numberOfCardsInMain == 0||user.getLifePoint()==0||opponent.getLifePoint()==0) {
            return false;
        }
        if (!dasteAval) {
            drawPhase(user, opponent, "dakhleBazi");
        }
        standbyPhase(user, opponent);
        mainPhase1(user, opponent);
        battlePhase(user, opponent);
        //mainPhase2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        endPhase(user, opponent);
        dasteAval = true;
        if (user.getActiveDeck().numberOfCardsInMain == 0||user.getLifePoint()==0||opponent.getLifePoint()==0) {
            return false;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!
        return true;
    }


    private void mainPhase1(User user, User opponent) throws Exception {
        showField(user,opponent);
        phase = "phase1";
        System.out.println("phase: Main Phase 1");
        String input = "";
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        while (true) {
            System.out.println("main phase 1");
            input = scanner.nextLine();
            boolean checker = false;
            if (input.equals("next phase"))return;
            checker = select(user, opponent, input, "phase1");
            if (!checker) {
                System.out.println("invalid input");
            }
        }
    }


    private void battlePhase(User user, User opponent) throws Exception {
        phase = "battle";
        showField(user,opponent);
        String input = "";
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        while (true) {
            System.out.println("phase: Battle Phase");
            input = scanner.nextLine();
            boolean checker = false;
            if (input.equals("next phase"))return;
            checker = select(user, opponent, input, "battle");

            if (!checker) {
                System.out.println("invalid input");
            }
        }
    }

    private void endPhase(User user, User opponent) {
        System.out.println("phase: End Phase");
        phase = "End";
        System.out.println("its " + opponent.getNickname() + "’s turn");
    }


    private void standbyPhase(User user, User opponent) {
        System.out.println("phase: standby phase");
        phase = "standby";
    }


    private void drawPhase(User user, User opponent, String faz) throws Exception {
        if (faz != "aval") {
            phase = "draw";
            System.out.println("phase: draw phase");
        }
        System.out.println(DrawCard.draw(user));
        String input = "";
        if (faz != "aval") {
            while (!input.equals("next phase")) {
                System.out.println("phase: draw phase");
                input = scanner.nextLine();
                boolean checker = false;
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                checker = select(user, opponent, input, "draw");
                if (!checker) {
                    System.out.println("invalid input");
                }
            }
        }
    }



//-------------------------------------------------------------------------------------------------------


    public boolean select(User user, User opponent, String input, String phase) throws Exception {
        boolean checker = false;
        Pattern pattern = Pattern.compile("select --hand ([\\d]+)");
        //what dose it do in other phase than phase1 and phase2???????????????????????????????
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            String temp = matcher.group(1);
            int address = Integer.parseInt(temp);
            address--;
            if (address>5)return false;
            boolean isFind = false;
            for (MonsterForUser monsterForUser : user.handMonster) {
                if (monsterForUser.address == address) {
                    isFind = true;
                    System.out.println("card selected");
                    MonsterControllerInGame.monsterSelectedFromHand(monsterForUser, user, phase);
                    break;
                }
            }
            if (!isFind) {
                for (SpellCardForUser spellCardForUser : user.handSpell) {
                    if (spellCardForUser.address == address) {
                        isFind = true;
                        System.out.println("card selected");
                        SpellControllerInGame.spellSelectedFromHand(spellCardForUser, user, opponent, phase);
                        break;
                    }
                }
            }
            if (isFind) {
                for (TrapCardForUser trapCardForUser : user.handTrap) {
                    if (trapCardForUser.address == address) {
                        isFind = true;
                        System.out.println("card selected");
                        trapControllerInGame.trapSelectedFromHand(trapCardForUser, user, opponent, phase);
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
            address--;
            if (user.monsterZone[address] == null) {
                System.out.println("no card found in the given position");
            } else {
                System.out.println("card selected");
                MonsterControllerInGame.selectedMonsterFromZone(user.monsterZone[address], user, opponent, phase);
            }
        }

        pattern = Pattern.compile("select --spell ([\\d]+)");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            int address = Integer.parseInt(matcher.group(1));
            address--;
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
            address--;
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
            address--;
            if (opponent.spellZone[address] == null || opponent.trapZone[address] == null) {
                System.out.println("no card found in the given position");
            } else {
                System.out.println("card selected");
                if (opponent.spellZone[address] == null) {
                    System.out.println("no card found in the given position");
                } else{
                    System.out.println("card selected");
                    generalSelected(opponent.spellZone[address]);
                }
            }
        }

        pattern = Pattern.compile("select --field ");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            int address = Integer.parseInt(matcher.group(1));
            address--;
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
            address--;
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


        if (input.equals("select -d") || input.equals("activate effect")) {
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
        boolean checker = false;
        String input = "";
        while (input != "back") {
            input = scanner.nextLine();
            if (input != "back") {
                System.out.println("invalid input");
            }
        }
    }


    public static boolean generalSelected(Card card) {
        Scanner scanner = new Scanner(System.in);
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
        System.out.print(opponent.NumOfGrave + "                       ");
        if (opponent.fieldZone == null) {
            System.out.println("E");
        } else System.out.println("O");

        System.out.println("");
        System.out.println("");
        System.out.println("--------------------------");
        System.out.println("");
        System.out.println("");

        if (user.fieldZone == null) {
            System.out.print("E" + "                       ");
        } else System.out.println("O" + "                       ");
        System.out.println(user.NumOfGrave);
        System.out.println();

        for (int a : khodm) {
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
        System.out.println("");
        for (int a : khodm) {
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

        if (monsterForUser.ATK > opponentMonsterForUser.ATK && opponentMonsterForUser.getPosition().equals(Position.valueOf("ATTACK"))) {

            int damage = monsterForUser.ATK - opponentMonsterForUser.ATK;
            opponent.decreaseLP(damage);
            opponentMonsterForUser.setField(Field.valueOf("GRAVE"));
            opponent.monsterZone[opponentMonsterForUser.address]=null;
            opponentMonsterForUser.address = opponent.NumOfGrave;
            opponent.NumOfGrave++;
            opponent.monsterGrave.add(opponentMonsterForUser);
            GameGraphic1.error= "your opponent's monster is destroyed and your opponent receives " + damage + " battle damage";

        } else if (monsterForUser.ATK == opponentMonsterForUser.ATK && opponentMonsterForUser.getPosition().equals(Position.valueOf("ATTACK"))) {

            opponentMonsterForUser.setField(Field.GRAVE);
            opponent.monsterZone[opponentMonsterForUser.address]=null;
            opponentMonsterForUser.address = opponent.NumOfGrave;
            opponent.NumOfGrave++;
            opponent.monsterGrave.add(opponentMonsterForUser);

            monsterForUser.setField(Field.GRAVE);
            user.monsterZone[monsterForUser.address]=null;
            monsterForUser.address = user.NumOfGrave;
            user.NumOfGrave++;
            user.monsterGrave.add(monsterForUser);
            GameGraphic1.error="both you and your opponent monster cards are destroyed and no one receives damage";

        } else if (monsterForUser.ATK < opponentMonsterForUser.ATK && opponentMonsterForUser.getPosition().equals(Position.valueOf("ATTACK"))) {

            int damage = opponentMonsterForUser.ATK - monsterForUser.ATK;
            user.decreaseLP(damage);
            user.monsterZone[monsterForUser.address]=null;
            monsterForUser.setField(Field.GRAVE);
            monsterForUser.address = user.NumOfGrave;
            user.NumOfGrave++;
            user.monsterGrave.add(monsterForUser);
            GameGraphic1.error="you monster card is destroyed and you receives " + damage + " battle damage";

        } else if (monsterForUser.ATK > opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("DEFEND"))) {
            opponentMonsterForUser.setField(Field.GRAVE);
            opponent.monsterZone[opponentMonsterForUser.address]=null;
            opponentMonsterForUser.address = opponent.NumOfGrave;
            opponent.monsterGrave.add(opponentMonsterForUser);
            opponent.NumOfGrave++;
            GameGraphic1.error="the defense position monster is destroyed";
        } else if (monsterForUser.ATK == opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("DEFEND"))) {
            System.out.println("no card is destroyed");
        } else if (monsterForUser.ATK < opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("DEFEND"))) {
            int damage = opponentMonsterForUser.DEF - monsterForUser.ATK;
            user.decreaseLP(damage);
            System.out.println("no card is destroyed and you received " + damage + " battle damage");
        } else if (monsterForUser.ATK > opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("HIDDEN"))) {
            opponentMonsterForUser.position=Position.valueOf("DEFEND");
            opponentMonsterForUser.setField(Field.GRAVE);
           opponent.monsterZone[opponentMonsterForUser.address]=null;
            opponentMonsterForUser.address = opponent.NumOfGrave;
            opponent.NumOfGrave++;
            opponent.monsterGrave.add(opponentMonsterForUser);
            GameGraphic1.error=("opponent's monster card was " + opponentMonsterForUser.getName() + " and the defense position monster is destroyed");
        } else if (monsterForUser.ATK == opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("HIDDEN"))) {
            opponentMonsterForUser.position=Position.valueOf("DEFEND");
            GameGraphic1.error=("opponent's monster card was " + opponentMonsterForUser.getName() + " no card is destroyed");
        } else if (monsterForUser.ATK < opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("HIDDEN"))) {
            opponentMonsterForUser.position=Position.valueOf("DEFEND");
            int damage = opponentMonsterForUser.DEF - monsterForUser.ATK;
            user.decreaseLP(damage);
            GameGraphic1.error=("opponent's monster card was " + opponentMonsterForUser.getName() + " no card is destroyed and you received " + damage + " battle damage");
        }
    }

}
