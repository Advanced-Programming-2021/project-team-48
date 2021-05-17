import Card.CartReader;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new CartReader();
        new ProgramController();
        try {
            Gson gson=new Gson();
            gson.toJson(User.getListOfUsers().get(0), new FileWriter("C:\\Users\\amirhossein\\Desktop\\monster.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}