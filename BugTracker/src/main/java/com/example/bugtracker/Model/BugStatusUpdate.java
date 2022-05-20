package com.example.bugtracker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugStatusUpdate {
    private Status newStatus;
    private String description;
    private User user;
    private LocalDateTime updateTime;
}
