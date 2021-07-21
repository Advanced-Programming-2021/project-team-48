package sample;

import sample.controller.*;
import sample.model.Card.*;
import sample.model.Deck;
import sample.model.User;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramController {
    public static Scanner scanner = new Scanner(System.in);


    public ProgramController() throws Exception {
        run();
    }

    public static void run() throws Exception {
        System.out.println("Login Menu");
        System.out.println("0.exit");
        System.out.println("1.creat user");
        System.out.println("2.login user");
        String input = scanner.nextLine();
        while (!input.equals("menu exit") && !input.equals("0")) {

            boolean checker = false;
            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("Login Menu");
            }
            if (input.equals("1")) {
                checker = true;
                System.out.println("enter username:");
                String username = scanner.nextLine();
                System.out.println("enter nickname:");
                String nickname = scanner.nextLine();
                System.out.println("enter password");
                String password = scanner.nextLine();
                System.out.println(SignupController.creatUser(username, nickname, password));
            }


            pattern = Pattern.compile("user create --username ([\\S]+) --nickname ([\\S]+) --password ([\\S]+)");
            matcher = pattern.matcher(input);

            pattern = Pattern.compile("user create --nickname ([^\\s]+) --username ([^\\s]+) --password ([^\\s]+)");
            Matcher matcher1 = pattern.matcher(input);

            pattern = Pattern.compile("user create --username ([^\\s]+) --password ([^\\s]+) --nickname ([^\\s]+)");
            Matcher matcher2 = pattern.matcher(input);

            pattern = Pattern.compile("user create --nickname ([^\\s]+) --password ([^\\s]+) --username ([^\\s]+)");
            Matcher matcher3 = pattern.matcher(input);

            pattern = Pattern.compile("user create --password ([^\\s]+) --username ([^\\s]+) --nickname ([^\\s]+)");
            Matcher matcher4 = pattern.matcher(input);

            pattern = Pattern.compile("user create --password ([^\\s]+) --nickname ([^\\s]+) --username ([^\\s]+)");
            Matcher matcher5 = pattern.matcher(input);


            String username = "";
            String nickname = "";
            String password = "";
            if (matcher.find()) {
                checker = true;
                username = matcher.group(1);
                nickname = matcher.group(2);
                password = matcher.group(3);
                System.out.println(SignupController.creatUser(username, nickname, password));
            } else if (matcher1.find()) {
                checker = true;
                username = matcher1.group(2);
                nickname = matcher1.group(1);
                password = matcher1.group(3);
                System.out.println(SignupController.creatUser(username, nickname, password));
            } else if (matcher2.find()) {
                checker = true;
                username = matcher2.group(1);
                nickname = matcher2.group(3);
                password = matcher2.group(2);
                System.out.println(SignupController.creatUser(username, nickname, password));
            } else if (matcher3.find()) {
                checker = true;

                username = matcher3.group(3);
                nickname = matcher3.group(1);
                password = matcher3.group(2);
                System.out.println(SignupController.creatUser(username, nickname, password));
            } else if (matcher4.find()) {
                checker = true;
                username = matcher4.group(2);
                nickname = matcher4.group(3);
                password = matcher4.group(1);
                System.out.println(SignupController.creatUser(username, nickname, password));
            } else if (matcher5.find()) {
                checker = true;
                username = matcher5.group(3);
                nickname = matcher5.group(2);
                password = matcher5.group(1);
                System.out.println(SignupController.creatUser(username, nickname, password));
            }


            if (input.equals("2")) {
                checker = true;
                System.out.println("enter username:");
                username = scanner.nextLine();
                System.out.println("enter password:");
                password = scanner.nextLine();
                String nextStep = LoginController.login(username, password);
                if (nextStep.equals("OK")) {
                    System.out.println("user logged in successfully!");
                    mainMenu(User.getUserByUsername(username));
                } else System.out.println(nextStep);
            }

            pattern = Pattern.compile("user login --username ([^\\s]+) --password ([^\\s]+)");
            matcher = pattern.matcher(input);

            pattern = Pattern.compile("user login --password ([^\\s]+) --username ([^\\s]+)");
            matcher1 = pattern.matcher(input);


            username = "";
            password = "";
            if (matcher.find()) {
                checker = true;
                username = matcher.group(1);
                password = matcher.group(2);
                String nextStep = LoginController.login(username, password);
                if (nextStep.equals("OK")) {
                    System.out.println("user logged in successfully!");
                    mainMenu(User.getUserByUsername(username));
                } else System.out.println(nextStep);
            } else if (matcher1.find()) {
                checker = true;
                username = matcher1.group(2);
                password = matcher.group(1);
                String nextStep = LoginController.login(username, password);
                if (nextStep.equals("OK")) {
                    System.out.println("user logged in successfully!");
                    mainMenu(User.getUserByUsername(username));
                } else System.out.println(nextStep);
            }


            if (!checker) {
                System.out.println("please login first");
            }
            System.out.println("____________________________________________");
            System.out.println("Login Menu");
            System.out.println("0.exit");
            System.out.println("1.creat user");
            System.out.println("2.login user");
            input = scanner.nextLine();
        }

    }


    public static void mainMenu(User user) throws Exception {
        System.out.println("Main Menu");
        System.out.println("0.log out");
        System.out.println("1.enter scoreboard menu");
        System.out.println("2.enter profile menu");
        System.out.println("3.enter deck menu");
        System.out.println("4.enter shop");
        System.out.println("5.duel");
        String input = scanner.nextLine();

        while (true) {
            boolean checker = false;
            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("Main Menu");
            }


            if (input.equals("user logout") || input.equals("menu exit") || input.equals("0")) {
                System.out.println("user logged out successfully!");
                return;
            }

            pattern = Pattern.compile("menu enter scoreboard");
            matcher = pattern.matcher(input);
            if (matcher.find() || input.equals("1")) {
                checker = true;
                scorebosrd();
            }

            pattern = Pattern.compile("menu enter profile");
            matcher = pattern.matcher(input);
            if (matcher.find() || input.equals("2")) {
                checker = true;
                profile(user);
            }

            pattern = Pattern.compile("menu enter deck");
            matcher = pattern.matcher(input);
            if (matcher.find() || input.equals("3")) {
                checker = true;
                deck(user);
            }

            pattern = Pattern.compile("menu enter shop");
            matcher = pattern.matcher(input);
            if (matcher.find() || input.equals("4")) {
                checker = true;
                shop(user);
            }

            if (input.equals("5")) {
                checker = true;
                System.out.println("enter second player username:");
                String user2Username = scanner.nextLine();
                System.out.println("enter numbers of rounds:");
                String temp = scanner.nextLine();
                pattern = Pattern.compile("\\d+");
                matcher = pattern.matcher(temp);
                if (matcher.find()) {
                    int round = Integer.parseInt(temp);
                    System.out.println(StartGameController.Game(user2Username, user, round));
                } else System.out.println("invalid round");
            }
            pattern = Pattern.compile("duel --new --second-player ([^\\s]+) --rounds ([\\d])");
            matcher = pattern.matcher(input);

            pattern = Pattern.compile("duel --new --rounds ([\\d]) --second-player ([^\\s]+)");
            Matcher matcher1 = pattern.matcher(input);


            String user2Username = "";
            int round;
            if (matcher.find()) {
                checker = true;
                user2Username = matcher.group(1);
                String temp = matcher.group(2);
                round = Integer.parseInt(temp);
                System.out.println(StartGameController.Game(user2Username, user, round));
            } else if (matcher1.find()) {
                checker = true;
                user2Username = matcher1.group(2);
                String temp = matcher1.group(1);
                round = Integer.parseInt(temp);
                System.out.println(StartGameController.Game(user2Username, user, round));
            }


            if (!checker) {
                System.out.println("invalid input");
            }

            System.out.println("Main Menu");
            System.out.println("0.log out");
            System.out.println("1.enter scoreboard menu");
            System.out.println("2.enter profile menu");
            System.out.println("3.enter deck menu");
            System.out.println("4.enter shop");
            System.out.println("5.duel");
            input = scanner.nextLine();
        }
    }


    public static void scorebosrd() {
        System.out.println("Socreborad Menu");
        System.out.println("0.enter main menu");
        System.out.println("1.scoreboard show");
        String input = scanner.nextLine();
        while (!input.equals("menu exit") && !input.equals("0")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("Scoreboard Menu");
            }

            pattern = Pattern.compile("scoreboard show");
            matcher = pattern.matcher(input);
            if (matcher.find() || input.equals("1")) {
                checker = true;
                SortUserList.Sort();
                int j = 1;
                for (int i = 0; i < User.getListOfUsers().size(); i++) {
                    System.out.println(j + "- " + User.getListOfUsers().get(i).getNickname() + ": " + User.getListOfUsers().get(i).getScore());
                    if (i + 1 < User.getListOfUsers().size())
                        if (User.getListOfUsers().get(i + 1) != User.getListOfUsers().get(i)) j++;
                }
            }

            if (!checker) {
                System.out.println("invalid command");
            }
            System.out.println("Socreborad Menu");
            System.out.println("0.enter main menu");
            System.out.println("1.scoreboard show");
            input = scanner.nextLine();
        }
    }

    private static void shop(User user) {
        System.out.println("Shop Mneu");
        System.out.println("0.enter main menu");
        System.out.println("1.show a card");
        System.out.println("2.buy a card");
        System.out.println("3.show all card names");
        String input = scanner.nextLine();
        while (true) {
            boolean checker = false;

            if (input.equals("getmore")) {
                checker = true;
                System.out.println("you got 1000");
                user.setMoney(user.getMoney() + 1000);
            }

            if (input.equals("show money")) {
                checker = true;
                System.out.println(user.getMoney());
            }

            if (input.equals("menu show-current")) {
                checker = true;
                System.out.println("Shop Menu");
            }

            if (input.equals("menu exit") || input.equals("0")) {
                break;
            }

            if (input.equals("1")) {
                checker = true;
                System.out.println("enter card name to show:");
                String cardName = scanner.nextLine();
                CardShow(cardName);
            }
            Pattern pattern = Pattern.compile("card show ([^\\s]+)");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String cardName = matcher.group(1);
                CardShow(cardName);
            }

            if (input.equals("2")) {
                checker = true;
                System.out.println("enter card name to buy:");
                String cardName = scanner.nextLine();
                System.out.println(BuyCard.BuyCard(cardName, user));
            }

            pattern = Pattern.compile("^shop buy ([^\\s]+)$");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String cardName = matcher.group(1);
                System.out.println(BuyCard.BuyCard(cardName, user));
            }

            pattern = Pattern.compile("^shop show --all$");
            matcher = pattern.matcher(input);
            if (matcher.find() || input.equals("3")) {
                checker = true;
                showShop();
            }

            if (!checker) {
                System.out.println("invalid command");
            }
            System.out.println("Shop Mneu");
            System.out.println("0.enter main menu");
            System.out.println("1.show a card");
            System.out.println("2.buy a card");
            System.out.println("3.show all card names");
            input = scanner.nextLine();
        }
    }


    public static void profile(User user) {
        System.out.println("Profile Menu");
        System.out.println("0.enter main menu");
        System.out.println("1.change nickname");
        System.out.println("2.change password");
        String input = scanner.nextLine();
        while (!input.equals("menu exit") && !input.equals("0")) {
            boolean checker = false;


            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("profile Menu");
            }


            if (input.equals("1")) {
                checker = true;
                System.out.println("enter new nickname:");
                String newNickname = scanner.nextLine();
                NicknameChangeController.changeNickname(newNickname, user);
            }
            pattern = Pattern.compile("profile change --nickname ([^\\s]+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String newNickname = matcher.group(1);
                System.out.println(NicknameChangeController.changeNickname(newNickname, user));
            }

            if (input.equals("2")) {
                checker = true;
                System.out.println("enter old password:");
                String oldPassword = scanner.nextLine();
                System.out.println("enter new password:");
                String newPassword = scanner.nextLine();
                String nextStep = PasswordChangeController.changePassword(oldPassword, newPassword, user);
                System.out.println(nextStep);

            }

            pattern = Pattern.compile("profile change --password --current ([^\\s]+) --new ([^\\s]+)");
            matcher = pattern.matcher(input);

            pattern = Pattern.compile("profile change --password --new ([^\\s]+) --current ([^\\s]+)");
            Matcher matcher1 = pattern.matcher(input);


            String oldPassword = "";
            String newPassword = "";
            if (matcher.find()) {
                checker = true;
                oldPassword = matcher.group(1);
                newPassword = matcher.group(2);
                String nextStep = PasswordChangeController.changePassword(oldPassword, newPassword, user);
                System.out.println(nextStep);
            } else if (matcher1.find()) {
                checker = true;
                oldPassword = matcher1.group(2);
                newPassword = matcher1.group(1);
                String nextStep = PasswordChangeController.changePassword(oldPassword, newPassword, user);
                System.out.println(nextStep);
            }


            if (!checker) {
                System.out.println("invalid command");
            }

            System.out.println("Profile Menu");
            System.out.println("0.enter main menu");
            System.out.println("1.change nickname");
            System.out.println("2.change password");
            input = scanner.nextLine();

        }
    }


    public static void deck(User user) {
        System.out.println("Deck Menu");
        System.out.println("0. enter main menu");
        System.out.println("1. show a card");
        System.out.println("2. creat a new deck");
        System.out.println("3. delete a deck");
        System.out.println("4. set a deck active");
        System.out.println("5. add a card to a deck");
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!7
        System.out.println("6. add a card to a sidedeck");
        System.out.println("7. show all decks");
        System.out.println("8. show a deck");
        System.out.println("9. show a side deck");
        System.out.println("10. show all card names");
        String input = scanner.nextLine();
        while (!input.equals("menu exit") && !input.equals("0")) {
            boolean checker = false;

            Pattern pattern = Pattern.compile("menu show-current");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                System.out.println("deck Menu");
            }

            if (input.equals("1")) {
                checker = true;
                System.out.println("enter card name:");
                String cardName = scanner.nextLine();
                CardShow(cardName);
            }

            pattern = Pattern.compile("card show ([^\\s+]+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String cardName = matcher.group(1);
                CardShow(cardName);
            }

            if (input.equals("2")) {
                checker = true;
                System.out.println("enter deck name:");
                String name = scanner.nextLine();
                System.out.println(DeckCreat.creat(name, user));
            }

            pattern = Pattern.compile("deck create ([^\\s]+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String name = matcher.group(1);
                System.out.println(DeckCreat.creat(name, user));
            }

            if (input.equals("3")) {
                checker = true;
                String name = scanner.nextLine();
                System.out.println(DeleteDeck.deleteConsole(name, user));
            }
            pattern = Pattern.compile("deck delete ([^\\s]+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String name = matcher.group(1);
                System.out.println(DeleteDeck.deleteConsole(name, user));
            }

            if (input.equals("4")) {
                checker = true;
                String name = scanner.nextLine();
                setActiveDeck(name, user);
            }
            pattern = Pattern.compile("deck set-activate ([^\\s]+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String name = matcher.group(1);
                System.out.println(setActiveDeck(name, user));
            }

            if (input.equals("5")) {
                checker = true;
                System.out.println("enter card name:");
                String cardName = scanner.nextLine();
                System.out.println("enter deck name:");
                String deckName = scanner.nextLine();
                System.out.println(CardAdder(cardName, deckName, user));
            }

            pattern = Pattern.compile("^deck add-card --card ([^\\s]+) --deck ([^\\s]+)$");
            matcher = pattern.matcher(input);

            pattern = Pattern.compile("^deck add-card --deck ([^\\s]+) --card ([^\\s]+)$");
            Matcher matcher1 = pattern.matcher(input);

            String cardName = "";
            String deckName = "";
            if (matcher.find()) {
                checker = true;
                cardName = matcher.group(1);
                deckName = matcher.group(2);
                System.out.println(CardAdder(cardName, deckName, user));
            } else if (matcher1.find()) {
                checker = true;
                cardName = matcher1.group(2);
                deckName = matcher1.group(1);
                System.out.println(CardAdder(cardName, deckName, user));
            }


            if (input.equals("6")) {
                checker = true;
                System.out.println("enter card name:");
                cardName = scanner.nextLine();
                System.out.println("enter (side)deck name:");
                deckName = scanner.nextLine();
                CardAdderSidedeck(cardName, deckName, user);
            }

            pattern = Pattern.compile("^deck add-card --card ([^\\s]+) --deck ([^\\s]+) --side$");
            matcher = pattern.matcher(input);

            pattern = Pattern.compile("^deck add-card --deck ([^\\s]+) --side --card ([^\\s]+)$");
            matcher1 = pattern.matcher(input);

            pattern = Pattern.compile("^deck add-card --deck ([^\\s]+) --card ([^\\s]+) --side$");
            Matcher matcher2 = pattern.matcher(input);

            pattern = Pattern.compile("^deck add-card --deck ([^\\s]+) --side --card ([^\\s]+)$");
            Matcher matcher3 = pattern.matcher(input);


            cardName = "";
            deckName = "";
            if (matcher.find()) {
                checker = true;
                cardName = matcher.group(1);
                deckName = matcher.group(2);
                CardAdderSidedeck(cardName, deckName, user);
            } else if (matcher1.find()) {
                checker = true;
                cardName = matcher1.group(2);
                deckName = matcher1.group(1);
                CardAdderSidedeck(cardName, deckName, user);
            } else if (matcher2.find()) {
                checker = true;
                cardName = matcher2.group(2);
                deckName = matcher2.group(1);
                CardAdderSidedeck(cardName, deckName, user);
            } else if (matcher3.find()) {
                checker = true;
                cardName = matcher3.group(2);
                deckName = matcher3.group(1);
                CardAdderSidedeck(cardName, deckName, user);
            }


            pattern = Pattern.compile("deck show --all");
            matcher = pattern.matcher(input);
            if (matcher.find() || input.equals("7")) {
                checker = true;
                showAllDecks(user);
            }

            if (input.equals("8")) {
                checker = true;
                System.out.println("enter deck name:");
                String name = scanner.nextLine();
                showDeckMainExistChecker(name, user);
            }
            pattern = Pattern.compile("^deck show --deck-name ([^\\s]+)$");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String name = matcher.group(1);
                showDeckMainExistChecker(name, user);
            }

            if (input.equals("9")) {
                checker = true;
                String name = scanner.nextLine();
                showDeckSideExistChecker(name, user);
            }
            pattern = Pattern.compile("^deck show --deck-name ([^\\s]+) --side$");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                String name = matcher.group(1);
                showDeckSideExistChecker(name, user);
            }

            pattern = Pattern.compile("^deck show --cards$");
            matcher = pattern.matcher(input);
            if (matcher.find() || input.equals("10")) {
                checker = true;
                ShowAllDeckCards(user);
            }
            if (!checker) {
                System.out.println("invalid command");

            }

            System.out.println("Deck Menu");
            System.out.println("0.enter main menu");
            System.out.println("1.show a card");
            System.out.println("2.creat a new deck");
            System.out.println("3.delete a deck");
            System.out.println("4.set a deck active");
            System.out.println("5.add a card to a deck");
            System.out.println("6.add a card to a sidedeck");
            System.out.println("7.show all decks");
            System.out.println("8.show a deck");
            System.out.println("9.show a side deck");
            System.out.println("10.show all card names");
            input = scanner.nextLine();
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------


    private static void showDeckSideExistChecker(String name, User user) {
        boolean exist = false;
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

    private static void showDeckMainExistChecker(String name, User user) {
        boolean exist = false;
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

    private static void showAllDecks(User user) {
        if (user.getActiveDeck() != null) {
            System.out.println("Decks:");
            System.out.println("Active deck:");
            System.out.println(user.getActiveDeck().getName() + ": main deck" + user.getActiveDeck().numberOfCardsInMain + ", side deck" + user.getActiveDeck().numberOfCardsInSide + ", " + user.getActiveDeck().isValid());
            System.out.println("Other decks:");
            if (!user.allDecks.isEmpty())
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

                System.out.println(deck.getName() + ": main deck" + deck.numberOfCardsInMain + ", side deck" + deck.numberOfCardsInSide + ", " + deck.isValid());

            }
        }
    }

    private static String setActiveDeck(String name, User user) {
        boolean exist = false;
        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(name)) {
                exist = true;
                break;
            }
        }
        if (exist) {
            user.setActiveDeck(user.getDeckByName(name));
            user.hasActiveDeck = true;
            return ("deck activated successfully");
        } else {
            return ("deck with name " + name + " does not exist");
        }
    }


    public static void CardShow(String cardName) {
        for (MonsterCard monsterCard : MonsterCard.getAllMonsterCards()) {
            if (monsterCard.getName().equals(cardName)) {

                //???????????????????????????????????????????
                ShowMonster(cardName);
                return;
            }
        }
        for (SpellCard spellCard : SpellCard.getAllSpellCard()) {
            if (spellCard.getName().equals(cardName)) {
                ShowSpell(cardName);
                return;
            }
        }
        for (TrapCard trapCard : TrapCard.getAllTrapCard()) {
            if (trapCard.getName().equals(cardName)) {
                ShowTrap(cardName);
                return;
            }
        }
    }

    public static void ShowMonster(String monsterName) {
        System.out.println("Name: " + monsterName);
        System.out.println("Level: " + MonsterCard.getMonsterCardByName(monsterName).level);
        System.out.println("Type: " + MonsterCard.getMonsterCardByName(monsterName).monsterType);
        System.out.println("ATK: " + MonsterCard.getMonsterCardByName(monsterName).ATK);
        System.out.println("DEF: " + MonsterCard.getMonsterCardByName(monsterName).DEF);
        System.out.println("Description: " + MonsterCard.getMonsterCardByName(monsterName).getDescription());
    }

    private static void ShowSpell(String spellName) {
        System.out.println("Name: " + spellName);
        System.out.println("Spell");
        System.out.println("Type: " + SpellCard.getSpellCardByName(spellName).getProperty());
        System.out.println("Description: " + SpellCard.getSpellCardByName(spellName).getDescription());
    }

    private static void ShowTrap(String trapName) {
        System.out.println("Name: " + trapName);
        System.out.println("Trap");
        System.out.println("Type: " + TrapCard.getTrapCardByName(trapName).getProperty());
        System.out.println("Description: " + TrapCard.getTrapCardByName(trapName).getDescription());
    }

    private static void showShop() {
        ArrayList<String> allCard = new ArrayList<>();
        for (Card card : Card.getAllCards()) {
            allCard.add(card.getName());
        }
        Collections.sort(allCard);
        for (String cardName : allCard) {
            System.out.println(cardName + ":" + Card.getCardByName(cardName).getPrice());
        }

    }


    private static void ShowAllDeckCards(User user) {
        ArrayList<String> allOwnedCards = AllOwnedCards.all(user);
        for (String cardName : allOwnedCards) {
            System.out.println(cardName + ":" + Card.getCardByName(cardName).getDescription());
        }
    }


    private static void ShowMainDeck(Deck deck) {
        System.out.println("Deck: " + deck.getName());
        System.out.println("Side/Main deck: Main");
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
        System.out.println("Side/Main deck: Side");
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

    private static String CardAdder(String cardName, String deckName, User user) {
        boolean cardExist = false;

        for (MonsterForUser monsterForUser : user.allMonsters) {
            if (monsterForUser.getName().equals(cardName)) {
                cardExist = true;
                break;
            }
        }
        if (cardExist) {
            return MonsterAdder(cardName, deckName, user);
        } else {
            for (SpellCardForUser spellCardForUser : user.allSpells) {
                if (spellCardForUser.getName().equals(cardName)) {
                    cardExist = true;
                    break;
                }
            }
            if (cardExist) {
                return SpellAdder(cardName, deckName, user);
            } else {
                for (TrapCardForUser trapCardForUser : user.allTraps) {
                    if (trapCardForUser.getName().equals(cardName)) {
                        cardExist = true;
                        break;
                    }
                }
                if (cardExist) {
                    return TrapAdder(cardName, deckName, user);
                } else {
                    return "card with name " + cardName + " does not exist";

                }
            }
        }
    }

    private static String MonsterAdder(String cardName, String deckName, User user) {
        boolean deckExist = false;
        int check = 0;

        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(deckName)) {
                deckExist = true;
            }
        }

        if (!deckExist) {
            return ("deck with name " + deckName + " does not exist");

        } else if (user.getDeckByName(deckName).numberOfCardsInMain < 60) {

            for (MonsterForUser monsterForUser1 : user.getDeckByName(deckName).allMonsterForUserMain) {
                if (monsterForUser1.getName().equals(cardName)) {
                    check++;
                }
            }
            for (MonsterForUser monsterForUser1 : user.getDeckByName(deckName).allMonsterForUserSide) {
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
                        return "card added to deck successfully";
                    }
                }
            } else {
                return ("there are already three cards with name " + cardName + " in deck " + deckName);
            }
        } else {
            return ("main deck is full");
        }
        return "error";
    }


    private static String SpellAdder(String cardName, String deckName, User user) {
        boolean deckExist = false;
        int check = 0;

        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(deckName)) {
                deckExist = true;
            }
        }

        if (!deckExist) {
            return ("deck with name " + deckName + " does not exist");

        }

        if (user.getDeckByName(deckName).numberOfCardsInMain < 60) {

            for (SpellCardForUser spellCardForUser : user.getDeckByName(deckName).allSpellCardsForUserMain) {
                if (spellCardForUser.getName().equals(cardName)) {
                    check++;
                }
            }
            for (SpellCardForUser spellCardForUser : user.getDeckByName(deckName).allSpellCardsForUserSide) {
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
                        return "card added to deck successfully";
                    }
                }
            } else {
                return ("there are already three cards with name " + cardName + " in deck " + deckName);
            }
        } else {
            return ("main deck is full");
        }
        return "error";
    }


    private static String TrapAdder(String cardName, String deckName, User user) {
        boolean deckExist = false;
        int check = 0;

        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(deckName)) {
                deckExist = true;
            }
        }

        if (!deckExist) {
            return ("deck with name " + deckName + " does not exist");

        }

        if (user.getDeckByName(deckName).numberOfCardsInMain < 60) {

            for (TrapCardForUser trapCardForUser : user.getDeckByName(deckName).allTrapCardsForUserMain) {
                if (trapCardForUser.getName().equals(cardName)) {
                    check++;
                }
            }
            for (TrapCardForUser trapCardForUser : user.getDeckByName(deckName).allTrapCardsForUserSide) {
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
                        return "card added to deck successfully";
                    }
                }
            } else {
                return ("there are already three cards with name " + cardName + " in deck " + deckName);
            }
        } else {
            return ("main deck is full");
        }
        return "error";
    }

    private static void CardAdderSidedeck(String cardName, String deckName, User user) {
        boolean cardExist = false;

        for (MonsterForUser monsterForUser : user.allMonsters) {
            if (monsterForUser.getName().equals(cardName)) {
                cardExist = true;
                break;
            }
        }
        if (cardExist) {
            MonsterAdderSidedeck(cardName, deckName, user);
            return;
        }

        for (SpellCardForUser spellCardForUser : user.allSpells) {
            if (spellCardForUser.getName().equals(cardName)) {
                cardExist = true;
                break;
            }
        }
        if (cardExist) {
            SpellAdderSidedeck(cardName, deckName, user);
            return;
        }

        for (TrapCardForUser trapCardForUser : user.allTraps) {
            if (trapCardForUser.getName().equals(cardName)) {
                cardExist = true;
                break;
            }
        }
        if (cardExist) {
            TrapAdderSidedeck(cardName, deckName, user);
            return;
        }


        if (!cardExist) {
            System.out.println("card with name " + cardName + " does not exist");

        }

    }

    private static void MonsterAdderSidedeck(String cardName, String deckName, User user) {
        boolean deckExist = false;
        int check = 0;

        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(deckName)) {
                deckExist = true;
                break;
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
            for (MonsterForUser monsterForUser1 : user.getDeckByName(deckName).allMonsterForUserMain) {
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
                        System.out.println("card added to side deck successfully");
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
            for (SpellCardForUser spellCardForUser : user.getDeckByName(deckName).allSpellCardsForUserMain) {
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
                        System.out.println("card added to side deck successfully");
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
            for (TrapCardForUser trapCardForUser : user.getDeckByName(deckName).allTrapCardsForUserMain) {
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
                        System.out.println("card added to side deck successfully");
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
