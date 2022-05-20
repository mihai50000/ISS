package com.example.bugtracker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Complain {
    private int id;
    private String name;
    private LocalDateTime dateTime;
    private Severity severity;
    private String description;
    private Bug linkedBug;

    public Complain(int id, String name, LocalDateTime localDateTime, Severity severity, String description) {
        this(id, name, localDateTime, severity, description, null);
    }

    public Complain(String name, LocalDateTime localDateTime, Severity severity, String description) {
        this(0, name, localDateTime, severity, description);
    }
}
