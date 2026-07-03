package com.example.task_management_api.service;

import com.example.task_management_api.domain.Task;
import com.example.task_management_api.domain.TaskStatus;
import com.example.task_management_api.dto.TaskRequest;
import com.example.task_management_api.dto.TaskResponse;
import com.example.task_management_api.exception.TaskNotFoundException;
import com.example.task_management_api.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;
    @Test
    void shouldCreateTask() {

        TaskRequest request = new TaskRequest();
        request.setTitle("Assignment");
        request.setDescription("DDD");
        request.setTaskStatus(TaskStatus.PENDING);
        request.setDueDate(LocalDate.now().plusDays(2));

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TaskResponse response = taskService.createTask(request);

        assertEquals("Assignment", response.getTitle());
        assertEquals(TaskStatus.PENDING, response.getStatus());

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {

        when(taskRepository.findById("100"))
                .thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> taskService.getTaskById("100"));
    }

    @Test
    void shouldReturnTask() {

        Task task = Task.builder()
                .id("1")
                .title("Spring boot")
                .status(TaskStatus.PENDING)
                .dueDate(LocalDate.now().plusDays(1))
                .build();

        when(taskRepository.findById("1"))
                .thenReturn(Optional.of(task));

        TaskResponse response = taskService.getTaskById("1");

        assertEquals("Spring boot", response.getTitle());
    }

}
