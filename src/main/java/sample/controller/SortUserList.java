package sample.controller;

import sample.model.User;

public class SortUserList {
    public static void Sort() {
        for (int i = 1; i < User.getListOfUsers().size(); ++i) {
            User key = User.getListOfUsers().get(i);
            int j = i - 1;
            while (j >= 0 && User.getListOfUsers().get(j).getScore() < key.getScore()) {
                User.getListOfUsers().set(j + 1, User.getListOfUsers().get(j));
                j = j - 1;
            }
            User.getListOfUsers().set(j + 1, key);
        }

        for (int i = 1; i < User.getListOfUsers().size(); i++) {
            User key = User.getListOfUsers().get(i);
            int j = i - 1;
            while (j >= 0 && User.getListOfUsers().get(j).getScore() == key.getScore() && key.getNickname().compareTo(User.getListOfUsers().get(j).getNickname()) < 1) {
                User.getListOfUsers().set(j + 1, User.getListOfUsers().get(j));
                j = j - 1;
            }
            User.getListOfUsers().set(j + 1, key);
        }
    }
}
