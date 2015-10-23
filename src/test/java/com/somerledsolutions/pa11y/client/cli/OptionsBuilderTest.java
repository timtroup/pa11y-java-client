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
import org.junit.Test;

import java.util.Collection;

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

        assertEquals(10, options.getOptions().size());

        assertPa11yOptions(options);

        OptionGroup optionGroup = options.getOptionGroup(options.getOption("c"));
        Collection<String> optionGroupNames = optionGroup.getNames();
        assertTrue(optionGroupNames.contains("c"));
        assertTrue(optionGroupNames.contains("l"));
        assertTrue(optionGroupNames.contains("r"));
        assertTrue(optionGroupNames.contains("g"));
        assertTrue(optionGroupNames.contains("d"));
    }

    @Test
    public void testGetAllOptions() throws Exception {
        Options options = OptionsBuilder.getAllOptions();

        assertEquals(11, options.getOptions().size());

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
        assertTaskIdOption(options);
        assertRunOption(options);
        assertRetrieveTaskOption(options);
        assertDeleteOption(options);
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

    private void assertTaskIdOption(Options options) {
        Option taskIdOption = options.getOption("tid");
        assertEquals("tid", taskIdOption.getOpt());
        assertEquals("Task ID", taskIdOption.getArgName());
        assertEquals("taskid", taskIdOption.getLongOpt());
        assertEquals("The ID of the task", taskIdOption.getDescription());
        assertTrue(taskIdOption.hasArg());
    }

    private void assertRunOption(Options options) {
        Option taskIdOption = options.getOption("r");
        assertEquals("r", taskIdOption.getOpt());
        assertEquals("Run", taskIdOption.getArgName());
        assertEquals("run", taskIdOption.getLongOpt());
        assertEquals("Run a task by ID, generating new results", taskIdOption.getDescription());
    }

    private void assertRetrieveTaskOption(Options options) {
        Option taskIdOption = options.getOption("g");
        assertEquals("g", taskIdOption.getOpt());
        assertEquals("Get", taskIdOption.getArgName());
        assertEquals("get", taskIdOption.getLongOpt());
        assertEquals("Get a single task by ID", taskIdOption.getDescription());
    }

    private void assertDeleteOption(Options options) {
        Option deleteTaskOption = options.getOption("d");
        assertEquals("d", deleteTaskOption.getOpt());
        assertEquals("Delete", deleteTaskOption.getArgName());
        assertEquals("delete", deleteTaskOption.getLongOpt());
        assertEquals("Delete a task by ID", deleteTaskOption.getDescription());
    }
}