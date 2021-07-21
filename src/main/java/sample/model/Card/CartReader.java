package sample.model.Card;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


public class CartReader {
    private static ArrayList<MonsterCard> monsterCards = new ArrayList<>();
    private static ArrayList<SpellCard> spellCards = new ArrayList<>();
    private static ArrayList<TrapCard> trapCards = new ArrayList<>();


    public CartReader() {
        csv();
    }

    private void csv() {

        boolean aval = true;
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf("C:\\Users\\amirhossein\\Desktop\\project-team-48-komak\\src\\main\\resources\\sample\\model\\Card\\Monster.csv")))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!aval) {
                    String[] values = line.split(",");
                    monsterCards.add(new MonsterCard(values[0], values[7], Integer.parseInt(values[8]), Integer.parseInt(values[1]), values[4], Integer.parseInt(values[5]), Integer.parseInt(values[6]), MonsterType.valueOf(values[3].toUpperCase(Locale.ROOT)), Attribute.valueOf(values[2].toUpperCase(Locale.ROOT))));
                } else aval = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        aval = true;
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf("C:\\Users\\amirhossein\\Desktop\\project-team-48-komak\\src\\main\\resources\\sample\\model\\Card\\SpellTrap.csv")))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!aval) {
                    String[] values = line.split(",");
                    if (values[1].equals("Trap")) {

                        trapCards.add(new TrapCard(values[0], values[3], Integer.parseInt(values[5]), Property.valueOf(values[2].toUpperCase(Locale.ROOT)), Status.valueOf(values[4].toUpperCase(Locale.ROOT))));
                    } else {

                        spellCards.add(new SpellCard(values[0], values[3], Integer.parseInt(values[5]), Property.valueOf(values[2].toUpperCase(Locale.ROOT)), Status.valueOf(values[4].toUpperCase(Locale.ROOT))));
                    }
                } else aval = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

