package sample.model;

import javafx.scene.image.Image;
import sample.model.Card.MonsterForUser;
import sample.model.Card.SpellCardForUser;
import sample.model.Card.TrapCardForUser;

import java.util.ArrayList;

public class User {
    private String username;
    private String nickname;
    private String password;
    private int score = 0;
    private long money = 10000000;
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public int lifePoint = 8000;
    private Image avatar;
    public ArrayList<MonsterForUser> handMonster = new ArrayList<>();
    public ArrayList<SpellCardForUser> handSpell = new ArrayList<>();
    public ArrayList<TrapCardForUser> handTrap = new ArrayList<>();

    public ArrayList<MonsterForUser> monsterGrave = new ArrayList<>();
    public ArrayList<SpellCardForUser> spellGrave = new ArrayList<>();
    public ArrayList<TrapCardForUser> trapGrave = new ArrayList<>();
    public int NumOfGrave = 0;

    public MonsterForUser[] monsterZone = new MonsterForUser[5];
    public SpellCardForUser[] spellZone = new SpellCardForUser[5];
    public TrapCardForUser[] trapZone = new TrapCardForUser[5];
    public SpellCardForUser fieldZone;


    private static ArrayList<User> listOfUsers;
    static {
        listOfUsers = new ArrayList<>();
    }


    public ArrayList<MonsterForUser> allMonsters = new ArrayList<>();

    public ArrayList<TrapCardForUser> allTraps=new ArrayList<>();
    //????????????????????????????????????????????/////
    public ArrayList<SpellCardForUser> allSpells;
    {
        allSpells = new ArrayList<>();
    }

    public ArrayList<Deck> allDecks;
    {
        allDecks = new ArrayList<>();
    }

    private Deck activeDeck;
    public boolean hasActiveDeck = false;

    //-------------------------------------------------
    public User(String username, String nickname, String password) {
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

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public int getLifePoint() {
        return lifePoint;
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

    public void setActiveDeck(Deck activeDeck) {
        this.activeDeck = activeDeck;
    }

    public Deck getActiveDeck() {
        return activeDeck;
    }

    public String getPassword() {
        return password;
    }

    public static User getUserByUsername(String username) {
        for (User user : listOfUsers) {
            if (user.username.equals(username)) {
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

    public static boolean passwordChecker(String username, String password) {
        return User.getUserByUsername(username).getPassword().equals(password);
    }

    public int getScore() {
        return score;
    }


    public Deck getDeckByName(String name) {
        for (Deck deck : allDecks) {
            if (deck.getName().equals(name)) {
                return deck;
            }
        }
        return null;
    }

    public void decreaseLP(int damage){
        this.lifePoint -= damage;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public Image getAvatar() {
        return avatar;
    }
}
