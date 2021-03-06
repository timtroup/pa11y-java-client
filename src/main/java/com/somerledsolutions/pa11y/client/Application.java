/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015. Tim Troup
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
import org.springframework.http.HttpStatus;

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
            } else if (cl.hasOption(OptionsBuilder.LIST_OPT)) {
                printListOfTasks(cl);
            } else if (cl.hasOption(OptionsBuilder.RUN_OPT)) {
                runTask(cl);
            } else if (cl.hasOption(OptionsBuilder.GET_TASK_OPT)) {
                getTask(cl);
            } else if (cl.hasOption(OptionsBuilder.DELETE_OPT)){
                deleteTask(cl);
            }
        } catch (ParseException e) {
            log.error("Failed to parse comand line properties", e);
            printHelp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteTask(CommandLine cl) {
        if(optionsValidator.validateDeleteOptions(cl)){
            client.deleteTask(cl.getOptionValue(OptionsBuilder.TID_OPT),
            cl.getOptionValue(OptionsBuilder.URL_OPT));

        }

    }

    private void getTask(CommandLine cl) throws IOException {
        if (optionsValidator.validateRunOptions(cl)) {
            Task task = client.getTask(cl.getOptionValue(OptionsBuilder.TID_OPT),
                    cl.getOptionValue(OptionsBuilder.URL_OPT),
                    cl.hasOption(OptionsBuilder.LASTRES_OPT));
            Pa11yPrinter printer = new Pa11yPrinter();
            printer.printTask(task, System.out);
        }
    }

    private void runTask(CommandLine cl) {

        if(optionsValidator.validateRunOptions(cl)) {
            HttpStatus httpStatus = client.runTask(cl.getOptionValue(OptionsBuilder.TID_OPT),
                    cl.getOptionValue(OptionsBuilder.URL_OPT));
            System.out.println(httpStatus.getReasonPhrase());
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