package ru.test.taskmanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.taskmanager.model.Task;
import ru.test.taskmanager.model.TaskStatus;
import ru.test.taskmanager.repository.TaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        task.setTaskStatus(TaskStatus.NEW);
        return taskRepository.save(task);
    }

    public Task patchTask(Integer taskId, Task task) {
        Task updatedTask = taskRepository.findById(taskId)
                .orElseThrow(NoSuchElementException::new);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if(task.getId() != null) {
            updatedTask.setId(task.getId());
        }
        if(task.getName() != null) {
            updatedTask.setName(task.getName());
        }
        if(task.getDescription() != null) {
            updatedTask.setDescription(task.getDescription());
        }
        if(task.getDuration() != null) {
            updatedTask.setDuration(task.getDuration());
        }
        if(task.getExecutionTime() != null) {
            updatedTask.setExecutionTime(task.getExecutionTime());
        }
        if(task.getTaskStatus() != null) {
            updatedTask.setTaskStatus(task.getTaskStatus());
        }
        return taskRepository.save(updatedTask);
    }

    public void deleteTask(Integer taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task getById(Integer taskId) {
        Optional<Task> taskFromRepo = taskRepository.findById(taskId);
        if (taskFromRepo.isEmpty()){
            throw new NoSuchElementException("no such element in repository");
        }
        return taskFromRepo.get();
    }

    public List<Task> getTodayTasks() {
        LocalDateTime moment1 = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime moment2 = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        return taskRepository.findByExecutionTimeBetween(moment1, moment2);
    }
}

