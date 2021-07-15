package sample;

import java.text.DecimalFormat;
import java.util.TreeMap;

import sample.controller.DeleteDeck;
import sample.controller.Game.DrawCard;
import sample.controller.UserLogined;
import sample.model.Card.Position;
import sample.model.Card.*;
import sample.model.Card.Field;
import sample.model.Deck;
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
    public String phase = "start";
    private final int[] harif = {3, 1, 0, 2, 4};
    private final int[] khodm = {4, 2, 0, 1, 3};
    public static Deck deckTempUser = new Deck(UserLogined.user.getActiveDeck().user, "komaki1");
    public static Deck deckTempOpponent = new Deck(UserLogined.opponent.getActiveDeck().user, "komaki2");
    private static int round;
    private static int whichRound;
    public static int maxLp = 0;

    public Game(User user1, User user2, int rounds, int wRound) throws Exception {
        setUser1(user1);
        setUser2(user2);
        user1.setLifePoint(8000);
        user2.setLifePoint(8000);
        deckTempUser.copyDeck(UserLogined.user.getActiveDeck());
        deckTempOpponent.copyDeck(UserLogined.opponent.getActiveDeck());
        round = rounds;
        whichRound = wRound;
        run();
    }


    public void run() throws Exception {
        for (int i = 0; i < 4; i++) {
            System.out.println(user1.getNickname() + ":");
            drawPhase(user1, user2, "aval");
            System.out.println(user2.getNickname() + ":");
            drawPhase(user2, user1, "aval");
        }
        boolean bool = true;
        while (bool) {
            bool = play(user1, user2);
            if (bool) {
                bool = play(user2, user1);
            }
        }
        endGame(user1, user2);
    }

    public static void endGame(User user1, User user2) throws Exception {
        User winner;
        User loser;
        if (user2.lifePoint <= 0) {
            winner = user1;
            loser = user2;
        } else if (user1.lifePoint <= 0) {
            loser = user1;
            winner = user2;
        } else {
            winner = user1;
            loser = user2;
        }
        if (round == 1) {
            System.out.println(winner.getNickname() + " won");
            System.out.println(winner.getNickname() + " got " + winner.getLifePoint() + "+" + 1000 + " money");
            System.out.println(loser.getNickname() + " got " + 100 + " money");
            winner.setMoney(winner.getMoney() + winner.getLifePoint() + 1000);
            loser.setMoney(winner.getMoney() + 100);
            winner.setScore(winner.getScore() + 1000);
            System.out.println(winner.getNickname() + " got 1000 scores");
        }
        user1 = UserLogined.user;
        user2 = UserLogined.opponent;
        for (int i = 0; i < user1.monsterZone.length; i++) {
            if (user1.monsterZone[i] != null) {
                user1.monsterZone[i].canAttack = false;
                user1.monsterZone[i].canChange = false;
            }
        }
        for (int i = 0; i < user2.monsterZone.length; i++) {
            if (user2.monsterZone[i] != null) {
                user2.monsterZone[i].canAttack = false;
                user2.monsterZone[i].canChange = false;
            }
        }

        dasteAval = true;
        user1.getActiveDeck().copyDeck(deckTempUser);
        user2.getActiveDeck().copyDeck(deckTempOpponent);
        DeleteDeck.deleteDeck(deckTempUser, user1);
        DeleteDeck.deleteDeck(deckTempOpponent, user2);
        user1.handMonster.clear();
        user2.handMonster.clear();
        user1.handTrap.clear();
        user2.handTrap.clear();
        user1.handSpell.clear();
        user2.handSpell.clear();
        user1.fieldZone = null;
        user2.fieldZone = null;
        for (int i = 0; i < 5; i++) {
            user1.monsterZone[i] = null;
            user2.monsterZone[i] = null;
            user1.spellZone[i] = null;
            user2.spellZone[i] = null;
            user1.trapZone[i] = null;
            user2.trapZone[i] = null;
        }
        user1.monsterGrave.clear();
        user2.monsterGrave.clear();
        user1.spellGrave.clear();
        user2.spellGrave.clear();
        user1.spellGrave.clear();
        user2.spellGrave.clear();
        user1.NumOfGrave = 0;
        if (round == 1) ProgramController.mainMenu(UserLogined.user);
        else if (whichRound == 1) {
            System.out.println(winner.getNickname() + " won");
            if (winner.lifePoint >= maxLp) maxLp = winner.lifePoint;
            System.out.println("Round 2:");
            new Game(UserLogined.user, UserLogined.opponent, 3, 2);

        } else if (whichRound == 2) {
            System.out.println(winner.getNickname() + " won");
            if (winner.lifePoint >= maxLp) maxLp = winner.lifePoint;
            System.out.println("Round 3:");
            new Game(UserLogined.user, UserLogined.opponent, 3, 3);
        } else if (whichRound == 3) {
            if (winner.lifePoint >= maxLp) maxLp = winner.lifePoint;
            System.out.println(winner.getNickname() + " won");
            System.out.println(winner.getNickname() + " got " + maxLp * 3 + "+" + 3000 + " money");
            System.out.println(loser.getNickname() + " got " + 300 + " money");
            winner.setMoney(winner.getMoney() + maxLp * 3L + 3000);
            loser.setMoney(winner.getMoney() + 300);
            winner.setScore(winner.getScore() + 3000);
            System.out.println(winner.getNickname() + " got 1000 scores");
            ProgramController.mainMenu(UserLogined.user);
        }

    }

    private boolean play(User user, User opponent) throws Exception {
        hasSummonInThisRound = false;
        if (user.getActiveDeck().numberOfCardsInMain == 0 || user.getLifePoint() == 0 || opponent.getLifePoint() == 0) {
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
        dasteAval = false;
        if (user.getActiveDeck().numberOfCardsInMain == 0 || user.getLifePoint() == 0 || opponent.getLifePoint() == 0) {
            return false;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!
        return true;
    }


    private void mainPhase1(User user, User opponent) throws Exception {
        showField(user, opponent);
        phase = "phase1";
        String input = "";
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        while (true) {
            showField(user, opponent);

            System.out.println("main phase 1");
            input = scanner.nextLine();
            boolean checker = false;
            if (input.equals("next phase")) return;
            checker = select(user, opponent, input, "phase1");
            if (!checker) {
                System.out.println("invalid input");
            }
        }
    }


    private void battlePhase(User user, User opponent) throws Exception {
        phase = "battle";
        showField(user, opponent);
        String input = "";
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        while (true) {
            showField(user, opponent);

            System.out.println("phase: Battle Phase");
            input = scanner.nextLine();
            boolean checker = false;
            if (input.equals("next phase")) return;
            checker = select(user, opponent, input, "battle");

            if (!checker) {
                System.out.println("invalid input");
            }
        }
    }

    private void endPhase(User user, User opponent) throws Exception {
        for (int i = 0; i < user.monsterZone.length; i++) {
            if (user.monsterZone[i] != null) {
                user.monsterZone[i].canAttack = true;
                user.monsterZone[i].canChange = true;
            }
        }
        User user3 = UserLogined.user;
        UserLogined.user = UserLogined.opponent;
        UserLogined.opponent = user3;
        dasteAval = false;
        String input = "";
        phase = "End";
        while (true) {
            showField(user, opponent);

            System.out.println("phase: end phase");
            input = scanner.nextLine();
            boolean checker = false;
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            checker = select(user, opponent, input, "draw");
            if (input.equals("next phase")) {
                checker = true;
                break;
            }
            if (!checker) {
                System.out.println("invalid input");
            }
        }
        System.out.println("its " + opponent.getNickname() + "’s turn");
    }


    private void standbyPhase(User user, User opponent) throws Exception {
        showField(user, opponent);
        String input = "";
        phase = "standby";
        while (true) {
            System.out.println("phase: standby phase");
            input = scanner.nextLine();
            boolean checker = false;
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            checker = select(user, opponent, input, "draw");

            if (input.equals("next phase")) {
                break;
            }
            if (!checker) {
                System.out.println("invalid input");
            }
        }

    }


    private void drawPhase(User user, User opponent, String faz) throws Exception {

        System.out.println(DrawCard.draw(user));
        String input = "";
        if (!faz.equals("aval")) {
            phase = "draw";
            while (true) {
                showField(user, opponent);

                System.out.println("phase: draw phase");
                input = scanner.nextLine();
                boolean checker = false;
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                checker = select(user, opponent, input, "draw");
                if (input.equals("next phase")) {
                    break;
                }
                if (!checker) {
                    System.out.println("invalid input");
                }
            }
        }
    }


//-------------------------------------------------------------------------------------------------------


    public boolean select(User user, User opponent, String input, String phase) throws Exception {
        boolean checker = false;


        if (input.equals("surrender")) {
            checker = true;
            if (round == 1)
                System.out.println(user.getNickname() + ", do you wnat to surrender?(y/n)");
            else System.out.println(user.getNickname() + ", do you wnat to surrender all rounds you have played?(y/n)");

            String whatToDo = scanner.nextLine();
            if (whatToDo.equals("y")) {
                endGame(opponent, user);
            }
        }
        if (input.equals("hesoyam")) {
            checker = true;
            System.out.println("500 lp");
            user.lifePoint = user.lifePoint + 500;
        }
        if (input.equals("wingame")) {
            checker = true;
            System.out.println("done");
            endGame(user, opponent);
        }
        if (input.equals("summon") || input.equals("set") || input.equals("set --position attack") || input.equals("set --position defense") || input.equals("flip-summon") || input.equals("attack direct") || input.equals("activate effect") || input.equals("card show --selected")) {
            checker = true;
            System.out.println("no card is selected yet");
        }
        Pattern pattern = Pattern.compile("select --hand ([\\d]+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {

            checker = true;
            String temp = matcher.group(1);
            int address = Integer.parseInt(temp);
            address--;
            if (address > 5) {
                System.out.println("invalid selection");
                return true;
            }
            boolean isFind = false;
            for (MonsterForUser monsterForUser : user.handMonster) {
                if (monsterForUser.address == address) {
                    isFind = true;
                    System.out.println("card selected");
                    MonsterControllerInGame.monsterSelectedFromHand(monsterForUser, user, phase);
                    return true;
                }
            }
            if (!isFind) {

                for (SpellCardForUser spellCardForUser : user.handSpell) {
                    if (spellCardForUser.address == address) {
                        isFind = true;
                        System.out.println("card selected");
                        SpellControllerInGame.spellSelectedFromHand(spellCardForUser, user, opponent, phase);
                        return true;
                    }
                }
            }
            if (!isFind) {

                for (TrapCardForUser trapCardForUser : user.handTrap) {
                    if (trapCardForUser.address == address) {
                        isFind = true;
                        System.out.println("card selected");
                        trapControllerInGame.trapSelectedFromHand(trapCardForUser, user, opponent, phase);
                        return true;
                    }
                }
            }
            if (!isFind) {
                System.out.println("no card found in the given position");
                return true;
            }
        }


        pattern = Pattern.compile("attack ([\\d]+)");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            checker = true;
            System.out.println("no card is selected yet");
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
                System.out.println("what next?:)");
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
            if (opponent.spellZone[address] == null && opponent.trapZone[address] == null) {
                System.out.println("no card found in the given position");
            } else {
                System.out.println("card selected");
                if (opponent.spellZone[address] == null) {
                    generalSelected(opponent.trapZone[address]);
                } else {
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
                System.out.println("what next");
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


        if (input.equals("show graveyard")) {
            checker = true;
            showGrave(user);
        }


        if (input.equals("show graveyard opponent")) {
            checker = true;
            showGrave(opponent);
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

        String input = "";
        while (!input.equals("back")) {
            input = scanner.nextLine();
            if (!input.equals("back")) {
                System.out.println("invalid input");
            }
        }
    }


    public static boolean generalSelected(Card card) {
        Scanner scanner = new Scanner(System.in);
        boolean checker = false;
        String input = "";
        while (true) {
            checker = false;
            input = scanner.nextLine();
            Pattern pattern = Pattern.compile("card show --selected");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                ProgramController.CardShow(card.getName());
            }

            if (input.equals("select -d")) {
                checker = true;
                break;
            }

            if (!checker) {
                System.out.println("invalid input");
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

        System.out.println("    c   c   c   c   c   c");
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
            if (user.spellZone[a] == null && user.trapZone[a] == null) {
                System.out.print("    E");
            } else {
                if (user.spellZone[a] == null) {
                    if (user.trapZone[a].position.equals(Position.valueOf("HIDDEN"))) {
                        System.out.print("    H");
                    } else System.out.print("    O");
                } else {
                    if (user.spellZone[a].position.equals(Position.valueOf("HIDDEN"))) {
                        System.out.print("    H");
                    } else System.out.print("    O");
                }
            }
        }
        System.out.println("");
        System.out.println("                         " + user.getActiveDeck().numberOfCardsInMain);

        System.out.println("    c   c   c   c   c   c");
        System.out.println(user.getNickname() + ":" + user.getLifePoint());
    }


    public static String attack(MonsterForUser monsterForUser, MonsterForUser opponentMonsterForUser, User user, User opponent) {

        if (monsterForUser.ATK > opponentMonsterForUser.ATK && opponentMonsterForUser.getPosition().equals(Position.valueOf("ATTACK"))) {

            int damage = monsterForUser.ATK - opponentMonsterForUser.ATK;
            opponent.decreaseLP(damage);
            opponentMonsterForUser.setField(Field.valueOf("GRAVE"));
            opponent.monsterZone[opponentMonsterForUser.address] = null;
            opponentMonsterForUser.address = opponent.NumOfGrave;
            opponent.NumOfGrave++;
            opponent.monsterGrave.add(opponentMonsterForUser);
            if (opponentMonsterForUser.getName().equals("Yomi Ship")){
                monsterForUser.setField(Field.GRAVE);
                user.monsterZone[monsterForUser.address] = null;
                monsterForUser.address = user.NumOfGrave;
                user.NumOfGrave++;
                user.monsterGrave.add(monsterForUser);
            }
                GameGraphic1.error = "your opponent's monster is destroyed and your opponent receives " + damage + " battle damage";
            if (opponent.lifePoint <= 0) {
                return "tamam";
            }
            return "your opponent's monster is destroyed and your opponent receives " + damage + " battle damage";
        } else if (monsterForUser.ATK == opponentMonsterForUser.ATK && opponentMonsterForUser.getPosition().equals(Position.valueOf("ATTACK"))) {

            opponentMonsterForUser.setField(Field.GRAVE);
            opponent.monsterZone[opponentMonsterForUser.address] = null;
            opponentMonsterForUser.address = opponent.NumOfGrave;
            opponent.NumOfGrave++;
            opponent.monsterGrave.add(opponentMonsterForUser);

            monsterForUser.setField(Field.GRAVE);
            user.monsterZone[monsterForUser.address] = null;
            monsterForUser.address = user.NumOfGrave;
            user.NumOfGrave++;
            user.monsterGrave.add(monsterForUser);
            GameGraphic1.error = "both you and your opponent monster cards are destroyed and no one receives damage";
            return "both you and your opponent monster cards are destroyed and no one receives damage";
        } else if (monsterForUser.ATK < opponentMonsterForUser.ATK && opponentMonsterForUser.getPosition().equals(Position.valueOf("ATTACK"))) {

            int damage = opponentMonsterForUser.ATK - monsterForUser.ATK;
            user.decreaseLP(damage);
            user.monsterZone[monsterForUser.address] = null;
            monsterForUser.setField(Field.GRAVE);
            monsterForUser.address = user.NumOfGrave;
            user.NumOfGrave++;
            user.monsterGrave.add(monsterForUser);

            if (monsterForUser.getName().equals("Yomi Ship")){
                opponentMonsterForUser.setField(Field.GRAVE);
                opponent.monsterZone[opponentMonsterForUser.address] = null;
                opponentMonsterForUser.address = opponent.NumOfGrave;
                opponent.NumOfGrave++;
                opponent.monsterGrave.add(opponentMonsterForUser);
            }
            if (user.lifePoint <= 0) {
                return "tamam";
            }
            GameGraphic1.error = "you monster card is destroyed and you receives " + damage + " battle damage";
            return "you monster card is destroyed and you receives " + damage + " battle damage";
        } else if (monsterForUser.ATK > opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("DEFEND"))) {
            opponentMonsterForUser.setField(Field.GRAVE);
            opponent.monsterZone[opponentMonsterForUser.address] = null;
            opponentMonsterForUser.address = opponent.NumOfGrave;
            opponent.monsterGrave.add(opponentMonsterForUser);
            opponent.NumOfGrave++;
            if (opponentMonsterForUser.getName().equals("Yomi Ship")){
                monsterForUser.setField(Field.GRAVE);
                user.monsterZone[monsterForUser.address] = null;
                monsterForUser.address = user.NumOfGrave;
                user.NumOfGrave++;
                user.monsterGrave.add(monsterForUser);
            }
            GameGraphic1.error = "the defense position monster is destroyed";
            return "the defense position monster is destroyed";
        } else if (monsterForUser.ATK == opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("DEFEND"))) {
            GameGraphic1.error = ("no card is destroyed");
            return "no card is destroyed";
        } else if (monsterForUser.ATK < opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("DEFEND"))) {
            int damage = opponentMonsterForUser.DEF - monsterForUser.ATK;
            user.decreaseLP(damage);
            if (user.lifePoint <= 0) {
                return "tamam";
            }
            GameGraphic1.error = ("no card is destroyed and you received " + damage + " battle damage");
            return ("no card is destroyed and you received " + damage + " battle damage");
        } else if (monsterForUser.ATK > opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("HIDDEN"))) {
            opponentMonsterForUser.position = Position.valueOf("DEFEND");
            opponentMonsterForUser.setField(Field.GRAVE);
            opponent.monsterZone[opponentMonsterForUser.address] = null;
            opponentMonsterForUser.address = opponent.NumOfGrave;
            opponent.NumOfGrave++;
            opponent.monsterGrave.add(opponentMonsterForUser);
            if (opponentMonsterForUser.getName().equals("Yomi Ship")){
                monsterForUser.setField(Field.GRAVE);
                user.monsterZone[monsterForUser.address] = null;
                monsterForUser.address = user.NumOfGrave;
                user.NumOfGrave++;
                user.monsterGrave.add(monsterForUser);
            }
            GameGraphic1.error = ("opponent's monster card was " + opponentMonsterForUser.getName() + " and the defense position monster is destroyed");
            return ("opponent's monster card was " + opponentMonsterForUser.getName() + " and the defense position monster is destroyed");
        } else if (monsterForUser.ATK == opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("HIDDEN"))) {
            opponentMonsterForUser.position = Position.valueOf("DEFEND");
            GameGraphic1.error = ("opponent's monster card was " + opponentMonsterForUser.getName() + " no card is destroyed");
            return ("opponent's monster card was " + opponentMonsterForUser.getName() + " no card is destroyed");
        } else if (monsterForUser.ATK < opponentMonsterForUser.DEF && opponentMonsterForUser.getPosition().equals(Position.valueOf("HIDDEN"))) {
            opponentMonsterForUser.position = Position.valueOf("DEFEND");
            int damage = opponentMonsterForUser.DEF - monsterForUser.ATK;
            user.decreaseLP(damage);
            if (opponent.lifePoint <= 0) {
                return "tamam";
            }
            GameGraphic1.error = ("opponent's monster card was " + opponentMonsterForUser.getName() + " no card is destroyed and you received " + damage + " battle damage");
            return ("opponent's monster card was " + opponentMonsterForUser.getName() + " no card is destroyed and you received " + damage + " battle damage");
        }
        return "error";
    }

}
