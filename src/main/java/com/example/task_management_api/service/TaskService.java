package com.example.task_management_api.service;

import com.example.task_management_api.domain.TaskStatus;
import com.example.task_management_api.dto.TaskRequest;
import com.example.task_management_api.dto.TaskResponse;
import com.example.task_management_api.dto.UpdateTaskRequest;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse getTaskById(String id);

    List<TaskResponse> getAllTasks(TaskStatus status, int page, int size);

    void deleteTaskById(String id);

    TaskResponse updateTask(String id, UpdateTaskRequest taskRequest);
}
