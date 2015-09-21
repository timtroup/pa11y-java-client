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

import com.somerledsolutions.pa11y.client.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void testGetTaskWhenLastresOptionPresent() throws Exception {

        Map<String, String> params = new HashMap<String, String>();
        params.put("host", "HOST");
        params.put("id", "taskId");
        params.put("lastres", "true");

        Task task = new Task();
        when(restTemplate.getForObject(eq("{host}/tasks/{id}?lastres={lastres}"), eq(Task.class), eq(params))).thenReturn(task);

        Task actualTask = testee.getTask("taskId", "HOST", true);

        verify(restTemplate, times(1)).getForObject(eq("{host}/tasks/{id}?lastres={lastres}"), eq(Task.class), eq(params));

        assertSame(task, actualTask);

    }

    @Test
    public void testGetTaskWhenLastresOptionMissing() throws Exception {

        Map<String, String> params = new HashMap<String, String>();
        params.put("host", "HOST");
        params.put("id", "taskId");

        Task task = new Task();
        when(restTemplate.getForObject(eq("{host}/tasks/{id}"), eq(Task.class), eq(params))).thenReturn(task);

        Task actualTask = testee.getTask("taskId", "HOST", false);

        verify(restTemplate, times(1)).getForObject(eq("{host}/tasks/{id}"), eq(Task.class), eq(params));

        assertSame(task, actualTask);

    }

    @Test
    public void testDeleteTask() throws Exception {

    }

    @Test
    public void testRunTask() throws Exception {

        Map<String, String> params = new HashMap<String, String>();
        params.put("host", "HOST");
        params.put("id", "taskId");

        ResponseEntity<Object> entity = new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        when(restTemplate.postForEntity("{host}/tasks/{id}/run", null, null, params)).thenReturn(entity);

        HttpStatus status = testee.runTask("taskId", "HOST");

        verify(restTemplate, times(1)).postForEntity("{host}/tasks/{id}/run", null, null, params);

        assertSame(entity.getStatusCode(), status);

    }

    @Test
    public void testGetTaskResults() throws Exception {

    }

    @Test
    public void testGetTaskResult() throws Exception {

    }
}