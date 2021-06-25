package sample.controller;

import sample.model.Card.*;
import sample.model.User;

public class BuyCard {
    public static String BuyCard(String cardName, User user) {
        boolean exist = false;
        for (Card card : Card.getAllCards()) {
            if (card.getName().equals(cardName)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            return "there is no card with this name";
        } else {
            if (Card.getCardByName(cardName).getPrice() <= user.getMoney()) {
                long userMoney = user.getMoney();
                long cardPrice = Card.getCardByName(cardName).getPrice();
                long lastMoney = userMoney - cardPrice;
                user.setMoney(lastMoney);
                addCardToUser(cardName, user);
                return "done";
            } else {
                return "not enough money";
            }
        }
    }


    private static void addCardToUser(String cardname, User user) {
        for (MonsterCard monsterCard : MonsterCard.getAllMonsterCards()) {
            if (monsterCard.getName().equals(cardname)) {
                user.allMonsters.add(new MonsterForUser(monsterCard, user));
                //???????????????????????????????????????????????????????????????
                return;
            }
        }
        for (SpellCard spellCard : SpellCard.getAllSpellCard()) {
            if (spellCard.getName().equals(cardname)) {
                user.allSpells.add(new SpellCardForUser(spellCard, user));
                return;
            }
        }
        for (TrapCard trapCard : TrapCard.getAllTrapCard()) {
            if (trapCard.getName().equals(cardname)) {
                user.allTraps.add(new TrapCardForUser(trapCard, user));
                return;
            }
        }
    }
}
