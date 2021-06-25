package sample.controller;

import sample.model.Card.MonsterForUser;
import sample.model.Deck;

public class MonsterAdderToDeck {
    public static String AddMonsterToMain(Deck deck, MonsterForUser monsterForUser) {
        if (deck.numberOfCardsInMain >= 60) {
            return "full";
        } else {
//3 ta bishtar nabashe:
            int check = 0;
            for (MonsterForUser monster : deck.allMonsterForUserMain) {
                if (monsterForUser.getName().equals(monster.getName())) {
                    check++;
                }
                if (check == 3) break;
            }
            for (MonsterForUser monster : deck.allMonsterForUserSide) {
                if (monsterForUser.getName().equals(monster.getName())) {
                    check++;
                }
                if (check == 3) break;
            }
            //

            if (check >= 3) {
                return "three";
            } else {
                if (!monsterForUser.isInDeck) {
                    UserLogined.user.allMonsters.remove(monsterForUser);
                    deck.allMonsterForUserMain.add(monsterForUser);
                    deck.numberOfCardsInMain++;
                    monsterForUser.deck = deck;
                    monsterForUser.isInDeck = true;
                    return "done";
                } else {
                    return "card is in a deck!!!!!!!!!!!!!!!!!!!!!!!!!!!";
                }
            }

        }
    }

    public static String AddMonsterToSideDeck(Deck deck, MonsterForUser monsterForUser) {
        if (deck.numberOfCardsInSide >= 15) {
            return "full";
        } else {
                deck.allMonsterForUserSide.add(monsterForUser);
                deck.allMonsterForUserMain.remove(monsterForUser);
                deck.numberOfCardsInMain--;
                deck.numberOfCardsInSide++;
                return "done";
            }
        }

    public static String AddMonsterToUser(Deck deck, MonsterForUser monsterForUser) {
        deck.allMonsterForUserSide.remove(monsterForUser);
        deck.user.allMonsters.add(monsterForUser);
        deck.numberOfCardsInSide--;
        monsterForUser.isInDeck=false;
        monsterForUser.deck=null;
        return "done";
    }
}
