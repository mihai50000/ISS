package com.example.bugtracker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Complain {
    private String name;
    private LocalDateTime dateTime;
    private Severity severity;
    private String description;
    private Bug linkedBug;

    public Complain(String name, LocalDateTime localDateTime, Severity severity, String description) {
        this.name = name;
        this.dateTime = localDateTime;
        this.severity = severity;
        this.description = description;
    }
}
