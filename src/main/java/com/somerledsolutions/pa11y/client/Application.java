package com.somerledsolutions.pa11y.client;

import com.somerledsolutions.pa11y.client.cli.OptionsBuilder;
import com.somerledsolutions.pa11y.client.model.Task;
import com.somerledsolutions.pa11y.client.print.Pa11yPrinter;
import com.somerledsolutions.pa11y.client.validation.OptionsValidator;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private Pa11yRestClient client;

    @Autowired
    private OptionsValidator optionsValidator;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        if (shouldPrintHelp(strings)) {
            printHelp();
        } else {
            processPa11yCommandLine(strings);
        }
    }

    private boolean shouldPrintHelp(String[] strings) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = OptionsBuilder.buildHelpOptions();

        CommandLine cl = parser.parse(options, strings, true);
        return cl.hasOption(OptionsBuilder.HELP_OPT);
    }

    private void processPa11yCommandLine(String[] strings) {

        Options options = OptionsBuilder.buildPa11yOptions();

        CommandLineParser parser = new DefaultParser();

        try {
            // parse the command line arguments
            CommandLine cl = parser.parse(options, strings);

            if (cl.hasOption(OptionsBuilder.CREATE_OPT)) {
                createTask(cl);
            }
            if (cl.hasOption(OptionsBuilder.LIST_OPT)) {
                printListOfTasks(cl);
            }
        } catch (ParseException e) {
            log.error("Failed to parse comand line properties", e);
            printHelp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createTask(CommandLine cl) throws ParseException {

        String accessibilityStandardValue = cl.getOptionValue(OptionsBuilder.STD_OPT);
        if (optionsValidator.validateCreateOptions(cl) && optionsValidator.isAccessiblityStandardValid(accessibilityStandardValue)) {
            client.createTask(cl.getOptionValue(OptionsBuilder.NAME_OPT),
                    cl.getOptionValue(OptionsBuilder.URL_OPT),
                    accessibilityStandardValue);
        } else {
            throw new ParseException("Accessibility standard: " + accessibilityStandardValue + " is not valid. Must be one of Section508, WCAG2A, WCAG2AA, WCAG2AAA");
        }
    }

    private void printListOfTasks(CommandLine cl) throws IOException {
        if (optionsValidator.validateListOptions(cl)) {
            List<Task> tasks = client.getTasks(cl.getOptionValue(OptionsBuilder.URL_OPT), false);
            Pa11yPrinter printer = new Pa11yPrinter();
            printer.printTasks(tasks, System.out);
        }
    }

    private void printHelp() {
        // automatically generate the help statement
        Options options = OptionsBuilder.getAllOptions();
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("pa11y-java-client", options);
    }

}