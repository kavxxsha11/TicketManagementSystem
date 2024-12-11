package com.kaveesha.backend.thread;

import org.springframework.stereotype.Component;

@Component
public class ThreadPool {

    // This class now simply manages threads manually.
    
    public void submitTask(Runnable task) {
        Thread thread = new Thread(task);
        thread.start();  // Start the task in a new thread
    }

    public void shutdown() {
        // In this case, since we are managing threads manually,
        // there is no specific shutdown mechanism needed
        // (as threads will be stopped via interrupt when the system is stopped).
        System.out.println("Thread pool shutdown.");
    }
}
