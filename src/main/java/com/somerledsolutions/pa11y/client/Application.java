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
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        CommandLineParser parser = new DefaultParser();
        Options options = OptionsBuilder.buildOptions();

        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, strings);

            // validate that block-size has been set
            if( line.hasOption( "help" ) ) {
                System.out.println( line.getOptionValue( "help" ) );
            }
        } catch( ParseException exp ) {
            System.out.println( "Unexpected exception:" + exp.getMessage() );
        }

        client.setHost("http://0.0.0.0:3000");
//        client.getTask("55f947927d6a39bf01e2d30e", false);

        client.createTask("google4", "http://www.google.com", "WCAG2AAA");
    }

}