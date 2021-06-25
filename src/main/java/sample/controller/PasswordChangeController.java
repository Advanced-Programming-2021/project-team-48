package sample.controller;

import sample.model.User;

public class PasswordChangeController {
    public static String changePassword(String oldPassword, String newPassword, User user) {
        if (user.getPassword().equals(oldPassword)) {
            if (!oldPassword.equals(newPassword)) {
                user.setPassword(newPassword);
                return "password changed successfully!";
            } else {
                return "please enter a new password";
            }
        } else {
            return "current password is invalid";
        }
    }
}
