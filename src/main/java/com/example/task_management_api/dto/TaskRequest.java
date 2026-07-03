package com.example.task_management_api.dto;

import com.example.task_management_api.domain.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {

    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;

    private TaskStatus taskStatus = TaskStatus.PENDING;

    @NotNull(message = "Due date is mandatory")
    @FutureOrPresent(message = "Due date must be in the future or present")
    private LocalDate dueDate;
}
