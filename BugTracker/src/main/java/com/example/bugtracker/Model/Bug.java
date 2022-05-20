package com.example.bugtracker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bug {
    private String name;
    private LocalDateTime creationDate;
    private String description;
    private Severity severity;
}
