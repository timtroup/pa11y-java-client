package com.somerledsolutions.pa11y.client;

import com.somerledsolutions.pa11y.client.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertSame;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Pa11yRestClientImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private Pa11yRestClientImpl testee = new Pa11yRestClientImpl();

    @Test
    public void testCreateTask() throws Exception {

    }

    @Test
    public void testGetTasks() throws Exception {

    }

    @Test
    public void testGetTasksResults() throws Exception {

    }

    @Test
    public void testGetTask() throws Exception {

        Map<String, String> params = new HashMap<String, String>();
        params.put("host", "HOST");
        params.put("id", "taskId");
        params.put("lastres", "false");

        Task task = new Task();
        when(restTemplate.getForObject(eq("{host}/tasks/{id}?lastres={lastres}"), eq(Task.class), eq(params))).thenReturn(task);

        Task actualTask = testee.getTask("taskId", "HOST", false);

        verify(restTemplate, times(1)).getForObject(eq("{host}/tasks/{id}?lastres={lastres}"), eq(Task.class), eq(params));

        assertSame(task, actualTask);

    }

    @Test
    public void testDeleteTask() throws Exception {

    }

    @Test
    public void testRunTask() throws Exception {

    }

    @Test
    public void testGetTaskResults() throws Exception {

    }

    @Test
    public void testGetTaskResult() throws Exception {

    }
}