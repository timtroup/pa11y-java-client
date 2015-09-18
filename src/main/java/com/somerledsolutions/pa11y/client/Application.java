package com.somerledsolutions.pa11y.client;

import com.somerledsolutions.pa11y.client.cli.OptionsBuilder;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private Pa11yRestClient client;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        CommandLineParser parser = new DefaultParser();
        Options options = OptionsBuilder.buildOptions();

        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, strings);

            // validate that help has been set
            if( line.hasOption( "help" ) ) {
                // automatically generate the help statement
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("pa11y-java-client", options);
            }
        } catch( ParseException exp ) {
            System.out.println( "Unexpected exception:" + exp.getMessage() );
        }
    }

}