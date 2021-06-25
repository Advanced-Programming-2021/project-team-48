package sample;

import sample.model.Card.Field;
import sample.model.Card.Position;
import sample.model.Card.Property;
import sample.model.Card.SpellCardForUser;
import sample.model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class spellControlerInGame {
    private static int[] checkIfEmpty = {2, 3, 1, 4, 0};

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
                        FieldActiver(spellCardForUser,user,opponent,phase);
                    }else {
                        SpellActiver(spellCardForUser,user,opponent,phase);
                    }
                }else {
                    System.out.println("you can’t activate an effect on this turn");
                }
            }

            pattern = Pattern.compile("set");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")){
                        SpellSeter(spellCardForUser,user,opponent,phase);
                }else {
                    System.out.println("you can’t activate an effect on this turn");
                }
            }
        }
    }

    public static void FieldActiver(SpellCardForUser spellCardForUser,User user,User opponent,String phase){
        if (user.fieldZone!=null){
            user.fieldZone.field=Field.GRAVE;
            user.fieldZone.address= user.NumOfGrave;
            user.NumOfGrave++;
            user.spellGrave.add(user.fieldZone);

            user.fieldZone=spellCardForUser;
            spellCardForUser.position=Position.valueOf("ATTACK");
            spellCardForUser.address=0;
            spellCardForUser.field=Field.valueOf("GAME");
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
        }else {
            user.fieldZone=spellCardForUser;
            spellCardForUser.position=Position.valueOf("ATTACK");
            spellCardForUser.address=0;
            spellCardForUser.field=Field.valueOf("GAME");
        }
    }

    public static void SpellActiver(SpellCardForUser spellCardForUser,User user,User opponent,String phase){
        boolean isFull=true;
        for (int a:checkIfEmpty){
            if (user.spellZone[a]==null){
                isFull=false;
            }
        }
        if (!isFull){
            for (int a:checkIfEmpty){
                if (user.spellZone[a]==null){
                    user.spellZone[a]=spellCardForUser;
                    user.spellZone[a].position= Position.valueOf("ATTACK");
                    user.spellZone[a].address=a;
                    user.spellZone[a].field= Field.valueOf("GAME");
                    break;
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }
            }
        }else {
            System.out.println("spell card zone is full");
        }
    }



    public static void SpellSeter(SpellCardForUser spellCardForUser,User user,User opponent,String phase){
        boolean isFull=true;
        for (int a:checkIfEmpty){
            if (user.spellZone[a]==null){
                isFull=false;
            }
        }
        if (!isFull){
            for (int a:checkIfEmpty){
                if (user.spellZone[a]==null){
                    user.spellZone[a]=spellCardForUser;
                    user.spellZone[a].position= Position.ATTACK;
                    user.spellZone[a].address=a;
                    user.spellZone[a].field= Field.GAME;
                    break;
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }
            }
        }else {
            System.out.println("spell card zone is full");
        }
    }

}
