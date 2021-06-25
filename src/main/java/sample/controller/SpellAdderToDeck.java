package sample.controller;

import sample.model.Card.MonsterForUser;
import sample.model.Card.SpellCardForUser;
import sample.model.Deck;

public class SpellAdderToDeck {
    public static String AddSpellToMain(Deck deck, SpellCardForUser spellCardForUser) {
        if (deck.numberOfCardsInMain >= 60) {
            return "full";
        } else {
//3 ta bishtar nabashe:
            int check = 0;
            for (SpellCardForUser spellCardForUser1:deck.allSpellCardsForUserMain) {
                if (spellCardForUser1.getName().equals(spellCardForUser.getName())) {
                    check++;
                }
                if (check == 3) break;
            }
            for (SpellCardForUser spellCardForUser1:deck.allSpellCardsForUserSide) {
                if (spellCardForUser1.getName().equals(spellCardForUser.getName())) {
                    check++;
                }
                if (check == 3) break;
            }

            //

            if (check >= 3) {
                return "three";
            } else {
                if (!spellCardForUser.isInDeck) {
                    UserLogined.user.allSpells.remove(spellCardForUser);
                    deck.allSpellCardsForUserMain.add(spellCardForUser);
                    deck.numberOfCardsInMain++;
                    spellCardForUser.deck = deck;
                    spellCardForUser.isInDeck = true;
                    return "done";
                } else {
                    return "card is in a deck!!!!!!!!!!!!!!!!!!!!!!!!!!!";
                }
            }

        }
    }

    public static String AddSpelllToSideDeck(Deck deck, SpellCardForUser spellCardForUser) {
        if (deck.numberOfCardsInSide >= 15) {
            return "full";
        } else {
            deck.allSpellCardsForUserSide.add(spellCardForUser);
            deck.allSpellCardsForUserMain.remove(spellCardForUser);
            deck.numberOfCardsInMain--;
            deck.numberOfCardsInSide++;
            return "done";
        }
    }

    public static String AddMonsterToUser(Deck deck, SpellCardForUser spellCardForUser) {
        deck.allSpellCardsForUserSide.remove(spellCardForUser);
        deck.user.allSpells.add(spellCardForUser);
        deck.numberOfCardsInSide--;
        spellCardForUser.isInDeck=false;
        spellCardForUser.deck=null;
        return "done";
    }
}
