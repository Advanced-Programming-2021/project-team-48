package sample.controller;

import sample.model.User;

public class NicknameChangeController {

    public static String changeNickname(String newNickname, User user) {

        if (user.getNickname().equals(newNickname)) {
            return "this nickname is same to the nickname you have now!";
        } else {
            for (User user1 : User.getListOfUsers()) {
                if (user1.getNickname().equals(newNickname)) {
                    return "user with nickname " + newNickname + " already exists";
                }
            }
            user.setNickname(newNickname);
            return "nickname changed successfully!";
        }
    }
}
