import Card.Property;
import Card.SpellCardForUser;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class spellControlerInGame {


    public static void spellSelectedFromHand(SpellCardForUser spellCardForUser, User user, User opponent, String phase) {
        Scanner scanner=new Scanner(System.in);
        String input = "";
        while (!input.equals("select -d")) {
            input = scanner.nextLine();
            boolean checker = false;
            checker = Game.generalSelected(spellCardForUser);

            Pattern pattern = Pattern.compile("activate effect");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")){
                    if (spellCardForUser.getProperty().equals(Property.valueOf("FIELD"))){

                    }else {

                    }
                }else {
                    System.out.println("you can’t activate an effect on this turn");
                }
            }
        }
    }

}
