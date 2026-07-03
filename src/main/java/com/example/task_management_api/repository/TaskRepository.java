package com.example.task_management_api.repository;

import com.example.task_management_api.domain.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository {

    Task save(Task task);
}
