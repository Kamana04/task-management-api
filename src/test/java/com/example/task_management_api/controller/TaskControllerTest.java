package com.example.task_management_api.controller;

import com.example.task_management_api.domain.TaskStatus;
import com.example.task_management_api.dto.TaskResponse;
import com.example.task_management_api.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    void shouldCreateTask() throws Exception {

        TaskResponse response = TaskResponse.builder()
                .id("1")
                .title("Spring boot")
                .status(TaskStatus.PENDING)
                .dueDate(LocalDate.now().plusDays(1))
                .build();

        Mockito.when(taskService.createTask(any()))
                .thenReturn(response);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "title":"Spring boot",
                            "description":"assignment",
                            "status":"PENDING",
                            "dueDate":"2030-01-01"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Spring boot"));
    }
}
