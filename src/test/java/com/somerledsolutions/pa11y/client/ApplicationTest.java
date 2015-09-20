package com.somerledsolutions.pa11y.client;

import com.somerledsolutions.pa11y.client.validation.OptionsValidator;
import org.apache.commons.cli.CommandLine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

}