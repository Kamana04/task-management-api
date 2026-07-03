package com.example.task_management_api.service;

import com.example.task_management_api.dto.TaskRequest;
import com.example.task_management_api.dto.TaskResponse;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse getTaskById(String id);
}
