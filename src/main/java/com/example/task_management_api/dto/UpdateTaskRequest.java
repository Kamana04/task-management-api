package com.example.task_management_api.dto;

import com.example.task_management_api.domain.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTaskRequest {

    private String title;

    private String description;

    private TaskStatus taskStatus;

    @FutureOrPresent(message = "Due date must be in the future or present")
    private LocalDate dueDate;
}
