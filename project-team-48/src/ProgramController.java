import Card.*;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramController {
    public static Scanner scanner;


    static {
        scanner = new Scanner(System.in);
    }


    public ProgramController() {
        run();
    }

    public static void run() {
        String input = scanner.nextLine();
        while (!input.equals("menu exit")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("Login Menu");
            }

//ja be jaie hatmn dorost she!!!!!
            pattern = Pattern.compile("user create --username ([^\\s]+) --nickname ([^\\s]+) --password ([^\\s]+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String username = matcher.group(1);
                String nickname = matcher.group(2);
                String password = matcher.group(3);
                creatUser(username, nickname, password);
            }


            pattern = Pattern.compile("user login --username ([^\\s]+) --password ([^\\s]+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String username = matcher.group(1);
                String password = matcher.group(2);
                login(username, password);

            }
//login first---------------------------------------------------------------------

            input = scanner.nextLine();
        }

    }


    private static void creatUser(String username, String nickname, String password) {
        for (User user : User.getListOfUsers()) {
            if (user.getUsername().equals(username)) {
                System.out.println("user with username " + username + " already exists");
                return;
            }
        }
        for (User user : User.getListOfUsers()) {
            if (user.getNickname().equals(nickname)) {
                System.out.println("user with nickname " + nickname + " already exists");
                return;
            }
        }
        new User(username, nickname, password);
        System.out.println("user created successfully!");
        return;
    }

    private static void login(String username, String password) {
        //check she ke boolean null nishe
        if (!User.passwordChecker(username, password)) {
            System.out.println("Username and password didn’t match!");
        } else {
            mainMenu(User.getUserByUsername(username));
        }
    }


    private static void mainMenu(User user) {
        String input = scanner.nextLine();
        while (!input.equals("menu exit")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("Main Menu");
            }


            pattern = Pattern.compile("user logout");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("user logged out successfully!");
                return;
            }

            pattern = Pattern.compile("menu enter scoreboard");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                scorebosrd();

            }

            pattern = Pattern.compile("menu enter profile");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                profile(user);
            }

            pattern = Pattern.compile("menu enter deck");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                deck(user);
            }

            pattern = Pattern.compile("menu enter shop");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                shop(user);
            }


            input = scanner.nextLine();
        }
    }

    private static void shop(User user) {
        String input = scanner.nextLine();
        while (!input.equals("menu exit")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("Shop Menu");
            }

            pattern = Pattern.compile("card show (.+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }

            pattern = Pattern.compile("^shop buy (^\\s+)$");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                boolean exist = false;
                String cardName = matcher.group(1);
                for (Card card : Card.getAllCards()) {
                    if (card.getName().equals(cardName)) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    System.out.println("there is no card with this name");
                } else {
                    if (Card.getCardByName(cardName).getPrice() <= user.getMoney()) {
                        long userMoney = user.getMoney();
                        long cardPrice = Card.getCardByName(cardName).getPrice();
                        long lastMoney = userMoney - cardPrice;
                        user.setMoney(lastMoney);
                        addCardToUser(cardName,user);
                    } else {
                        System.out.println("not enough money");
                    }
                }
            }

            input = scanner.nextLine();
        }
    }

    private static void addCardToUser(String cardname,User user) {
        for (MonsterCard monsterCard:MonsterCard.getAllMonsterCards()){
            if (monsterCard.getName().equals(cardname)){
            new MonsterForUser(monsterCard,user);
            return;
            }
        }
        for (SpellCard spellCard:SpellCard.getAllSpellCard()){
            if (spellCard.getName().equals(cardname)){
                new SpellCardForUser(spellCard,user);
                return;
            }
        }
        for (TrapCard trapCard:TrapCard.getAllTrapCard()){
            if (trapCard.getName().equals(cardname)){
                new TrapCardForUser(trapCard,user);
                return;
            }
        }
    }

    public static void scorebosrd() {
        String input = scanner.nextLine();
        while (!input.equals("menu exit")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("Scoreboard Menu");
            }

            pattern = Pattern.compile("scoreboard show");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                Sort();
                int j = 1;
                for (int i = 1; i < User.getListOfUsers().size(); ++i) {
                    System.out.println(j + "- " + User.getListOfUsers().get(i).getNickname() + ": " + User.getListOfUsers().get(i).getScore());
                    if (User.getListOfUsers().get(i + 1) != User.getListOfUsers().get(i)) j++;
                }
            }


            input = scanner.nextLine();
        }
    }

    //ham emtiaza check shan!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private static void Sort() {
        for (int i = 1; i < User.getListOfUsers().size(); ++i) {
            User key = User.getListOfUsers().get(i);
            int j = i - 1;
            while (j >= 0 && User.getListOfUsers().get(j).getScore() > key.getScore()) {
                User.getListOfUsers().set(j + 1, User.getListOfUsers().get(j));
                j = j - 1;
            }
            User.getListOfUsers().set(j + 1, key);
        }
    }


    public static void profile(User user) {
        String input = scanner.nextLine();
        while (!input.equals("menu exit")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("profile Menu");
            }


            pattern = Pattern.compile("profile change --nickname (.+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                boolean exist = false;
                String newNickname = matcher.group(1);

                if (user.getNickname().equals(newNickname)) {
                    System.out.println("this nickname is same to the nickname you have now!");

                } else {
                    for (User user1 : User.getListOfUsers()) {
                        if (user1.getNickname().equals(newNickname)) {
                            System.out.println("user with nickname " + newNickname + " already exists");
                            exist = true;
                            break;
                        }
                        if (!exist) {
                            user.setNickname(newNickname);
                            System.out.println("nickname changed successfully!");
                        }
                    }

                }


                pattern = Pattern.compile("profile change --password --current (.+) --new (.+)");
                matcher = pattern.matcher(input);
                if (matcher.find()) {
                    checker = true;
                    String password = matcher.group(1);
                    String newPassword = matcher.group(2);
                    if (user.getPassword().equals(1)) {
                        if (!password.equals(newPassword)) {
                            user.setPassword(newPassword);
                            System.out.println("password changed successfully!");
                        } else {
                            System.out.println("please enter a new password");
                        }
                    } else {
                        System.out.println("current password is invalid");
                    }
                }


                input = scanner.nextLine();

            }
        }
    }

    public static void deck(User user) {
        String input = scanner.nextLine();
        while (!input.equals("menu exit")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("deck Menu");
            }


            pattern = Pattern.compile("card show (.+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }

            pattern = Pattern.compile("deck create (^\\s+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                boolean exist = false;
                String name = matcher.group(1);
                for (Deck deck : user.allDecks) {
                    if (deck.getName().equals(name)) {
                        System.out.println("deck with name +" + name + " already exists");
                        exist = true;
                        break;
                    }
                    if (!exist) {
                        Deck deck1 = new Deck(user, name);
                        user.allDecks.add(deck1);
                        System.out.println("deck created successfully!");
                    }
                }
            }

            pattern = Pattern.compile("deck delete (^\\s+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                boolean exist = false;
                String name = matcher.group(1);
                for (Deck deck : user.allDecks) {
                    if (deck.getName().equals(name)) {
                        exist = true;
                        break;
                    }
                    if (exist) {

                        for (MonsterForUser monsterForUser : user.getDeckByName(name).allMonsterForUserMain) {
                            user.allMonsters.add(monsterForUser);
                            monsterForUser.deck = null;
                            monsterForUser.isInDeck = false;
                        }
                        for (MonsterForUser monsterForUser : user.getDeckByName(name).allMonsterForUserSide) {
                            user.allMonsters.add(monsterForUser);
                            monsterForUser.deck = null;
                            monsterForUser.isInDeck = false;
                        }
                        for (SpellCardForUser spellCardForUser : user.getDeckByName(name).allSpellCardsForUserMain) {
                            user.allSpells.add(spellCardForUser);
                            spellCardForUser.deck = null;
                            spellCardForUser.isInDeck = false;
                        }
                        for (SpellCardForUser spellCardForUser : user.getDeckByName(name).allSpellCardsForUserSide) {
                            user.allSpells.add(spellCardForUser);
                            spellCardForUser.deck = null;
                            spellCardForUser.isInDeck = false;
                        }
                        for (TrapCardForUser trapCardForUser : user.getDeckByName(name).allTrapCardsForUserMain) {
                            user.allTraps.add(trapCardForUser);
                            trapCardForUser.deck = null;
                            trapCardForUser.isInDeck = false;
                        }
                        for (TrapCardForUser trapCardForUser : user.getDeckByName(name).allTrapCardsForUserSide) {
                            user.allTraps.add(trapCardForUser);
                            trapCardForUser.deck = null;
                            trapCardForUser.isInDeck = false;
                        }
                        System.out.println("deck deleted successfully");
                    } else {
                        System.out.println("deck with name " + name + " does not exist");
                    }
                }

            }

            pattern = Pattern.compile("deck set-activate (^\\s+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                boolean exist = false;
                String name = matcher.group(1);
                for (Deck deck : user.allDecks) {
                    if (deck.getName().equals(name)) {
                        exist = true;
                        break;
                    }
                    if (exist) {
                        user.setActiveDeck(user.getDeckByName(name));
                        System.out.println("deck activated successfully");
                    } else {
                        System.out.println("deck with name " + name + " does not exist");
                    }
                }
            }
            pattern = Pattern.compile("^deck add-card --card (.+) --deck (^\\s+)$");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String cardName = matcher.group(1);
                String deckName = matcher.group(2);
                CardAdder(cardName, deckName, user);
            }

            pattern = Pattern.compile("^deck add-card --card (.+) --deck (^\\s+) --side$");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String cardName = matcher.group(1);
                String deckName = matcher.group(2);
                CardAdderSidedeck(cardName, deckName, user);
            }

            pattern = Pattern.compile("deck show --all");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (user.getActiveDeck() != null) {
                    System.out.println("Decks:");
                    System.out.println("Active deck:");
                    System.out.println(user.getActiveDeck().getName() + ": main deck" + user.getActiveDeck().numberOfCardsInMain + ", side deck" + user.getActiveDeck().numberOfCardsInSide + ", " + user.getActiveDeck().isValid());
                    System.out.println("Other decks:");
                    for (Deck deck : user.allDecks) {
                        if (!deck.getName().equals(user.getActiveDeck().getName())) {
                            System.out.println(deck.getName() + ": main deck" + deck.numberOfCardsInMain + ", side deck" + deck.numberOfCardsInSide + ", " + deck.isValid());
                        }
                    }
                } else {
                    System.out.println("Decks:");
                    System.out.println("Active deck:");
                    System.out.println("Other decks:");
                    for (Deck deck : user.allDecks) {
                        if (!deck.getName().equals(user.getActiveDeck().getName())) {
                            System.out.println(deck.getName() + ": main deck" + deck.numberOfCardsInMain + ", side deck" + deck.numberOfCardsInSide + ", " + deck.isValid());
                        }
                    }

                }
            }

            pattern = Pattern.compile("^deck show --deck-name (^\\s+)$");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                boolean exist = false;
                String name = matcher.group(1);
                for (Deck deck : user.allDecks) {
                    if (deck.getName().equals(name)) {
                        ShowMainDeck(deck);
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    System.out.println("deck with name " + name + " does not exist");
                }
            }

            pattern = Pattern.compile("^deck show --deck-name (^\\s+) --side$");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                boolean exist = false;
                String name = matcher.group(1);
                for (Deck deck : user.allDecks) {
                    if (deck.getName().equals(name)) {
                        ShowSideDeck(deck);
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    System.out.println("deck with name " + name + " does not exist");
                }
            }

            pattern = Pattern.compile("^deck show --cards$");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                ShowAllDeckCards(user);
            }


            input = scanner.nextLine();
        }
    }


    private static void ShowAllDeckCards(User user) {
        ArrayList<String> allOwendCards = new ArrayList<>();
        for (MonsterForUser monsterForUser : user.allMonsters) {
            allOwendCards.add(monsterForUser.getName());
        }
        for (SpellCardForUser spellCardForUser : user.allSpells) {
            allOwendCards.add(spellCardForUser.getName());
        }
        for (TrapCardForUser trapCardForUser : user.allTraps) {
            allOwendCards.add(trapCardForUser.getName());
        }
        for (Deck deck : user.allDecks) {
            for (MonsterForUser monsterForUser : deck.allMonsterForUserMain) {
                allOwendCards.add(monsterForUser.getName());
            }
            for (SpellCardForUser spellCardForUser : deck.allSpellCardsForUserMain) {
                allOwendCards.add(spellCardForUser.getName());
            }
            for (TrapCardForUser trapCardForUser : deck.allTrapCardsForUserMain) {
                allOwendCards.add(trapCardForUser.getName());
            }
            for (MonsterForUser monsterForUser : deck.allMonsterForUserSide) {
                allOwendCards.add(monsterForUser.getName());
            }
            for (SpellCardForUser spellCardForUser : deck.allSpellCardsForUserSide) {
                allOwendCards.add(spellCardForUser.getName());
            }
            for (TrapCardForUser trapCardForUser : deck.allTrapCardsForUserSide) {
                allOwendCards.add(trapCardForUser.getName());
            }
        }
        Collections.sort(allOwendCards);
        for (String cardName : allOwendCards) {
            System.out.println(cardName + ":" + Card.getCardByName(cardName).getDescription());
        }
    }


    private static void ShowMainDeck(Deck deck) {
        System.out.println("Deck: " + deck.getName());
        System.out.print("Side/Main deck: Main");
        System.out.println("Monsters:");
        ArrayList<String> monsterNames = new ArrayList<>();
        for (MonsterForUser monsterForUser : deck.allMonsterForUserMain) {
            monsterNames.add(monsterForUser.getName());
        }
        Collections.sort(monsterNames);
        for (String monsterName : monsterNames) {
            System.out.println(monsterName + ": " + Card.getCardByName(monsterName).getDescription());
        }
        ArrayList<String> spellAndTrapNames = new ArrayList<>();
        for (SpellCardForUser spellCardForUser : deck.allSpellCardsForUserMain) {
            spellAndTrapNames.add(spellCardForUser.getName());
        }
        for (TrapCardForUser trapCardForUser : deck.allTrapCardsForUserMain) {
            spellAndTrapNames.add(trapCardForUser.getName());
        }
        System.out.println("Spell and Traps:");
        Collections.sort(spellAndTrapNames);
        for (String spellAndTrapname : spellAndTrapNames) {
            System.out.println(spellAndTrapname + ": " + Card.getCardByName(spellAndTrapname).getDescription());
        }
    }

    private static void ShowSideDeck(Deck deck) {
        System.out.println("Deck: " + deck.getName());
        System.out.print("Side/Main deck: Side");
        System.out.println("Monsters:");
        ArrayList<String> monsterNames = new ArrayList<>();
        for (MonsterForUser monsterForUser : deck.allMonsterForUserSide) {
            monsterNames.add(monsterForUser.getName());
        }
        Collections.sort(monsterNames);
        for (String monsterName : monsterNames) {
            System.out.println(monsterName + ": " + Card.getCardByName(monsterName).getDescription());
        }
        ArrayList<String> spellAndTrapNames = new ArrayList<>();
        for (SpellCardForUser spellCardForUser : deck.allSpellCardsForUserSide) {
            spellAndTrapNames.add(spellCardForUser.getName());
        }
        for (TrapCardForUser trapCardForUser : deck.allTrapCardsForUserSide) {
            spellAndTrapNames.add(trapCardForUser.getName());
        }
        System.out.println("Spell and Traps:");
        Collections.sort(spellAndTrapNames);
        for (String spellAndTrapname : spellAndTrapNames) {
            System.out.println(spellAndTrapname + ": " + Card.getCardByName(spellAndTrapname).getDescription());
        }
    }

    private static void CardAdder(String cardName, String deckName, User user) {
        boolean cardExist = false;
        boolean deckExist = false;

        for (MonsterForUser monsterForUser : user.allMonsters) {
            if (monsterForUser.getName().equals(cardName)) {
                cardExist = true;
            }
        }
        if (cardExist) {
            MonsterAdder(cardName, deckName, user);
            return;
        }

        for (SpellCardForUser spellCardForUser : user.allSpells) {
            if (spellCardForUser.getName().equals(cardName)) {

                cardExist = true;
            }
        }
        if (cardExist) {
            SpellAdder(cardName, deckName, user);
            return;
        }

        for (TrapCardForUser trapCardForUser : user.allTraps) {
            if (trapCardForUser.getName().equals(cardName)) {
                cardExist = true;
            }
        }
        if (cardExist) {
            TrapAdder(cardName, deckName, user);
            return;
        }


        if (!cardExist) {
            System.out.println("card with name " + cardName + " does not exist");
            return;
        }

    }

    private static void MonsterAdder(String cardName, String deckName, User user) {
        boolean deckExist = false;
        int check = 0;

        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(deckName)) {
                deckExist = true;
            }
        }

        if (!deckExist) {
            System.out.println("deck with name " + deckName + " does not exist");
            return;
        }

        if (user.getDeckByName(deckName).numberOfCardsInMain < 60) {

            for (MonsterForUser monsterForUser1 : user.getDeckByName(deckName).allMonsterForUserMain) {
                if (monsterForUser1.getName().equals(cardName)) {
                    check++;
                }
            }
            if (check < 3) {
                for (MonsterForUser monsterForUser1 : user.allMonsters) {
                    if (monsterForUser1.getName().equals(cardName) && !monsterForUser1.isInDeck) {
                        user.allMonsters.remove(monsterForUser1);
                        user.getDeckByName(deckName).allMonsterForUserMain.add(monsterForUser1);
                        monsterForUser1.deck = user.getDeckByName(deckName);
                        monsterForUser1.isInDeck = true;
                        user.getDeckByName(deckName).numberOfCardsInMain++;
                        return;
                    }
                }
            } else {
                System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
            }
        } else {
            System.out.println("main deck is full");
        }
    }


    private static void SpellAdder(String cardName, String deckName, User user) {
        boolean deckExist = false;
        int check = 0;

        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(deckName)) {
                deckExist = true;
            }
        }

        if (!deckExist) {
            System.out.println("deck with name " + deckName + " does not exist");
            return;
        }

        if (user.getDeckByName(deckName).numberOfCardsInMain < 60) {

            for (SpellCardForUser spellCardForUser : user.getDeckByName(deckName).allSpellCardsForUserMain) {
                if (spellCardForUser.getName().equals(cardName)) {
                    check++;
                }
            }
            if (check < 3) {
                for (SpellCardForUser spellCardForUser : user.allSpells) {
                    if (spellCardForUser.getName().equals(cardName) && !spellCardForUser.isInDeck) {
                        user.allSpells.remove(spellCardForUser);
                        user.getDeckByName(deckName).allSpellCardsForUserMain.add(spellCardForUser);
                        spellCardForUser.deck = user.getDeckByName(deckName);
                        spellCardForUser.isInDeck = true;
                        user.getDeckByName(deckName).numberOfCardsInMain++;
                        return;
                    }
                }
            } else {
                System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
            }
        } else {
            System.out.println("main deck is full");
        }
    }


    private static void TrapAdder(String cardName, String deckName, User user) {
        boolean deckExist = false;
        int check = 0;

        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(deckName)) {
                deckExist = true;
            }
        }

        if (!deckExist) {
            System.out.println("deck with name " + deckName + " does not exist");
            return;
        }

        if (user.getDeckByName(deckName).numberOfCardsInMain < 60) {

            for (TrapCardForUser trapCardForUser : user.getDeckByName(deckName).allTrapCardsForUserMain) {
                if (trapCardForUser.getName().equals(cardName)) {
                    check++;
                }
            }
            if (check < 3) {
                for (TrapCardForUser trapCardForUser : user.allTraps) {
                    if (trapCardForUser.getName().equals(cardName) && !trapCardForUser.isInDeck) {
                        user.allTraps.remove(trapCardForUser);
                        user.getDeckByName(deckName).allTrapCardsForUserMain.add(trapCardForUser);
                        trapCardForUser.deck = user.getDeckByName(deckName);
                        trapCardForUser.isInDeck = true;
                        user.getDeckByName(deckName).numberOfCardsInMain++;
                        return;
                    }
                }
            } else {
                System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
            }
        } else {
            System.out.println("main deck is full");
        }
    }

    private static void CardAdderSidedeck(String cardName, String deckName, User user) {
        boolean cardExist = false;
        boolean deckExist = false;

        for (MonsterForUser monsterForUser : user.allMonsters) {
            if (monsterForUser.getName().equals(cardName)) {
                cardExist = true;
            }
        }
        if (cardExist) {
            MonsterAdderSidedeck(cardName, deckName, user);
            return;
        }

        for (SpellCardForUser spellCardForUser : user.allSpells) {
            if (spellCardForUser.getName().equals(cardName)) {
                cardExist = true;
            }
        }
        if (cardExist) {
            SpellAdderSidedeck(cardName, deckName, user);
            return;
        }

        for (TrapCardForUser trapCardForUser : user.allTraps) {
            if (trapCardForUser.getName().equals(cardName)) {
                cardExist = true;
            }
        }
        if (cardExist) {
            TrapAdderSidedeck(cardName, deckName, user);
            return;
        }


        if (!cardExist) {
            System.out.println("card with name " + cardName + " does not exist");
            return;
        }

    }

    private static void MonsterAdderSidedeck(String cardName, String deckName, User user) {
        boolean deckExist = false;
        int check = 0;

        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(deckName)) {
                deckExist = true;
            }
        }

        if (!deckExist) {
            System.out.println("deck with name " + deckName + " does not exist");
            return;
        }

        if (user.getDeckByName(deckName).numberOfCardsInSide < 15) {

            for (MonsterForUser monsterForUser1 : user.getDeckByName(deckName).allMonsterForUserSide) {
                if (monsterForUser1.getName().equals(cardName)) {
                    check++;
                }
            }
            if (check < 3) {
                for (MonsterForUser monsterForUser1 : user.allMonsters) {
                    if (monsterForUser1.getName().equals(cardName) && !monsterForUser1.isInDeck) {
                        user.allMonsters.remove(monsterForUser1);
                        user.getDeckByName(deckName).allMonsterForUserSide.add(monsterForUser1);
                        monsterForUser1.deck = user.getDeckByName(deckName);
                        monsterForUser1.isInDeck = true;
                        user.getDeckByName(deckName).numberOfCardsInSide++;
                        return;
                    }
                }
            } else {
                System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
            }
        } else {
            System.out.println("side deck is full");
        }
    }


    private static void SpellAdderSidedeck(String cardName, String deckName, User user) {
        boolean deckExist = false;
        int check = 0;

        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(deckName)) {
                deckExist = true;
            }
        }

        if (!deckExist) {
            System.out.println("deck with name " + deckName + " does not exist");
            return;
        }

        if (user.getDeckByName(deckName).numberOfCardsInSide < 15) {

            for (SpellCardForUser spellCardForUser : user.getDeckByName(deckName).allSpellCardsForUserSide) {
                if (spellCardForUser.getName().equals(cardName)) {
                    check++;
                }
            }
            if (check < 3) {
                for (SpellCardForUser spellCardForUser : user.allSpells) {
                    if (spellCardForUser.getName().equals(cardName) && !spellCardForUser.isInDeck) {
                        user.allSpells.remove(spellCardForUser);
                        user.getDeckByName(deckName).allSpellCardsForUserSide.add(spellCardForUser);
                        spellCardForUser.deck = user.getDeckByName(deckName);
                        spellCardForUser.isInDeck = true;
                        user.getDeckByName(deckName).numberOfCardsInSide++;
                        return;
                    }
                }
            } else {
                System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
            }
        } else {
            System.out.println("side deck is full");
        }
    }


    private static void TrapAdderSidedeck(String cardName, String deckName, User user) {
        boolean deckExist = false;
        int check = 0;

        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(deckName)) {
                deckExist = true;
            }
        }

        if (!deckExist) {
            System.out.println("deck with name " + deckName + " does not exist");
            return;
        }

        if (user.getDeckByName(deckName).numberOfCardsInSide < 15) {

            for (TrapCardForUser trapCardForUser : user.getDeckByName(deckName).allTrapCardsForUserSide) {
                if (trapCardForUser.getName().equals(cardName)) {
                    check++;
                }
            }
            if (check < 3) {
                for (TrapCardForUser trapCardForUser : user.allTraps) {
                    if (trapCardForUser.getName().equals(cardName) && !trapCardForUser.isInDeck) {
                        user.allTraps.remove(trapCardForUser);
                        user.getDeckByName(deckName).allTrapCardsForUserSide.add(trapCardForUser);
                        trapCardForUser.deck = user.getDeckByName(deckName);
                        trapCardForUser.isInDeck = true;
                        user.getDeckByName(deckName).numberOfCardsInSide++;
                        return;
                    }
                }
            } else {
                System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
            }
        } else {
            System.out.println("side deck is full");
        }
    }
}
