import java.util.ArrayList;

public class Deck {
    private String name;
    private User user;
    private int numberOfCards;
    private static ArrayList<Deck> allDecks = new ArrayList<>();

    public Deck(User user,String name){
        setName(name);
        setUser(user);
        allDecks.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    public String getName() {
        return name;
    }

    public static ArrayList<Deck> getAllDecks() {
        return allDecks;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public User getUser() {
        return user;
    }
}
