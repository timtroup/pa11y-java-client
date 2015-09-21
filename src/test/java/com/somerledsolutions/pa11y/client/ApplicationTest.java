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

import com.somerledsolutions.pa11y.client.validation.OptionsValidator;
import org.apache.commons.cli.CommandLine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ApplicationTest {

    @InjectMocks
    private Application testee;

    @Mock
    private Pa11yRestClient client;

    @Mock
    private OptionsValidator optionsValidator;

    @Before
    public void setUp() {
        testee = new Application();
        initMocks(this);
    }

    @Test
    public void shouldCallCreateTaskOnClientWithCorrectArguments() throws Exception {

        when(optionsValidator.validateCreateOptions(any(CommandLine.class))).thenReturn(true);
        when(optionsValidator.isAccessiblityStandardValid(anyString())).thenReturn(true);

        testee.run("-c", "-n", "name of task", "-u", "http://0.0.0.0:3000", "-s", "WCAG2AAA");
        verify(client, times(1)).createTask("name of task", "http://0.0.0.0:3000", "WCAG2AAA");
    }

    @Test
    public void shouldNotCallCreateTaskOnClientWhenStandardIsNotRecognised() throws Exception {

        when(optionsValidator.validateCreateOptions(any(CommandLine.class))).thenReturn(true);
        when(optionsValidator.isAccessiblityStandardValid(anyString())).thenReturn(false);

        testee.run("-c", "-n", "name of task", "-u", "http://0.0.0.0:3000", "-s", "IAmNotAStandard");
        verify(client, times(0)).createTask("name of task", "http://0.0.0.0:3000", "IAmNotAStandard");
    }

    @Test
    public void shouldCallGetTasksOnClientWithCorrectArguments() throws Exception {

        when(optionsValidator.validateListOptions(any(CommandLine.class))).thenReturn(true);

        testee.run("-l", "-u", "http://0.0.0.0:3000");
        verify(client, times(1)).getTasks("http://0.0.0.0:3000", false);
    }

    @Test
    public void shouldNotCallGetTasksOnClientWhenValidationFails() throws Exception {

        when(optionsValidator.validateListOptions(any(CommandLine.class))).thenReturn(true);

        testee.run("-l");
        verify(client, times(0)).getTasks("http://0.0.0.0:3000", false);
    }

    @Test
    public void shouldCallRunTaskOnClientWithCorrectArguments() throws Exception {

        when(optionsValidator.validateRunOptions(any(CommandLine.class))).thenReturn(true);
        when(client.runTask("someId", "http://0.0.0.0:3000")).thenReturn(HttpStatus.ACCEPTED);

        testee.run("-r", "-tid", "someId", "-u", "http://0.0.0.0:3000");
        verify(client, times(1)).runTask("someId", "http://0.0.0.0:3000");
    }

    @Test
    public void shouldNotCallRunTaskOnClientWhenValidationFails() throws Exception {

        when(optionsValidator.validateRunOptions(any(CommandLine.class))).thenReturn(false);

        testee.run("-r", "-tid", "someId", "-u", "http://0.0.0.0:3000");
        verify(client, times(0)).runTask("someId", "http://0.0.0.0:3000");
    }

}