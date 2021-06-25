package sample.controller;

import sample.model.Card.MonsterForUser;
import sample.model.Card.SpellCardForUser;
import sample.model.Card.TrapCardForUser;
import sample.model.Deck;
import sample.model.User;

public class DeleteDeck {
    public static String deleteConsole(String name, User user) {
        boolean exist = false;
        for (Deck deck : user.allDecks) {
            if (deck.getName().equals(name)) {
                return deleteDeck(deck, user);
            }
        }
        return "deck with name " + name + " does not exist";
    }

    public static String deleteDeck(Deck deck, User user) {
        for (MonsterForUser monsterForUser : deck.allMonsterForUserMain) {
            user.allMonsters.add(monsterForUser);
            monsterForUser.deck = null;
            monsterForUser.isInDeck = false;
        }
        for (MonsterForUser monsterForUser : deck.allMonsterForUserSide) {
            user.allMonsters.add(monsterForUser);
            monsterForUser.deck = null;
            monsterForUser.isInDeck = false;
        }
        for (SpellCardForUser spellCardForUser : deck.allSpellCardsForUserMain) {
            user.allSpells.add(spellCardForUser);
            spellCardForUser.deck = null;
            spellCardForUser.isInDeck = false;
        }
        for (SpellCardForUser spellCardForUser : deck.allSpellCardsForUserSide) {
            user.allSpells.add(spellCardForUser);
            spellCardForUser.deck = null;
            spellCardForUser.isInDeck = false;
        }
        for (TrapCardForUser trapCardForUser : deck.allTrapCardsForUserMain) {
            user.allTraps.add(trapCardForUser);
            trapCardForUser.deck = null;
            trapCardForUser.isInDeck = false;
        }
        for (TrapCardForUser trapCardForUser : deck.allTrapCardsForUserSide) {
            user.allTraps.add(trapCardForUser);
            trapCardForUser.deck = null;
            trapCardForUser.isInDeck = false;
        }
        if (user.getActiveDeck() != null) {
            if (user.getActiveDeck().getName().equals(deck.getName())) {
                user.setActiveDeck(null);
                user.hasActiveDeck = false;
            }
        }

        user.allDecks.remove(deck);
        return "deck deleted successfully";
    }
}
