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
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

public interface Pa11yRestClient {

    /**
     * Create a new task
     *
     * @param name     The name of the new task.
     * @param url      The URL of the new task.
     * @param standard The accessibility standard to test the URL against. One of Section508, WCAG2A, WCAG2AA, WCAG2AAA
     */
    Task createTask(String name, String url, String standard);

    /**
     * Get all of the tasks in the application
     *
     * @param lastres Whether to include the last_result property in the output
     */
    List<Task> getTasks(String url, boolean lastres);

    /**
     * Get results for all of the tasks in the application.
     *
     * @param from The earliest recorded results to retrieve. Defaults to 30 days ago.
     * @param to   The latest recorded results to retrieve. Defaults to now
     * @param full Whether to include full results in the response. If this is not present, then only
     *             error/warning/notice counts will be included
     */
    void getTasksResults(Date from, Date to, boolean full);

    /**
     * Get a single task by ID
     *
     * @param taskId  the ID of the task to retrieve
     * @param lastres Whether to include the last_result property in the output
     * @return the task found
     */
    Task getTask(String taskId, String url, boolean lastres);

    //    public void editTask(String taskId, String name);

    /**
     * Delete a task by ID.
     *
     * @param id the ID of the task to delete
     */
    void deleteTask(String id, String url);

    /**
     * Run a task by ID, generating new results
     *
     * @param taskId the ID of the task to run
     */
    HttpStatus runTask(String taskId, String url);

    /**
     * Get results for a single task by ID.
     *
     * @param taskId the ID of the task
     * @param from   The earliest recorded results to retrieve
     * @param to     The latest recorded results to retrieve. Defaults to now.
     * @param full   Whether to include full results in the response. If this is not present, then only
     *               error/warning/notice counts will be included. Defaults to false.
     */
    void getTaskResults(String taskId, Date from, Date to, boolean full);

    /**
     * Get a single result by ID.
     *
     * @param taskId   the ID of the task
     * @param resultId the ID of the result to retrieve
     * @param full     Whether to include full results in the response. If this is not present, then only
     *                 error/warning/notice counts will be included. Defaults to false.
     */
    void getTaskResult(String taskId, String resultId, boolean full);

}
