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

package com.somerledsolutions.pa11y.client.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

public class OptionsBuilder {

    public static final String HELP_OPT = "h";
    public static final String HELP_LONG_OPT = "help";
    public static final String CREATE_OPT = "c";
    public static final String CREATE_LONG_OPT = "create";
    public static final String LIST_OPT = "l";
    public static final String LIST_LONG_OPT = "list";
    public static final String NAME_OPT = "n";
    public static final String NAME_LONG_OPT = "name";
    public static final String URL_OPT = "u";
    public static final String URL_LONG_OPT = "url";
    public static final String STD_OPT = "s";
    public static final String STD_LONG_OPT = "standard";
    public static final String RUN_OPT = "r";
    public static final String RUN_LONG_OPT = "run";
    public static final String TID_OPT = "tid";
    public static final String TID_LONG_OPT = "taskid";
    public static final String GET_TASK_OPT = "g";
    public static final String GET_TASK_LONG_OPT = "get";
    public static final String LASTRES_OPT = "lr";
    public static final String LASTRES_LONG_OPT = "lastres";

    public static Options buildHelpOptions() {
        Options options = new Options();
        options.addOption(getHelpOption());

        return options;
    }

    public static Options buildPa11yOptions() {
        Options options = new Options();

        OptionGroup mutuallyExclusiveOptions = new OptionGroup();
        mutuallyExclusiveOptions.addOption(getCreateTaskOption());
        mutuallyExclusiveOptions.addOption(getListTasksOption());
        mutuallyExclusiveOptions.addOption(getRunOption());

        options.addOptionGroup(mutuallyExclusiveOptions);
        options.addOption(getNameOption());
        options.addOption(getUrlOption());
        options.addOption(getStandardOption());
        options.addOption(getLastResultOption());
        options.addOption(getTaskIdOption());
        options.addOption(getRetrieveTaskOption());

        return options;
    }

    private static Option getHelpOption() {
        return Option.builder(HELP_OPT)
                .argName("Help")
                .longOpt(HELP_LONG_OPT)
                .desc("Shows this help")
                .build();
    }

    private static Option getCreateTaskOption() {
        return Option.builder(CREATE_OPT)
                .argName("Create task")
                .longOpt(CREATE_LONG_OPT)
                .desc("Create a new task")
                .build();
    }

    private static Option getListTasksOption() {
        return Option.builder(LIST_OPT)
                .argName("List all tasks")
                .longOpt(LIST_LONG_OPT)
                .desc("Get all of the tasks in the application")
                .build();
    }

    private static Option getNameOption() {
        return Option.builder(NAME_OPT)
                .argName("Name")
                .longOpt(NAME_LONG_OPT)
                .desc("The name of the task")
                .hasArg()
                .numberOfArgs(1)
                .build();
    }

    private static Option getUrlOption() {
        return Option.builder(URL_OPT)
                .argName("URL")
                .longOpt(URL_LONG_OPT)
                .desc("The base URL of the pa11y webservice")
                .required()
                .hasArg()
                .numberOfArgs(1)
                .build();
    }

    private static Option getStandardOption() {
        return Option.builder(STD_OPT)
                .argName("Accessibility standard")
                .longOpt(STD_LONG_OPT)
                .desc("The accessibility standard to test the URL against. Must be one of Section508, WCAG2A, WCAG2AA, WCAG2AAA")
                .hasArg()
                .numberOfArgs(1)
                .build();
    }

    public static Options getAllOptions() {
        Options allOptions = new Options();
        Options helpOptions = buildHelpOptions();

        for(Option option: helpOptions.getOptions()) {
            allOptions.addOption(option);
        }

        Options pa11yOptions = buildPa11yOptions();

        for(Option option: pa11yOptions.getOptions()) {
            allOptions.addOption(option);
        }

        return allOptions;
    }

    private static Option getTaskIdOption() {
        return Option.builder(TID_OPT)
                .argName("Task ID")
                .longOpt(TID_LONG_OPT)
                .desc("The ID of the task")
                .hasArg()
                .numberOfArgs(1)
                .build();
    }

    private static Option getRunOption() {
        return Option.builder(RUN_OPT)
                .argName("Run")
                .longOpt(RUN_LONG_OPT)
                .desc("Run a task by ID, generating new results")
                .build();
    }

    private static Option getRetrieveTaskOption() {
        return Option.builder(GET_TASK_OPT)
                .argName("Get")
                .longOpt(GET_TASK_LONG_OPT)
                .desc("Get a single task by ID")
                .build();
    }

    private static Option getLastResultOption() {
        return Option.builder(LASTRES_OPT)
                .argName("Last result")
                .longOpt(LASTRES_LONG_OPT)
                .desc("Include the last_result property in the output")
                .build();
    }
}
