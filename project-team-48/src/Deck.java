import Card.MonsterCard;
import Card.SpellCardForUser;
import Card.TrapCardForUser;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Deck {
    public String name;
    public User user;
    public int numberOfCards;
    public static ArrayList<Deck> allDecks;
    static {
        allDecks = new ArrayList<>();
    }

    public static ArrayList<SpellCardForUser> allSpellCardsForUser;
    static {
        allSpellCardsForUser = new ArrayList<>();
    }

    public static ArrayList<TrapCardForUser> allTrapCardsForUser;
    static {
        allTrapCardsForUser = new ArrayList<>();
    }
    public static ArrayList<MonsterFor> ;
    static {
        allDecks = new ArrayList<>();
    }

    public DeckOrSidedeck deckOrSidedeck;

    public Deck(User user,String name){
        setName(name);
        setUser(user);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static Deck getDeckByName(String name){
        for (Deck deck:allDecks){
            if (deck.name.equals(name)){
                return deck;
            }
        }
    return null;
    }
}
