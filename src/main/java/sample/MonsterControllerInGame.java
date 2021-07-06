package sample;

import sample.model.Card.Field;
import sample.model.Card.MonsterForUser;
import sample.model.Card.Position;
import sample.model.User;
import sample.view.graphic.GameGraphic1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonsterControllerInGame {

    private static Scanner scanner = new Scanner(System.in);

    public static void selectedMonsterFromZone(MonsterForUser monsterForUser, User user, User opponent, String phase) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            System.out.println("select part monster zone");
            input = scanner.nextLine();
            boolean checker = false;

            if (input.equals("select -d")) return;

            Pattern pattern = Pattern.compile("card show --selected");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                ProgramController.CardShow(monsterForUser.getName());
            }

            pattern = Pattern.compile("set --position attack");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    System.out.println(positionAttack(monsterForUser));
                } else {
                    System.out.println("you can’t do this action in this phase");
                }
            }
//in ke har dast ye bar avaz mishe!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            pattern = Pattern.compile("set --position defense");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    System.out.println(positionDefend(monsterForUser));
                } else {
                    System.out.println("you can’t do this action in this phase");
                }
            }

            pattern = Pattern.compile("flip-summon");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    System.out.println(flipSummon(monsterForUser));
                } else {
                    System.out.println("you can’t do this action in this phase");
                }
            }

            pattern = Pattern.compile("attack direct");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (Game.dasteAval) {
                    System.out.println("it is not allowed because it is daste aval :)");
                } else if (phase.equals("battle")) {
                    boolean checkIfOpponentMonsterZoneEmpty = true;
                    for (int a = 0; a < 5; a++) {
                        if (opponent.monsterZone[a] != null) {
                            checkIfOpponentMonsterZoneEmpty = false;
                            break;
                        }
                    }
                    if (checkIfOpponentMonsterZoneEmpty) {
                        if (monsterForUser.position.equals(Position.valueOf("ATTACK"))) {
                            opponent.lifePoint -= monsterForUser.ATK;
                            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            System.out.println("you opponent receives " + monsterForUser.ATK + " battale damage");
                        } else if (monsterForUser.position.equals(Position.valueOf("DEFEND"))) {
                            opponent.lifePoint -= monsterForUser.DEF;
                            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            System.out.println("you opponent receives " + monsterForUser.DEF + " battale damage");
                        }// age hidden bashe chi mishe?flip summon?
                    } else {
                        System.out.println("you can’t attack the opponent directly");
                    }
                } else {
                    System.out.println("you can’t do this action in this phase");
                }
            }

            pattern = Pattern.compile("attack ([\\d]+)");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                int address = Integer.parseInt(matcher.group(1));
                address--;
                if (Game.dasteAval) {
                    System.out.println("it is not allowed because it is daste aval :)");
                } else if (phase.equals("battle")) {
                    boolean checkIfOpponentMonsterZoneEmpty = true;
                    if (opponent.monsterZone[address] != null) {
                        checkIfOpponentMonsterZoneEmpty = false;
                    }
                    if (!checkIfOpponentMonsterZoneEmpty) {
                        Game.attack(monsterForUser, opponent.monsterZone[address], user, opponent);
                    } else {
                        System.out.println("there is no card to attack here");
                    }
                } else {
                    System.out.println("you can’t do this action in this phase");
                }
            }


            if (!checker) {
                System.out.println("invalid input");
            }
        }
    }


    public static void monsterSelectedFromHand(MonsterForUser monsterForUser, User user, String phase) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            System.out.println("selected monster from hand part");
            input = scanner.nextLine();
            boolean checker = false;

            if (input.equals("select -d")) return;

            Pattern pattern = Pattern.compile("card show --selected");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                ProgramController.CardShow(monsterForUser.getName());
            }

            pattern = Pattern.compile("summon");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    //!!!!!!!!!!!!!!!!!!!!!!has summon???
                    if (Game.hasSummonInThisRound) {
                        System.out.println("you already summoned/set on this turn");
                    } else {
                        System.out.println(summonController(monsterForUser, user));
                    }
                } else {
                    System.out.println("action not allowed in this phase");
                }
            }

            pattern = Pattern.compile("set");
            matcher = pattern.matcher(input);
            if (matcher.find()) {
                checker = true;
                if (phase.equals("phase1") || phase.equals("phase2")) {
                    if (Game.hasSummonInThisRound) {
                        System.out.println("you already summoned/set on this turn");
                    } else {
                        setController(monsterForUser, user);
                    }
                } else System.out.println("action not allowed in this phase");
            }

            if (!checker) {
                System.out.println("invalid input");
            }

        }
    }


    public static String summonController(MonsterForUser monsterForUser, User user) {
        if (monsterForUser.level <= 4) {
            return summon(monsterForUser, user);
        } else {
            if (tributeConsol(monsterForUser, user)) {
                return summon(monsterForUser, user);
            }
        }
        return "damn it";
    }

    public static String summon(MonsterForUser monsterForUser, User user) {
        boolean hasEmpty = false;
        for (int a = 0; a < 5; a++) {
            if (user.monsterZone[a] == null) {
                hasEmpty = true;

                monsterForUser.field = Field.valueOf("GAME");
                monsterForUser.address = a;
                monsterForUser.position = Position.valueOf("ATTACK");
                user.monsterZone[a] = monsterForUser;
                user.handMonster.remove(monsterForUser);

                Game.hasSummonInThisRound = true;
                GameGraphic1.hasSummon=true;
                return "summoned successfully";

            }
        }
        if (!hasEmpty) {
            return "monster card zone is full";
        }
        return "damn it";
    }

    public static void tributeGraphic(User user,MonsterForUser tributeCard) {
        tributeCard.field = Field.valueOf("GRAVE");
        user.monsterGrave.add(tributeCard);
        tributeCard.address = user.NumOfGrave;
        user.NumOfGrave++;
        for (int i=0;i<user.monsterZone.length;i++){
            if (user.monsterZone[i]==tributeCard){
                user.monsterZone[i]=null;
            }
        }
    }

    private static boolean tributeConsol(MonsterForUser monsterForUser, User user) {
        if (monsterForUser.level == 5 || monsterForUser.level == 6) {
            boolean hasAnyCard = false;
            for (int a = 0; a < 5; a++) {
                if (user.handMonster != null) {
                    hasAnyCard = true;
                    break;
                }
            }

            if (hasAnyCard) {
                System.out.println("enter an address to tribute");
                String temp = scanner.nextLine();
                int address = Integer.parseInt(temp);
                address--;
                if (user.monsterZone[address] == null) {
                    System.out.println("there no monsters one this address");
                } else {
                    user.monsterZone[address].field = Field.valueOf("GRAVE");
                    user.monsterGrave.add(user.monsterZone[address]);
                    user.monsterZone[address].address = user.NumOfGrave;
                    user.NumOfGrave++;
                    user.monsterZone[address] = null;
                    System.out.println("done");
                    return true;
                }
            } else {
                System.out.println("there are not enough cards for tribute");
                return false;
            }
        }


        if (monsterForUser.level > 6) {
            int checker = 0;
            for (int a = 0; a < 5; a++) {
                if (user.handMonster != null) {
                    checker++;
                }
            }

            if (checker >= 2) {
                System.out.println("enter an address to tribute");
                String temp = scanner.nextLine();
                int address = Integer.parseInt(temp);
                address--;
                if (user.monsterZone[address] == null) {
                    System.out.println("there no monsters one this address");
                } else {
                    System.out.println("enter an address to tribute");
                    temp = scanner.nextLine();
                    int address1 = Integer.parseInt(temp);
                    address1--;
                    if (user.monsterZone[address1] == null) {
                        System.out.println("there no monsters one this address");
                    } else {
                        user.monsterZone[address].field = Field.valueOf("GRAVE");
                        user.monsterGrave.add(user.monsterZone[address]);
                        user.monsterZone[address].address = user.NumOfGrave;
                        user.NumOfGrave++;
                        user.monsterZone[address] = null;

                        user.monsterZone[address1].field = Field.valueOf("GRAVE");
                        user.monsterGrave.add(user.monsterZone[address1]);
                        user.monsterZone[address1].address = user.NumOfGrave;
                        user.NumOfGrave++;
                        user.monsterZone[address1] = null;

                        return true;
                    }
                }
            } else {
                System.out.println("there are not enough cards for tribute");
                return false;
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return false;
    }


    public static String positionAttack(MonsterForUser monsterForUser) {
        if (monsterForUser.position.equals(Position.valueOf("ATTACK"))) {
           return "this card is already in the wanted position";
        } else {
            if (monsterForUser.position.equals(Position.valueOf("DEFEND"))) {
                monsterForUser.position = Position.valueOf("ATTACK");
                monsterForUser.canChange=false;
                return ("monster card position changed successfully");
            }
        }
        return "error";
    }

    public static String positionDefend(MonsterForUser monsterForUser) {
        if (monsterForUser.position.equals(Position.valueOf("DEFEND"))) {
            return ("this card is already in the wanted position");
        } else {
            if (monsterForUser.position.equals(Position.valueOf("ATTACK"))) {
                monsterForUser.position = Position.valueOf("DEFEND");
                monsterForUser.canChange=false;
                return ("monster card position changed successfully");
            }
        }
        return "error";
    }

    public static String flipSummon(MonsterForUser monsterForUser) {
        if (monsterForUser.position.equals(Position.valueOf("HIDDEN"))) {
            monsterForUser.position = Position.valueOf("ATTACK");
            monsterForUser.canChange=false;
            return "flip summoned successfully";
        } else {
            return ("you can’t flip summon this card");
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!age taze gozashte bashe nmishe flip krd!!!!!!!!!!!!!!!
        }
    }

    private static void setController(MonsterForUser monsterForUser, User user) {
        if (monsterForUser.level <= 4) {
            System.out.println(set(monsterForUser, user));
        } else {
            if (tributeConsol(monsterForUser, user)) {
                System.out.println(set(monsterForUser, user));
            }
        }
    }

    public static String set(MonsterForUser monsterForUser, User user) {
        boolean hasEmpty = false;
        for (int a = 0; a < 5; a++) {
            if (user.monsterZone[a] == null) {
                hasEmpty = true;
                monsterForUser.field = Field.valueOf("GAME");
                monsterForUser.address = a;
                monsterForUser.position = Position.valueOf("HIDDEN");
                user.monsterZone[a] = monsterForUser;
                user.handMonster.remove(monsterForUser);
                Game.hasSummonInThisRound = true;
                return "set successfully";
            }
        }
        if (!hasEmpty) {
            return "monster card zone is full";
        }
        return "damn it set";
    }

}
