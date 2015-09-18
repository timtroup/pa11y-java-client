package com.somerledsolutions.pa11y.client;

import com.somerledsolutions.pa11y.client.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Pa11yRestClientImpl implements Pa11yRestClient {

    private static final Logger log = LoggerFactory.getLogger(Pa11yRestClientImpl.class);

    private String host;

    private final String createTaskUrl = "{host}/tasks";
    private final String getTasksUrl = "{host}/tasks";
    private final String getTasksResultsUrl = "{host}/tasks/results";
    private final String getTaskUrl = "{host}/tasks/{id}?lastres={lastres}";

    @Autowired
    private RestTemplate restTemplate;

    public Pa11yRestClientImpl(String h) {
        host = h;
    }

    public Pa11yRestClientImpl() {

    }

    public void setHost(String h) {
        host = h;
    }

    public void createTask(String name, String url, String standard) {

        Task task = new Task();
        task.setName(name);
        task.setUrl(url);
        task.setStandard(standard);

        Map<String, String> params = new HashMap<String, String>();
        params.put("host", host);

        restTemplate.postForObject(createTaskUrl, task, Task.class, params);
    }

    @Override
    public void getTasks(boolean lastres) {

    }

    @Override
    public void getTasksResults(Date from, Date to, boolean full) {

    }

    @Override
    public Task getTask(String taskId, boolean lastres) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("host", host);
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
    public void runTask(String id) {

    }

    @Override
    public void getTaskResults(String taskId, Date from, Date to, boolean full) {

    }

    @Override
    public void getTaskResult(String taskId, String resultId, boolean full) {

    }
}