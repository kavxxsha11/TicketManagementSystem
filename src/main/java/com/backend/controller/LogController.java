package com.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class LogController {

    @GetMapping("/log")
    public List<String> getSystemLogs() {
        try {
            return Files.readAllLines(Paths.get("system.log"));
        } catch (IOException e) {
            return List.of("Error reading system logs: " + e.getMessage());
        }
    }
}
