package sample.model;

import javafx.scene.image.Image;
import sample.model.Card.MonsterForUser;
import sample.model.Card.SpellCardForUser;
import sample.model.Card.TrapCardForUser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;


public class User implements Serializable {


    public String username;
    public String nickname;
    public String password;

    public int score = 0;
    private long money = 10000000;
    public int lifePoint = 8000;
    public String token;
    public ArrayList<MonsterForUser> handMonster = new ArrayList<>();
    public ArrayList<SpellCardForUser> handSpell = new ArrayList<>();
    public ArrayList<TrapCardForUser> handTrap = new ArrayList<>();

    public ArrayList<MonsterForUser> monsterGrave = new ArrayList<>();
    public ArrayList<SpellCardForUser> spellGrave = new ArrayList<>();
    public ArrayList<TrapCardForUser> trapGrave = new ArrayList<>();
    public int NumOfGrave = 0;

    public String imageAddress;
    public MonsterForUser[] monsterZone = new MonsterForUser[5];
    public SpellCardForUser[] spellZone = new SpellCardForUser[5];
    public TrapCardForUser[] trapZone = new TrapCardForUser[5];
    public SpellCardForUser fieldZone;


    public ArrayList<MonsterForUser> allMonsters = new ArrayList<>();

    public ArrayList<TrapCardForUser> allTraps = new ArrayList<>();

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
    public User(String username, String nickname, String password, String imageAddress) {
        setUsername(username);
        setNickname(nickname);
        this.password = password;
        this.imageAddress = imageAddress;

        Socket socket = null;
        try {
            socket = new Socket("localhost", 7001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.writeUTF("sendingNewUser");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
                objectOutputStream.writeObject(this);
                objectOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public static User getUserByUsername(String username) {
        for (User user : User.getListOfUsers()) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
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

    public void decreaseLP(int damage) {
        this.lifePoint -= damage;
    }




    public static ArrayList<User> getListOfUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        Socket socket = null;
        try {
            socket = new Socket("localhost", 7001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.writeUTF("userList");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            try {
                allUsers = (ArrayList<User>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public Image getAvatar() {
        return (new Image(new File(imageAddress).toURI().toString()));
    }
}
