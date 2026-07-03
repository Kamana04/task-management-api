package com.example.task_management_api.dto;

import com.example.task_management_api.domain.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private String id;

    private String title;

    private String description;

    private TaskStatus taskStatus;

    private LocalDate dueDate;
}
