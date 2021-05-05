import Card.MonsterForUser;
import Card.SpellCardForUser;
import Card.TrapCard;
import Card.TrapCardForUser;

import java.text.DecimalFormat;
import java.util.ArrayList;
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


            input = scanner.nextLine();
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
                    if (deck.name.equals(name)) {
                        exist = true;
                        break;
                    }
                    if (exist) {

                        for (MonsterForUser monsterForUser : user.getDeckByName(name).allMonsterForUser) {
                            monsterForUser.deck = null;
                            monsterForUser.isInDeck = false;
                        }
                        for (SpellCardForUser spellCardForUser : user.getDeckByName(name).allSpellCardsForUser) {
                            spellCardForUser.deck = null;
                            spellCardForUser.isInDeck = false;
                        }
                        for (TrapCardForUser trapCardForUser : user.getDeckByName(name).allTrapCardsForUser) {
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
                    if (deck.name.equals(name)) {
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
            input = scanner.nextLine();
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

        if (!cardExist) {
            System.out.println("card with name " + cardName + " does not exist");
            return;
        }
        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(deckName)) {
                deckExist = true;
            }
        }

        if (!deckExist) {
            System.out.println("deck with name " + deckName + " does not exist");
            return;
        }

        if (user.getDeckByName(deckName).numberOfCards < 60) {
            int check = 0;
            for (MonsterForUser monsterForUser1 : user.getDeckByName(deckName).allMonsterForUser) {
                if (monsterForUser1.getName().equals(cardName)) {
                    check++;
                }
            }
            if (check < 3) {
                for (MonsterForUser monsterForUser1 : user.allMonsters) {
                    if (monsterForUser1.getName().equals(cardName)&&monsterForUser1.deck==null) {
                        user.allMonsters.remove(monsterForUser1);
                        user.getDeckByName(deckName).allMonsterForUser.add(monsterForUser1);
                        monsterForUser1.deck = user.getDeckByName(deckName);
                    }
                }
            }
            else {
                System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
            }
        }
        else {
            System.out.println("main deck is full");
        }
    }
}

            }

                    if(!cardExist)
                    for(TrapCardForUser trapCardForUser:user.allTraps){
                    if(trapCardForUser.getName().equals(cardName)){
                    cardExist=true;
                    /////////////
                    }
                    }
                    if(!cardExist){
                    for(SpellCardForUser spellCardForUser:user.allSpells){
                    if(spellCardForUser.getName().equals(cardName)){
                    cardExist=true;
                    ///////////////
                    }
                    }

                    }
                    }
                    }
                    }