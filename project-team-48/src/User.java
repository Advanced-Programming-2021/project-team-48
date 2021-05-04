import java.util.ArrayList;
import java.util.HashMap;
public class User {
    public  String username;
    public String nickname;
    private String password;
    public int score=0;
    public int money;
    public static ArrayList<User> listOfUsers;
    static {
        listOfUsers=new ArrayList<>();
    }

    public static HashMap<User,Integer> userAndscore;
    static {
        userAndscore=new HashMap<>();
    }

 /*   public static HashMap<String,User> usernameAndUsers;
    static {
        usernameAndUsers=new HashMap<>();
    }

  */


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

    public String getPassword() { return password;}

    public static User getUserByUsername(String username){
        for (User user:listOfUsers){
            if (user.username.equals(username)){
                return user;
            }
        }
        return null;
    }

    public static boolean passwordChecker(String username,String password){
        return User.getUserByUsername(username).equals(password);
    }

    public int getScore() {
        return score;
    }
}
