import Card.CartReader;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("project","project","project"));
        users.add(new User("46","46","46"));
        users.add(new User("ok","ok","ok"));

        System.out.println(users);

        try {
            FileWriter myWriter = new FileWriter("monsterCards.json");
            myWriter.write(new Gson().toJson(users.toArray()));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        new CartReader();
        new ProgramController();
    }

}