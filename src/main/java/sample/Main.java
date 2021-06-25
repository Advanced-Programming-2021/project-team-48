package sample;

import sample.model.Card.CartReader;
import sample.view.graphic.Start;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new CartReader();
        //new ProgramController();
        Start.main(args);
    }

}