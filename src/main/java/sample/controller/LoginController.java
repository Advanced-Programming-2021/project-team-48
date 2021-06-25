package sample.controller;

import sample.ProgramController;
import sample.model.User;

public class LoginController {
    public static String login(String username, String password) {
        boolean exist = false;
        for (User user : User.getListOfUsers()) {
            if (user.getUsername().equals(username)) {
                exist = true;
                break;
            }
        }
        if (exist) {
            if (!User.passwordChecker(username, password)) {
                return "Username and password didn't match!";
            } else {
                return "OK";
            }
        } else {
            return "Username and password didn't match!";
        }
    }
}
