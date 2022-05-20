package com.example.bugtracker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bug {
    private int id;
    private String name;
    private LocalDateTime creationDate;
    private String description;
    private Severity severity;
    private Status status;

    public Bug(String name, LocalDateTime creationDate, Severity severity, String description) {
        this(0, name, creationDate, description, severity, Status.OPEN);
    }
}
