package sample.model;

import sample.model.Card.MonsterForUser;
import sample.model.Card.SpellCardForUser;
import sample.model.Card.TrapCardForUser;

import java.util.ArrayList;

public class Deck implements Cloneable {
    private String name;
    public User user;
    public int numberOfCardsInMain;
    public int numberOfCardsInSide;

    public ArrayList<SpellCardForUser> allSpellCardsForUserMain;
    {
        allSpellCardsForUserMain = new ArrayList<>();
    }

    public ArrayList<TrapCardForUser> allTrapCardsForUserMain;
    {
        allTrapCardsForUserMain = new ArrayList<>();
    }
    public ArrayList<MonsterForUser> allMonsterForUserMain;
    {
        allMonsterForUserMain = new ArrayList<>();
    }
    public ArrayList<SpellCardForUser> allSpellCardsForUserSide;
    {
        allSpellCardsForUserSide = new ArrayList<>();
    }

    public ArrayList<TrapCardForUser> allTrapCardsForUserSide;
    {
        allTrapCardsForUserSide = new ArrayList<>();
    }
    public ArrayList<MonsterForUser> allMonsterForUserSide;
    {
        allMonsterForUserSide = new ArrayList<>();
    }

    public Deck(User user, String name) {
        setName(name);
        setUser(user);
        user.allDecks.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }


    public String isValid() {
        if (numberOfCardsInMain >= 40 && numberOfCardsInMain <= 60 && numberOfCardsInSide >= 0 & numberOfCardsInSide <= 15) {
            return "valid";
        } else return "invalid";
    }

    public void copyDeck(Deck deck){
        this.numberOfCardsInSide=deck.numberOfCardsInSide;
        this.numberOfCardsInMain=deck.numberOfCardsInMain;

        this.allMonsterForUserMain.addAll(deck.allMonsterForUserMain);
        this.allMonsterForUserSide.addAll(deck.allMonsterForUserSide);

        this.allSpellCardsForUserMain.addAll(deck.allSpellCardsForUserMain);
        this.allSpellCardsForUserSide.addAll(deck.allSpellCardsForUserSide);

        this.allTrapCardsForUserMain.addAll(deck.allTrapCardsForUserMain);
        this.allTrapCardsForUserSide.addAll(deck.allTrapCardsForUserSide);
    }
}
