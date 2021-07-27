package sample.Server;

import sample.model.Card.CartReader;
import sample.model.User;

public class ServerMain {
    public static void main(String[] args) {
        new CartReader();
        Server server=new Server();
        server.runApp();
    }
}
