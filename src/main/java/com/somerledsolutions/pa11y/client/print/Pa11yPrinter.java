package com.somerledsolutions.pa11y.client.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.somerledsolutions.pa11y.client.model.Task;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class Pa11yPrinter {

    public void printTasks(List<Task> tasks, OutputStream os) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        for(Task task: tasks) {
            os.write(mapper.writeValueAsString(task).getBytes());
        }

    }
}
