package ru.test.taskmanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.taskmanager.model.Task;
import ru.test.taskmanager.model.TaskStatus;
import ru.test.taskmanager.repository.TaskRepository;
import java.util.List;

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
}

