package ru.test.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.test.taskmanager.model.Task;
import ru.test.taskmanager.service.TaskService;
import java.util.List;

@RestController
@RequestMapping(path="/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {
    private final TaskService taskService;
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
    @PostMapping
    public Task saveTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }
}
