package sample;

import sample.controller.BuyCard;
import sample.controller.MonsterAdderToDeck;
import sample.controller.SpellAdderToDeck;
import sample.controller.UserLogined;
import sample.model.Card.CartReader;
import sample.model.Card.MonsterForUser;
import sample.model.Card.SpellCardForUser;
import sample.model.Deck;
import sample.model.User;
import sample.view.graphic.Start;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        new CartReader();
/*
//<user2>
        User naweed = new User("naweed", "ahmadvand", "321");
        UserLogined.user = naweed;
        BuyCard.BuyCard("Battle Ox", naweed);
        BuyCard.BuyCard("Battle Ox", naweed);
        BuyCard.BuyCard("Battle Ox", naweed);
        BuyCard.BuyCard("Axe Raider", naweed);
        BuyCard.BuyCard("Axe Raider", naweed);
        BuyCard.BuyCard("Axe Raider", naweed);
        BuyCard.BuyCard("Yomi Ship", naweed);
        BuyCard.BuyCard("Yomi Ship", naweed);
        BuyCard.BuyCard("Yomi Ship", naweed);
        BuyCard.BuyCard("Horn Imp", naweed);
        BuyCard.BuyCard("Horn Imp", naweed);
        BuyCard.BuyCard("Horn Imp", naweed);
        BuyCard.BuyCard("Silver Fang", naweed);
        BuyCard.BuyCard("Silver Fang", naweed);
        BuyCard.BuyCard("Silver Fang", naweed);
        BuyCard.BuyCard("Suijin", naweed);
        BuyCard.BuyCard("Suijin", naweed);
        BuyCard.BuyCard("Suijin", naweed);
        BuyCard.BuyCard("Fireyarou", naweed);
        BuyCard.BuyCard("Fireyarou", naweed);
        BuyCard.BuyCard("Fireyarou", naweed);
        BuyCard.BuyCard("Curtain of the dark ones", naweed);
        BuyCard.BuyCard("Curtain of the dark ones", naweed);
        BuyCard.BuyCard("Curtain of the dark ones", naweed);
        BuyCard.BuyCard("Feral Imp", naweed);
        BuyCard.BuyCard("Feral Imp", naweed);
        BuyCard.BuyCard("Dark magician", naweed);
        BuyCard.BuyCard("Dark magician", naweed);
        BuyCard.BuyCard("Wattkid", naweed);
        BuyCard.BuyCard("Wattkid", naweed);
        BuyCard.BuyCard("Wattkid", naweed);
        BuyCard.BuyCard("Baby dragon", naweed);
        BuyCard.BuyCard("Baby dragon", naweed);
        BuyCard.BuyCard("Yami", naweed);
        BuyCard.BuyCard("Yami", naweed);
        BuyCard.BuyCard("Yami", naweed);
        BuyCard.BuyCard("Hero of the east", naweed);
        BuyCard.BuyCard("Hero of the east", naweed);
        BuyCard.BuyCard("Hero of the east", naweed);
        BuyCard.BuyCard("Battle warrior", naweed);
        BuyCard.BuyCard("Raigeki", naweed);
        BuyCard.BuyCard("Raigeki", naweed);
        BuyCard.BuyCard("Raigeki", naweed);
        BuyCard.BuyCard("Pot of Greed", naweed);
        BuyCard.BuyCard("Pot of Greed", naweed);
        BuyCard.BuyCard("Pot of Greed", naweed);
        BuyCard.BuyCard("Dark Hole", naweed);
        BuyCard.BuyCard("Dark Hole", naweed);
        BuyCard.BuyCard("Dark Hole", naweed);
        BuyCard.BuyCard("Black Pendant", naweed);
        BuyCard.BuyCard("Black Pendant", naweed);
        BuyCard.BuyCard("Black Pendant", naweed);

        Deck deck1 = new Deck(naweed, "naweeds deck");

        for (MonsterForUser monsterForUser : naweed.allMonsters) {
            MonsterAdderToDeck.AddMonsterToMain(deck1, monsterForUser);
        }

        for (SpellCardForUser spellCardForUser : naweed.allSpells) {
            SpellAdderToDeck.AddSpellToMain(deck1, spellCardForUser);
        }
        UserLogined.user.hasActiveDeck = true;
        UserLogined.user.setActiveDeck(deck1);
        UserLogined.opponent = naweed;
        //</user2>

        //<user1>
        User arian = new User("arian", "qhzvini", "123");
        UserLogined.user = arian;

        BuyCard.BuyCard("Battle Ox", arian);
        BuyCard.BuyCard("Battle Ox", arian);
        BuyCard.BuyCard("Battle Ox", arian);
        BuyCard.BuyCard("Axe Raider", arian);
        BuyCard.BuyCard("Axe Raider", arian);
        BuyCard.BuyCard("Axe Raider", arian);
        BuyCard.BuyCard("Yomi Ship", arian);
        BuyCard.BuyCard("Yomi Ship", arian);
        BuyCard.BuyCard("Yomi Ship", arian);
        BuyCard.BuyCard("Horn Imp", arian);
        BuyCard.BuyCard("Horn Imp", arian);
        BuyCard.BuyCard("Horn Imp", arian);
        BuyCard.BuyCard("Silver Fang", arian);
        BuyCard.BuyCard("Silver Fang", arian);
        BuyCard.BuyCard("Silver Fang", arian);
        BuyCard.BuyCard("Suijin", arian);
        BuyCard.BuyCard("Suijin", arian);
        BuyCard.BuyCard("Suijin", arian);
        BuyCard.BuyCard("Fireyarou", arian);
        BuyCard.BuyCard("Fireyarou", arian);
        BuyCard.BuyCard("Fireyarou", arian);
        BuyCard.BuyCard("Curtain of the dark ones", arian);
        BuyCard.BuyCard("Curtain of the dark ones", arian);
        BuyCard.BuyCard("Curtain of the dark ones", arian);
        BuyCard.BuyCard("Feral Imp", arian);
        BuyCard.BuyCard("Feral Imp", arian);
        BuyCard.BuyCard("Dark magician", arian);
        BuyCard.BuyCard("Dark magician", arian);
        BuyCard.BuyCard("Wattkid", arian);
        BuyCard.BuyCard("Wattkid", arian);
        BuyCard.BuyCard("Wattkid", arian);
        BuyCard.BuyCard("Baby dragon", arian);
        BuyCard.BuyCard("Baby dragon", arian);
        BuyCard.BuyCard("Yami", arian);
        BuyCard.BuyCard("Yami", arian);
        BuyCard.BuyCard("Yami", arian);
        BuyCard.BuyCard("Hero of the east", arian);
        BuyCard.BuyCard("Hero of the east", arian);
        BuyCard.BuyCard("Hero of the east", arian);
        BuyCard.BuyCard("Battle warrior", arian);
        BuyCard.BuyCard("Raigeki", arian);
        BuyCard.BuyCard("Raigeki", arian);
        BuyCard.BuyCard("Raigeki", arian);
        BuyCard.BuyCard("Pot of Greed", arian);
        BuyCard.BuyCard("Pot of Greed", arian);
        BuyCard.BuyCard("Pot of Greed", arian);
        BuyCard.BuyCard("Dark Hole", arian);
        BuyCard.BuyCard("Dark Hole", arian);
        BuyCard.BuyCard("Dark Hole", arian);
        BuyCard.BuyCard("Black Pendant", arian);
        BuyCard.BuyCard("Black Pendant", arian);
        BuyCard.BuyCard("Black Pendant", arian);
        BuyCard.BuyCard("Advanced Ritual Art", arian);
        BuyCard.BuyCard("Advanced Ritual Art", arian);
        BuyCard.BuyCard("Advanced Ritual Art", arian);
        BuyCard.BuyCard("Sword Of dark destruction", arian);
        BuyCard.BuyCard("Sword Of dark destruction", arian);
        BuyCard.BuyCard("Sword Of dark destruction", arian);
        BuyCard.BuyCard("Sword Of dark destruction", arian);
        BuyCard.BuyCard("Sword Of dark destruction", arian);
        BuyCard.BuyCard("Sword Of dark destruction", arian);
        BuyCard.BuyCard("Sword Of dark destruction", arian);
        BuyCard.BuyCard("Sword Of dark destruction", arian);
        BuyCard.BuyCard("Sword Of dark destruction", arian);


        Deck deck = new Deck(arian, "arians deck");

        for (MonsterForUser monsterForUser : arian.allMonsters) {
            MonsterAdderToDeck.AddMonsterToMain(deck, monsterForUser);
        }
        for (SpellCardForUser spellCardForUser : arian.allSpells) {
            SpellAdderToDeck.AddSpellToMain(deck, spellCardForUser);
        }


        UserLogined.user.hasActiveDeck = true;
        UserLogined.user.setActiveDeck(deck);


        //</user1>
        //</temp>


 */



        //new ProgramController();

        Start.main(args);
    }

}