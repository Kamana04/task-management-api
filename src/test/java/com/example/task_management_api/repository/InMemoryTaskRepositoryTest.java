package com.example.task_management_api.repository;

import com.example.task_management_api.domain.Task;
import com.example.task_management_api.domain.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskRepositoryTest {

    private InMemoryTaskRepository repository;

    @BeforeEach
    void setup() {
        repository = new InMemoryTaskRepository();
    }

    @Test
    void shouldSaveTask() {
        Task task = Task.builder()
                .id("1")
                .title("Task Management project")
                .status(TaskStatus.PENDING)
                .dueDate(LocalDate.now().plusDays(1))
                .build();

        repository.save(task);
        assertTrue(repository.findById("1").isPresent());
    }

    @Test
    void shouldDeleteTask() {

        Task task = Task.builder()
                .id("1")
                .title("Task")
                .status(TaskStatus.PENDING)
                .dueDate(LocalDate.now().plusDays(1))
                .build();

        repository.save(task);

        repository.deleteById("1");

        assertFalse(repository.findById("1").isPresent());
    }

    @Test
    void shouldReturnAllTasks() {

        repository.save(Task.builder()
                .id("1")
                .title("Task1")
                .status(TaskStatus.PENDING)
                .dueDate(LocalDate.now().plusDays(1))
                .build());

        repository.save(Task.builder()
                .id("2")
                .title("Task2")
                .status(TaskStatus.DONE)
                .dueDate(LocalDate.now().plusDays(2))
                .build());

        assertEquals(2, repository.findAll().size());
    }
}
