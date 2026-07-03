package com.example.task_management_api.repository;

import com.example.task_management_api.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryTaskRepository implements TaskRepository{

    //key -> id
    //value -> task
    private final ConcurrentHashMap<String, Task> taskStore = new ConcurrentHashMap<>();

    @Override
    public Task save(Task task) {
        taskStore.put(task.getId(), task);
        return task;
    }

    @Override
    public Optional<Task> findById(String id) {
        return Optional.ofNullable(taskStore.get(id));
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(taskStore.values());
    }

    @Override
    public void deleteById(String id) {
        taskStore.remove(id);

    }

    @Override
    public boolean existsById(String id) {
        return taskStore.containsKey(id);
    }
}
