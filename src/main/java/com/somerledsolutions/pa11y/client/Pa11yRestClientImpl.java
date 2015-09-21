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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class Pa11yRestClientImpl implements Pa11yRestClient {

    private static final Logger log = LoggerFactory.getLogger(Pa11yRestClientImpl.class);

    private final String getTasksResultsUrl = "{host}/tasks/results";

    @Autowired
    private RestTemplate restTemplate;

    public Pa11yRestClientImpl() {

    }

    public Task createTask(String name, String url, String standard) {

        Task task = new Task();
        task.setName(name);
        task.setUrl(url);
        task.setStandard(standard);

        Map<String, String> params = new HashMap<String, String>();
        params.put("host", url);

        String createTaskUrl = "{host}/tasks";
        return restTemplate.postForObject(createTaskUrl, task, Task.class, params);
    }

    @Override
    public List<Task> getTasks(String url, boolean lastres) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("host", url);

        String getTasksUrl = "{host}/tasks";
        return Arrays.asList(restTemplate.getForObject(getTasksUrl, Task[].class, params));

    }

    @Override
    public void getTasksResults(Date from, Date to, boolean full) {

    }

    @Override
    public Task getTask(String taskId, String url, boolean lastres) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("host", url);
        params.put("id", taskId);
        params.put("lastres", String.valueOf(lastres));

        String getTaskUrl = "{host}/tasks/{id}?lastres={lastres}";
        Task task = restTemplate.getForObject(getTaskUrl, Task.class, params);
        log.info(task.toString());
        return task;
    }

    @Override
    public void deleteTask(String id) {

    }

    @Override
    public HttpStatus runTask(String taskId, String url) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("host", url);
        params.put("id", taskId);

        String runTaskUrl = "{host}/tasks/{id}/run";
        ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity(runTaskUrl, null, null, params);
        return objectResponseEntity.getStatusCode();
    }

    @Override
    public void getTaskResults(String taskId, Date from, Date to, boolean full) {

    }

    @Override
    public void getTaskResult(String taskId, String resultId, boolean full) {

    }
}