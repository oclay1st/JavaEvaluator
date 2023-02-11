package dev.oclay.evaluator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        var portEnv = System.getenv("PORT");
        int port = portEnv != null ? Integer.parseInt(portEnv) : 5000;

        var server = new Server(port);
        server.start();
    }

}
