package ru.test.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.test.taskmanager.dto.TaskStatusDto;
import ru.test.taskmanager.model.Task;
import ru.test.taskmanager.model.TaskStatus;
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
    public List<Task> getTasksToday(
            @RequestParam(name="status", required = false) String status
    ){
        return taskService.getTodayTasks(status);
    }

    @GetMapping(path="/week")
    public List<Task> getTasksWeek(
            @RequestParam(name="status", required = false) String status
    ){
        return taskService.getWeekTasks(status);
    }

    @GetMapping(path="/month")
    public List<Task> getTasksMonth(
            @RequestParam(name="status", required = false) String status
    ){
        return taskService.getMonthTasks(status);
    }

    @PostMapping
    public Task saveTask(@RequestBody Task task) {
        log.info("post task");
        return taskService.createTask(task);
    }

    @PatchMapping(path="/{taskId}")
    public Task updateTask(
            @PathVariable(name="taskId") Integer taskId,
            @RequestBody Task task) {
        log.info("patching task");
        return taskService.patchTask(taskId, task);
    }

    @PatchMapping(path="/set-status")
    public Task updateTaskStatus(
            @RequestBody TaskStatusDto taskStatusDto
    ) {
        log.info("patching taskStatus: id=" + taskStatusDto.getId(), ", status:" + taskStatusDto.getTaskStatus());
        return taskService.updateTaskStatus(taskStatusDto);
    }

    @DeleteMapping(path="/{taskId}")
    public void deleteTask(@PathVariable Integer taskId) {
        log.info("deleting task");
        taskService.deleteTask(taskId);
    }
}
