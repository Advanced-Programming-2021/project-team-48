package sample.controller;


import sample.Game;
import sample.model.User;

public class StartGameController {

    public static String Game(String user2Username, User user, int round) {

        boolean exist = false;
        for (User user1 : User.getListOfUsers()) {
            if (user1.getUsername().equals(user2Username) && !user.getUsername().equals(user2Username)) {
                exist = true;
                break;
            }
        }

        if (exist) {
            User user2 = User.getUserByUsername(user2Username);
            if (user.hasActiveDeck) {
                if (user2.hasActiveDeck) {
                    if (user.getActiveDeck().isValid().equals("valid")) {
                        if (user2.getActiveDeck().isValid().equals("valid")) {
                            if (round == 1) {
                                UserLogined.opponent=user2;
                                new Game(user, user2);
                                return "done";
                            } else if (round == 3) {
//@!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                //hosh masnoie!!!!!!!!!!!!!!!!!!!
                                return "khuck";
                            } else {
                                return "number of rounds is not supported";
                            }
                        } else {
                            return (user2.getUsername() + "’s deck is invalid");
                        }
                    } else {
                        return (user.getUsername() + "’s deck is invalid");
                    }
                } else {
                    return (user2.getUsername() + " has no active deck");
                }
            } else {
               return (user.getUsername() + " has no active deck");
            }
        } else {
            return "there is no player with this username";
        }
    }

}
