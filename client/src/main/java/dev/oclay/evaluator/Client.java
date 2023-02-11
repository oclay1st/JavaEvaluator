package dev.oclay.evaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.net.Socket;

public class Client {

    private final String serverHost;

    private final int serverPort;

    public Client(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public void connect()  {
        try (var socket = new Socket(serverHost, serverPort);
             var out = new PrintWriter(socket.getOutputStream(), true);
             var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var expressionsReader = new BufferedReader(new InputStreamReader(getExpressionsFileStream()))) {
            String expression;
            while ((expression = expressionsReader.readLine()) != null) {
                out.println(expression);
                System.out.println(in.readLine());
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private InputStream getExpressionsFileStream() {
        String expressionFile = "expressions.txt";
        var inputStream = getClass().getClassLoader().getResourceAsStream(expressionFile);
        if (inputStream == null) {
            throw new IllegalArgumentException("file " + expressionFile + " not found!");
        }
        return inputStream;
    }
}
