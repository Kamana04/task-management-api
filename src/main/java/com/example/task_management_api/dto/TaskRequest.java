package com.example.task_management_api.dto;

import com.example.task_management_api.domain.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {

    private String title;

    private String description;

    private TaskStatus taskStatus = TaskStatus.PENDING;

    private LocalDate dueDate;
}
