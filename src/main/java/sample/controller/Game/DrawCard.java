package sample.controller.Game;

import sample.model.Card.Field;
import sample.model.Card.MonsterForUser;
import sample.model.Card.SpellCardForUser;
import sample.model.Card.TrapCardForUser;
import sample.model.User;

import java.util.Collections;
import java.util.Random;

public class DrawCard {

    public static String draw(User user) {
        int i = 0,j=0;
        for (MonsterForUser monsterForUser : user.handMonster) {
            i++;
            if (monsterForUser.address==j){
                j++;
            }
        }
        for (SpellCardForUser spellCardForUser : user.handSpell) {
            i++;
            if (spellCardForUser.address==j){
                j++;
            }
        }
        for (TrapCardForUser trapCardForUser : user.handTrap) {
            i++;
            if (trapCardForUser.address==j){
                j++;
            }
        }
        if (i >= 6) {
            return "Hand is full";
        }

        i=j;
        Random random = new Random();
        int r = random.nextInt(3);
        boolean check = false;
        while (!check) {
            if (r == 0) {
                if (user.getActiveDeck().allMonsterForUserMain.isEmpty()) {
                    r = 1;
                } else {
                    check = true;
                    Collections.shuffle(user.getActiveDeck().allMonsterForUserMain);
                    user.handMonster.add(user.getActiveDeck().allMonsterForUserMain.get(0));
                    user.getActiveDeck().allMonsterForUserMain.get(0).field = Field.valueOf("HAND");
                    //i++;
                    user.getActiveDeck().allMonsterForUserMain.get(0).address = i;
                    user.getActiveDeck().numberOfCardsInMain--;
                    user.getActiveDeck().allMonsterForUserMain.remove(0);
                    return "new card added to the hand : " + user.getActiveDeck().allMonsterForUserMain.get(0).getName();
                }
            }
            if (r == 1) {
                if (user.getActiveDeck().allSpellCardsForUserMain.isEmpty()) {
                    r = 2;
                } else {
                    check = true;
                    Collections.shuffle(user.getActiveDeck().allSpellCardsForUserMain);
                    user.handSpell.add(user.getActiveDeck().allSpellCardsForUserMain.get(0));
                    user.getActiveDeck().allSpellCardsForUserMain.get(0).field = Field.valueOf("HAND");
                   // i++;
                    user.getActiveDeck().allSpellCardsForUserMain.get(0).address = i;
                    user.getActiveDeck().numberOfCardsInMain--;
                    user.getActiveDeck().allSpellCardsForUserMain.remove(0);
                    return "new card added to the hand : " + user.getActiveDeck().allSpellCardsForUserMain.get(0).getName();

                }
            }
            if (r == 2) {
                if (user.getActiveDeck().allTrapCardsForUserMain.isEmpty()) {
                    r = 0;
                } else {
                    check = true;
                    Collections.shuffle(user.getActiveDeck().allTrapCardsForUserMain);
                    user.handTrap.add(user.getActiveDeck().allTrapCardsForUserMain.get(0));
                    user.getActiveDeck().allTrapCardsForUserMain.get(0).field = Field.valueOf("HAND");
                    //i++;
                    user.getActiveDeck().allTrapCardsForUserMain.get(0).address = i;
                    user.getActiveDeck().numberOfCardsInMain--;
                    user.getActiveDeck().allTrapCardsForUserMain.remove(0);
                    return "new card added to the hand : " + user.getActiveDeck().allTrapCardsForUserMain.get(0).getName();
                }
            }

        }
    return "error";}
}
