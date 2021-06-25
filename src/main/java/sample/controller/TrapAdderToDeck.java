package sample.controller;

import sample.model.Card.SpellCardForUser;
import sample.model.Card.TrapCardForUser;
import sample.model.Deck;

public class TrapAdderToDeck {
    public static String AddTrapToMain(Deck deck, TrapCardForUser trapCardForUser) {
        if (deck.numberOfCardsInMain >= 60) {
            return "full";
        } else {
//3 ta bishtar nabashe:
            int check = 0;
            for (TrapCardForUser trapCardForUser1:deck.allTrapCardsForUserMain) {
                if (trapCardForUser1.getName().equals(trapCardForUser.getName())) {
                    check++;
                }
                if (check == 3) break;
            }
            for (TrapCardForUser trapCardForUser1:deck.allTrapCardsForUserSide) {
                if (trapCardForUser1.getName().equals(trapCardForUser.getName())) {
                    check++;
                }
                if (check == 3) break;
            }
            //

            if (check >= 3) {
                return "three";
            } else {
                if (!trapCardForUser.isInDeck) {
                    UserLogined.user.allTraps.remove(trapCardForUser);
                    deck.allTrapCardsForUserMain.add(trapCardForUser);
                    deck.numberOfCardsInMain++;
                    trapCardForUser.deck = deck;
                    trapCardForUser.isInDeck = true;
                    return "done";
                } else {
                    return "card is in a deck!!!!!!!!!!!!!!!!!!!!!!!!!!!";
                }
            }

        }
    }

    public static String AddTrapToSideDeck(Deck deck, TrapCardForUser trapCardForUser) {
        if (deck.numberOfCardsInSide >= 15) {
            return "full";
        } else {
            deck.allTrapCardsForUserSide.add(trapCardForUser);
            deck.allTrapCardsForUserMain.remove(trapCardForUser);
            deck.numberOfCardsInMain--;
            deck.numberOfCardsInSide++;
            return "done";
        }
    }

    public static String AddTrapToUser(Deck deck, TrapCardForUser trapCardForUser) {
        deck.allTrapCardsForUserSide.remove(trapCardForUser);
        deck.user.allTraps.add(trapCardForUser);
        deck.numberOfCardsInSide--;
        trapCardForUser.isInDeck=false;
        trapCardForUser.deck=null;
        return "done";
    }
}
