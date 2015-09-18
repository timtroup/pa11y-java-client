package com.somerledsolutions.pa11y.client;

import com.somerledsolutions.pa11y.client.model.Task;

import java.util.Date;

public interface Pa11yRestClient {

    /**
     * Create a new task
     *
     * @param name     The name of the new task.
     * @param url      The URL of the new task.
     * @param standard The accessibility standard to test the URL against. One of Section508, WCAG2A, WCAG2AA, WCAG2AAA
     */
    public void createTask(String name, String url, String standard);

    /**
     * Get all of the tasks in the application
     *
     * @param lastres Whether to include the last_result property in the output
     */
    public void getTasks(boolean lastres);

    /**
     * Get results for all of the tasks in the application.
     *
     * @param from The earliest recorded results to retrieve. Defaults to 30 days ago.
     * @param to   The latest recorded results to retrieve. Defaults to now
     * @param full Whether to include full results in the response. If this is not present, then only
     *             error/warning/notice counts will be included
     */
    public void getTasksResults(Date from, Date to, boolean full);

    /**
     * Get a single task by ID
     *
     * @param taskId  the ID of the task to retrieve
     * @param lastres Whether to include the last_result property in the output
     * @return the task found
     */
    public Task getTask(String taskId, boolean lastres);

    //    public void editTask(String taskId, String name);

    /**
     * Delete a task by ID.
     *
     * @param id the ID of the task to delete
     */
    public void deleteTask(String id);

    /**
     * Run a task by ID, generating new results
     *
     * @param id the ID of the task to run
     */
    public void runTask(String id);

    /**
     * Get results for a single task by ID.
     *
     * @param taskId the ID of the task
     * @param from   The earliest recorded results to retrieve
     * @param to     The latest recorded results to retrieve. Defaults to now.
     * @param full   Whether to include full results in the response. If this is not present, then only
     *               error/warning/notice counts will be included. Defaults to false.
     */
    public void getTaskResults(String taskId, Date from, Date to, boolean full);

    /**
     * Get a single result by ID.
     *
     * @param taskId   the ID of the task
     * @param resultId the ID of the result to retrieve
     * @param full     Whether to include full results in the response. If this is not present, then only
     *                 error/warning/notice counts will be included. Defaults to false.
     */
    public void getTaskResult(String taskId, String resultId, boolean full);


    void setHost(String s);
}
