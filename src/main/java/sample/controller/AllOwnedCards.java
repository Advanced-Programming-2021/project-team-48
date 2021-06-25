package sample.controller;

import sample.model.Card.MonsterForUser;
import sample.model.Card.SpellCardForUser;
import sample.model.Card.TrapCardForUser;
import sample.model.Deck;
import sample.model.User;

import java.util.ArrayList;
import java.util.Collections;

public class AllOwnedCards {
    public static ArrayList<String> all(User user){
        ArrayList<String> allOwnedCards = new ArrayList<>();
        for (MonsterForUser monsterForUser : user.allMonsters) {
            allOwnedCards.add(monsterForUser.getName());
        }
        for (SpellCardForUser spellCardForUser : user.allSpells) {
            allOwnedCards.add(spellCardForUser.getName());
        }
        for (TrapCardForUser trapCardForUser : user.allTraps) {
            allOwnedCards.add(trapCardForUser.getName());
        }
        for (Deck deck : user.allDecks) {
            for (MonsterForUser monsterForUser : deck.allMonsterForUserMain) {
                allOwnedCards.add(monsterForUser.getName());
            }
            for (SpellCardForUser spellCardForUser : deck.allSpellCardsForUserMain) {
                allOwnedCards.add(spellCardForUser.getName());
            }
            for (TrapCardForUser trapCardForUser : deck.allTrapCardsForUserMain) {
                allOwnedCards.add(trapCardForUser.getName());
            }
            for (MonsterForUser monsterForUser : deck.allMonsterForUserSide) {
                allOwnedCards.add(monsterForUser.getName());
            }
            for (SpellCardForUser spellCardForUser : deck.allSpellCardsForUserSide) {
                allOwnedCards.add(spellCardForUser.getName());
            }
            for (TrapCardForUser trapCardForUser : deck.allTrapCardsForUserSide) {
                allOwnedCards.add(trapCardForUser.getName());
            }
        }
        Collections.sort(allOwnedCards);
        return allOwnedCards;
    }
}
