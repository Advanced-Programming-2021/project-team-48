package sample;

import sample.model.Card.Field;
import sample.model.Card.Position;
import sample.model.Card.Property;
import sample.model.Card.SpellCardForUser;
import sample.model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpellControllerInGame {

    public static void spellSelectedFromHand(SpellCardForUser spellCardForUser, User user, User opponent, String phase) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("select -d")) {
            input = scanner.nextLine();
            boolean checker = false;
            checker = Game.generalSelected(spellCardForUser);

            Pattern pattern = Pattern.compile("activate effect");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    if (spellCardForUser.getProperty().equals(Property.valueOf("FIELD"))) {
                        FieldActiver(spellCardForUser, user);
                    } else {
                        System.out.println(SpellActiver(spellCardForUser, user));
                    }
                } else {
                    System.out.println("you can’t activate an effect on this turn");
                }
            }

            pattern = Pattern.compile("set");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    System.out.println(SpellSeter(spellCardForUser, user));
                } else {
                    System.out.println("you can’t activate an effect on this turn");
                }
            }
        }
    }

    public static void FieldActiver(SpellCardForUser spellCardForUser, User user) {
        if (user.fieldZone != null) {
            user.fieldZone.field = Field.GRAVE;
            user.fieldZone.address = user.NumOfGrave;
            user.NumOfGrave++;
            user.spellGrave.add(user.fieldZone);

            user.fieldZone = spellCardForUser;
            user.allSpells.remove(spellCardForUser);
            spellCardForUser.position = Position.valueOf("ATTACK");
            spellCardForUser.address = 0;
            spellCardForUser.field = Field.valueOf("GAME");
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
        } else {
            user.fieldZone = spellCardForUser;
            user.allSpells.remove(spellCardForUser);
            spellCardForUser.position = Position.valueOf("ATTACK");
            spellCardForUser.address = 0;
            spellCardForUser.field = Field.valueOf("GAME");
        }
    }

    public static String SpellActiver(SpellCardForUser spellCardForUser, User user) {
        boolean isFull = true;
        for (int a = 0; a < 5; a++) {
            if (user.spellZone[a] == null && user.trapZone[a] == null) {
                isFull = false;
            }
        }
        if (!isFull) {
            for (int a = 0; a < 5; a++) {
                if (user.spellZone[a] == null && user.trapZone[a] == null) {

                    user.spellZone[a] = spellCardForUser;
                    user.allSpells.remove(spellCardForUser);
                    user.spellZone[a].position = Position.valueOf("ATTACK");
                    user.spellZone[a].address = a;
                    user.spellZone[a].field = Field.valueOf("GAME");
                    user.handSpell.remove(spellCardForUser);

                    return ("spell activated");

                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }
            }
        } else {
            return ("spell card zone is full");
        }
        return "error";
    }


    public static String SpellSeter(SpellCardForUser spellCardForUser, User user) {
        boolean isFull = true;
        for (int a = 0; a < 5; a++) {
            if (user.spellZone[a] == null && user.trapZone[a] == null) {
                isFull = false;
            }
        }
        if (!isFull) {
            for (int a = 0; a < 5; a++) {
                if (user.spellZone[a] == null && user.trapZone[a] == null) {

                    user.spellZone[a] = spellCardForUser;
                    user.allSpells.remove(spellCardForUser);
                    user.spellZone[a].position = Position.HIDDEN;
                    user.spellZone[a].address = a;
                    user.spellZone[a].field = Field.GAME;
                    user.handSpell.remove(spellCardForUser);
                    return ("set successfully");
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }
            }
        } else {
            return ("spell card zone is full");
        }
        return "error";
    }

}
