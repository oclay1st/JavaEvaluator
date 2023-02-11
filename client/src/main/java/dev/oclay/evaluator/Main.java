package dev.oclay.evaluator;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    public static void main(String[] args)  {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter helper = new HelpFormatter();
        Options options = new Options();
        var server =  new Option("s", "server", true, "Set the server hostname");
        server.setRequired(true);
        options.addOption(server);
        var port =  new Option("p", "port", true, "Set the server port");
        port.setRequired(true);
        options.addOption(port);
        try {
            var cmd = parser.parse(options, args);
            var serverHost = cmd.getOptionValue("s");
            var serverPort =  cmd.getOptionValue("p");
            var client = new Client(serverHost, Integer.parseInt(serverPort));
            client.connect();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            helper.printHelp("Usage:", options);
            System.exit(0);
        }
    }
}
