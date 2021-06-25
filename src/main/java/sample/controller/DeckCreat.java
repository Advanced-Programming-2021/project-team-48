package sample.controller;

import sample.model.Deck;
import sample.model.User;

public class DeckCreat {
    public static String creat(String name, User user) {
        boolean exist = false;

        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(name)) {
                exist = true;
                break;
            }
        }

        if (!exist) {
            new Deck(user, name);
            return "deck created successfully!";
        }else {
            return "deck with name " + name + " already exists";

        }
    }
}
