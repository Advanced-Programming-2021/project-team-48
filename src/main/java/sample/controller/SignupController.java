package sample.controller;

import sample.model.User;

public class SignupController {
    public static String creatUser(String username, String nickname, String password) {
        for (User user : User.getListOfUsers()) {
            if (user.getUsername().equals(username)) {
               return "user with username " + username + " already exists";
            }
        }
        for (User user : User.getListOfUsers()) {
            if (user.getNickname().equals(nickname)) {
                return "user with nickname " + nickname + " already exists";
            }
        }
        new User(username, nickname, password);
        return "user created successfully!";
    }

}
