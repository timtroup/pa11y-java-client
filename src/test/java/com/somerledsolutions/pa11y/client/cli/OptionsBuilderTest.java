package com.somerledsolutions.pa11y.client.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class OptionsBuilderTest {

    @Test
    public void testBuildHelpOptions() throws Exception {
        Options options = OptionsBuilder.buildHelpOptions();
        assertEquals(1, options.getOptions().size());

        assertHelpOption(options);
    }

    @Test
    public void testBuildPa11yOptions() throws Exception {
        Options options = OptionsBuilder.buildPa11yOptions();

        assertEquals(6, options.getOptions().size());

        assertPa11yOptions(options);

        OptionGroup optionGroup = options.getOptionGroup(options.getOption("c"));
        Collection<String> optionGroupNames = optionGroup.getNames();
        assertTrue(optionGroupNames.contains("c"));
        assertTrue(optionGroupNames.contains("l"));
    }

    @Test
    public void testGetAllOptions() throws Exception {
        Options options = OptionsBuilder.getAllOptions();

        assertEquals(7, options.getOptions().size());

        assertHelpOption(options);
        assertPa11yOptions(options);
    }

    private void assertHelpOption(Options options) {
        Option helpOption = options.getOption("h");
        assertEquals("h", helpOption.getOpt());
        assertEquals("Help", helpOption.getArgName());
        assertEquals("help", helpOption.getLongOpt());
        assertEquals("Shows this help", helpOption.getDescription());
    }

    private void assertPa11yOptions(Options options) {
        assertCreateOption(options);
        assertListTasksOption(options);
        assertNameOption(options);
        assertUrlOption(options);
        assertStandardOption(options);
        assertLastResultOption(options);
    }

    private void assertCreateOption(Options options) {
        Option createOption = options.getOption("c");
        assertEquals("c", createOption.getOpt());
        assertEquals("Create task", createOption.getArgName());
        assertEquals("create", createOption.getLongOpt());
        assertEquals("Create a new task", createOption.getDescription());
    }

    private void assertListTasksOption(Options options) {
        Option createOption = options.getOption("l");
        assertEquals("l", createOption.getOpt());
        assertEquals("List all tasks", createOption.getArgName());
        assertEquals("list", createOption.getLongOpt());
        assertEquals("Get all of the tasks in the application", createOption.getDescription());
    }

    private void assertNameOption(Options options) {
        Option createOption = options.getOption("n");
        assertEquals("n", createOption.getOpt());
        assertEquals("Name", createOption.getArgName());
        assertEquals("name", createOption.getLongOpt());
        assertEquals("The name of the task", createOption.getDescription());
    }

    private void assertUrlOption(Options options) {
        Option createOption = options.getOption("u");
        assertEquals("u", createOption.getOpt());
        assertEquals("URL", createOption.getArgName());
        assertEquals("url", createOption.getLongOpt());
        assertEquals("The base URL of the pa11y webservice", createOption.getDescription());
    }

    private void assertStandardOption(Options options) {
        Option createOption = options.getOption("s");
        assertEquals("s", createOption.getOpt());
        assertEquals("Accessibility standard", createOption.getArgName());
        assertEquals("standard", createOption.getLongOpt());
        assertEquals("The accessibility standard to test the URL against. Must be one of Section508, WCAG2A, WCAG2AA, WCAG2AAA", createOption.getDescription());
    }

    private void assertLastResultOption(Options options) {
        Option createOption = options.getOption("lr");
        assertEquals("lr", createOption.getOpt());
        assertEquals("Last result", createOption.getArgName());
        assertEquals("lastres", createOption.getLongOpt());
        assertEquals("Include the last_result property in the output", createOption.getDescription());
    }
}