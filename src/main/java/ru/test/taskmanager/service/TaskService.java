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
                .orElseThrow(()->new NoSuchElementException());
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
        if(task.getStartTime() != null) {
            updatedTask.setStartTime(task.getStartTime());
        }
        if(task.getTaskStatus() != null) {
            updatedTask.setTaskStatus(task.getTaskStatus());
        }
        return taskRepository.save(updatedTask);
    }

    public void deleteTask(Integer taskId) {
        taskRepository.deleteById(taskId);
    }
}

