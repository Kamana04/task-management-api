package com.example.task_management_api.repository;

import com.example.task_management_api.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository {

    Task save(Task task);

    Optional<Task> findById(String id);

    List<Task> findAll();

    void deleteById(String id);

    boolean existsById(String id);
}
