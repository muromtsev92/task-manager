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
        log.info("get all tasks");
        return taskService.getAllTasks();
    }
    @GetMapping(path="/{taskId}")
    public Task getTaskById(
            @PathVariable(name="taskId") Integer taskId){
        return taskService.getById(taskId);
    }
    @GetMapping(path="/today")
    public List<Task> getTaskByExecutionTime(){
        return taskService.getTodayTasks();
    }
    @PostMapping
    public Task saveTask(@RequestBody Task task) {
        log.info("post task " + task.getName());
        return taskService.createTask(task);
    }

    @PatchMapping(path="/{taskId}")
    public Task updateTask(
            @PathVariable(name="taskId") Integer taskId,
            @RequestBody Task task) {
        log.info("patching task");
        return taskService.patchTask(taskId, task);
    }

    @DeleteMapping(path="/{taskId}")
    public void deleteTask(@PathVariable Integer taskId) {
        log.info("deleting task");
        taskService.deleteTask(taskId);
    }
}
