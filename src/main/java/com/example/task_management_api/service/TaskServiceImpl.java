package com.example.task_management_api.service;

import com.example.task_management_api.domain.Task;
import com.example.task_management_api.domain.TaskStatus;
import com.example.task_management_api.dto.TaskRequest;
import com.example.task_management_api.dto.TaskResponse;
import com.example.task_management_api.dto.UpdateTaskRequest;
import com.example.task_management_api.exception.TaskNotFoundException;
import com.example.task_management_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

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
                -> new TaskNotFoundException("Task not found with id: " + id));
        return mapToTaskResponse(task);
    }

    @Override
    public List<TaskResponse> getAllTasks(TaskStatus status, int page, int size) {
        List<Task> tasks = taskRepository.findAll();

        //filter by status
        if (status != null) {
            tasks  = tasks.stream().filter(task -> task.getStatus() == status)
                    .toList();
        }
        //sort tasks by due date
        tasks.stream().sorted(Comparator.comparing(Task :: getDueDate)).toList();

        //pagination
        int start = page * size;

        if(start >= tasks.size()) {
            return Collections.emptyList();
        }
        int end = Math.min(start + size, tasks.size());

        return tasks.subList(start, end)
                .stream()
                .map(this::mapToTaskResponse)
                .toList();
    }

    @Override
    public void deleteTaskById(String id) {
       if (!taskRepository.existsById(id)) {
           throw new TaskNotFoundException("Task not found with id: " + id);
       }

       taskRepository.deleteById(id);

    }

    @Override
    public TaskResponse updateTask(String id, UpdateTaskRequest taskRequest) {

        //find existing task
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Task not found with id: " + id));

        if (taskRequest.getTitle() != null) {
            task.setTitle(taskRequest.getTitle());
        }

        if (taskRequest.getDescription() != null) {
            task.setDescription(taskRequest.getDescription());
        }

        if (taskRequest.getDueDate() != null) {
            task.setDueDate(taskRequest.getDueDate());
        }

        if (taskRequest.getTaskStatus() != null) {
            task.setStatus(taskRequest.getTaskStatus());
        }

        //save updated task
        taskRepository.save(task);
        return mapToTaskResponse(task);
    }

    private TaskResponse mapToTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .build();
    }
}
