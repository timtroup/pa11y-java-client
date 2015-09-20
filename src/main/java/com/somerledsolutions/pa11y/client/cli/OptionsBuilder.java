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
    public static final String HOST_OPT = "h";
    public static final String HOST_LONG_OPT = "host";
    public static final String STD_OPT = "s";
    public static final String STD_LONG_OPT = "standard";

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

        options.addOptionGroup(mutuallyExclusiveOptions);
        options.addOption(getNameOption());
        options.addOption(getHostOption());
        options.addOption(getStandardOption());
        options.addOption(getLastResultOption());

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
                .required()
                .hasArg()
                .numberOfArgs(1)
                .build();
    }

    private static Option getHostOption() {
        return Option.builder(HOST_OPT)
                .argName("Host")
                .longOpt(HOST_LONG_OPT)
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
                .required()
                .hasArg()
                .numberOfArgs(1)
                .build();
    }

    private static Option getLastResultOption() {
        return Option.builder("lr")
                .argName("Last result")
                .longOpt("lastres")
                .desc("Include the last_result property in the output")
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
}
