import Card.MonsterForUser;
import Card.SpellCardForUser;
import Card.TrapCardForUser;

import java.util.ArrayList;
import java.util.HashMap;
public class User {
    private String username;
    private String nickname;
    private String password;
    private int score=0;
    private long  money=100000;
    private static ArrayList<User> listOfUsers ;
    static {
        listOfUsers=new ArrayList<>();
    }
    public ArrayList<MonsterForUser> allMonsters=new ArrayList<>();
    //??????????????????????????????
    public ArrayList<TrapCardForUser> allTraps;
     {
        allTraps=new ArrayList<>();
    }
    public ArrayList<SpellCardForUser> allSpells;
    {
        allSpells=new ArrayList<>();
    }
    public ArrayList<Deck> allDecks;
    {
        allDecks=new ArrayList<>();
    }
    private Deck activeDeck;
    //-------------------------------------------------
    User(String username,String nickname,String password){
        setUsername(username);
        setNickname(nickname);
        setPassword(password);
        listOfUsers.add(this);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setActiveDeck(Deck activeDeck){
        this.activeDeck=activeDeck;
    }

    public Deck getActiveDeck() {
        return activeDeck;
    }

    public String getPassword() { return password;}

    public static User getUserByUsername(String username){
        for (User user:listOfUsers){
            if (user.username.equals(username)){
                return user;
            }
        }
        return null;
    }

    public static ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    public long getMoney() {
        return money;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUsername() {
        return username;
    }

    public static boolean passwordChecker(String username, String password){
        return User.getUserByUsername(username).equals(password);
    }

    public int getScore() {
        return score;
    }

    public Deck getDeckByName(String name){
        for (Deck deck:allDecks){
            if (deck.getName().equals(name)){
                return deck;
            }
        }
        return null;
    }
}
