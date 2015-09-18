package com.somerledsolutions.pa11y.client.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionsBuilder {


    public static Options buildOptions() {
        Options options = new Options();
        addHelp(options);

        return options;
    }

    private static void addHelp(Options options) {
        Option help = Option.builder()
                .argName("help")
                .longOpt("help")
                .desc("print this message")
                .build();
        options.addOption(help);
    }
}
