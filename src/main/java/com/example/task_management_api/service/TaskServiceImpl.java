package com.example.task_management_api.service;

import com.example.task_management_api.domain.Task;
import com.example.task_management_api.domain.TaskStatus;
import com.example.task_management_api.dto.TaskRequest;
import com.example.task_management_api.dto.TaskResponse;
import com.example.task_management_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {

        Task task = Task.builder()
                .id(UUID.randomUUID().toString())
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .dueDate(taskRequest.getDueDate())
                // Set default status if not provided
                .status(taskRequest.getTaskStatus() == null ?
                        TaskStatus.PENDING : taskRequest.getTaskStatus())
                .build();

        taskRepository.save(task);
        // Convert entity to response DTO
        return mapToTaskResponse(task);
    }

    @Override
    public TaskResponse getTaskById(String id) {
        Task task = taskRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Task not found with id: " + id));
        return mapToTaskResponse(task);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                // Sort tasks by due date
                .sorted(Comparator.comparing(Task::getDueDate))
                .map(this::mapToTaskResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    private TaskResponse mapToTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .taskStatus(task.getStatus())
                .dueDate(task.getDueDate())
                .build();
    }
}
