package dev.oclay.evaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class Server {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        var serverSocket = new ServerSocket(port);
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        while (true) {
            var clientSocket = serverSocket.accept();
            executor.execute(() -> handleClient(clientSocket));
        }
    }

    private void handleClient(Socket socket) {
        try (var clientSocket = socket;
             var out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
             var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String expression;
            while ((expression = in.readLine()) != null) {
                String result = "NaN";
                if (Evaluator.isValid(expression)) {
                    result = String.valueOf(Evaluator.evaluate(expression));
                }
                out.println(expression + "=" + result);
            }
        } catch (IOException ex) {
            System.out.println("The connection has been closed");
        }
    }
}
