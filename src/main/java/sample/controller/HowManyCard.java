package sample.controller;

import sample.model.User;

public class HowManyCard {
    public static int YouHave(User user,String cardName){
        int i=0;
        for (String allCard:AllOwnedCards.all(user)){
            if (allCard.equals(cardName))i++;
        }
        return i;
    }
}
