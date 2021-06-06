import Card.CartReader;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        new CartReader();
        new ProgramController();
    }

}