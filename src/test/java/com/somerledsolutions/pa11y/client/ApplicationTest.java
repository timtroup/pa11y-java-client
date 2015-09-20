package com.somerledsolutions.pa11y.client;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ApplicationTest {

    @InjectMocks
    private Application testee;

    @Mock
    private Pa11yRestClient client;

    @Before
    public void setUp() {
        testee = new Application();
        initMocks(this);
    }

    @Test
    public void shouldCallCreateTaskOnClientWithCorrectArguments() throws Exception {

        testee.run("-c", "-n", "name of task", "-h", "http://0.0.0.0:3000", "-s", "WCAG2AAA");
        verify(client, times(1)).createTask("name of task", "http://0.0.0.0:3000", "WCAG2AAA");
    }

}