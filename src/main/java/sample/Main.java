package sample;

import sample.controller.BuyCard;
import sample.controller.MonsterAdderToDeck;
import sample.controller.SpellAdderToDeck;
import sample.controller.UserLogined;
import sample.model.Card.CartReader;
import sample.model.Card.MonsterForUser;
import sample.model.Card.SpellCardForUser;
import sample.model.Deck;
import sample.model.User;
import sample.view.graphic.Start;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        new CartReader();
        //new ProgramController();
        Start.main(args);

    }

}