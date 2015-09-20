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

    private final String createTaskUrl = "{host}/tasks";
    private final String getTasksUrl = "{host}/tasks";
    private final String getTasksResultsUrl = "{host}/tasks/results";
    private final String getTaskUrl = "{host}/tasks/{id}?lastres={lastres}";
    private final String runTaskUrl = "{host}/tasks/{id}/run";

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

        return restTemplate.postForObject(createTaskUrl, task, Task.class, params);
    }

    @Override
    public List<Task> getTasks(String url, boolean lastres) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("host", url);

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