package sample;

import sample.model.Card.*;
import sample.model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class trapControllerInGame {
    private static int[] checkIfEmpty = {2, 3, 1, 4, 0};

    public static void trapSelectedFromHand(TrapCardForUser trapCardForUser, User user, User opponent, String phase) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("select -d")) {
            input = scanner.nextLine();
            boolean checker = false;
            checker = Game.generalSelected(trapCardForUser);


            Pattern pattern = Pattern.compile("set");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    TrapSeter(trapCardForUser, user, opponent, phase);
                } else {
                    System.out.println("you can’t activate an effect on this turn");
                }
            }

        }
    }

    private static void TrapSeter(TrapCardForUser trapCardForUser, User user, User opponent, String phase) {
        boolean isFull=true;
        for (int a:checkIfEmpty){
            if (user.trapZone[a]==null){
                isFull=false;
            }
        }
        if (!isFull){
            for (int a:checkIfEmpty){
                if (user.trapZone[a]==null){
                    user.trapZone[a]=trapCardForUser;
                    user.trapZone[a].position= Position.ATTACK;
                    user.trapZone[a].address=a;
                    user.trapZone[a].field= Field.GAME;
                    break;
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }
            }
        }else {
            System.out.println("spell card zone is full");
        }
    }
}
