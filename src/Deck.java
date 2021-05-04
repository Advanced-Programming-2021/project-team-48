import java.util.ArrayList;

public class Deck {
    public String name;
    public User user;
    public int numberOfCards;
    public static ArrayList<Deck> allDecks;
    static {
        allDecks = new ArrayList<>();
    }

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
}
