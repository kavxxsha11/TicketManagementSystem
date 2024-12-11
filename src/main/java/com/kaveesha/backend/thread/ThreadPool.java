package com.kaveesha.backend.thread;

import org.springframework.stereotype.Component;

// The @Component annotation is used to indicate this class is a Spring
@Component
public class ThreadPool {

    // This method is used to submit a task to the thread pool
    public void submitTask(Runnable task) {
        // Create a new thread to run the task
        Thread thread = new Thread(task);
        thread.start();  // Start the task in a new thread
    }

    // This method is used to shut down the thread pool
    public void shutdown() {
        // Print a message indicating the thread pool is shutting down
        System.out.println("Thread pool shutdown.");
    }
}
