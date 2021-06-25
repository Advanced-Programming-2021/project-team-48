package sample.controller;

import sample.model.Card.MonsterCard;
import sample.model.Card.SpellCard;
import sample.model.Card.TrapCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SortCards {
    public static ArrayList<String> MonsterSort(){
        ArrayList<String> allMonsters = new ArrayList<>();
        for (MonsterCard monsterCard : MonsterCard.getAllMonsterCards()) {
            allMonsters.add(monsterCard.getName());
        }
        Set<String> set = new HashSet<>(allMonsters);
        allMonsters.clear();
        allMonsters.addAll(set);
        Collections.sort(allMonsters);
        return allMonsters;
    }

    public static ArrayList<String> SpellSort() {
        ArrayList<String> allSpell = new ArrayList<>();
        for (SpellCard spellCard : SpellCard.getAllSpellCard()) {
            allSpell.add(spellCard.getName());
        }
        Set<String> set = new HashSet<>(allSpell);
        allSpell.clear();
        allSpell.addAll(set);
        Collections.sort(allSpell);
        return allSpell;
    }

    public static ArrayList<String> TrapSort() {
        ArrayList<String> allTrap = new ArrayList<>();
        for (TrapCard trapCard : TrapCard.getAllTrapCard()) {
            allTrap.add(trapCard.getName());
        }
        Set<String> set = new HashSet<>(allTrap);
        allTrap.clear();
        allTrap.addAll(set);
        Collections.sort(allTrap);
        return allTrap;
    }

}
