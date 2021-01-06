package com.lptraining.assignment2.tasks.servicer;

import com.lptraining.assignment2.tasks.exceptions.ProjectNotActive;
import com.lptraining.assignment2.tasks.exceptions.ProjectNotExists;
import com.lptraining.assignment2.tasks.models.Task;
import com.lptraining.assignment2.tasks.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public Task save(Task task) throws ProjectNotActive, ProjectNotExists {
        String projectStatus = isActiveProject(task.getProjectId());
        if (projectStatus.equals("ACTIVE")) {
            return taskRepository.save(task);
        } else if (projectStatus.equals("NOT-ACTIVE")) {
            throw new ProjectNotActive("Project under id :" + task.getProjectId() + " not active");
        } else if (projectStatus.equals("NOT-FOUND")) {
            throw new ProjectNotExists("Project under id: " + task.getProjectId() + "not active");
        }
        return null;
    }

    @Override
    public Task update(Task task, int id) throws ProjectNotActive, ProjectNotExists {
        String projectStatus = isActiveProject(task.getProjectId());
        if (projectStatus.equals("ACTIVE")) {
            Optional<Task> taskById = taskRepository.findById(id);
            if (taskById.isPresent()) {
                Task fetchedTask = taskById.get();
                fetchedTask.setTaskName(task.getTaskName());
                fetchedTask.setTaskDescription(task.getTaskDescription());
                fetchedTask.setIsActive(task.getIsActive());
                return taskRepository.save(fetchedTask);
            }
        } else if (projectStatus.equals("NOT-ACTIVE")) {
            throw new ProjectNotActive("Project under id :" + task.getProjectId() + " not active");
        } else if (projectStatus.equals("NOT-FOUND")) {
            throw new ProjectNotExists("Project under id: " + task.getProjectId() + "not active");
        }
        return null;
    }

    @Override
    public List<Task> fetchAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task fetchTaskById(int id) {
        Optional<Task> taskById = taskRepository.findById(id);
        if (taskById.isPresent()) {
            return taskById.get();
        }
        return null;
    }

    @Override
    public Task removeTaskById(int id) {
        Optional<Task> taskById = taskRepository.findById(id);
        if (taskById.isPresent()) {
            taskRepository.deleteById(id);
            return taskById.get();
        }
        return null;
    }

    private String isActiveProject(int projectId) {
        String uri = "http://localhost:8080/projects/" + projectId + "/isActive";
//        String projectStatus = restTemplate.getForObject(uri, String.class);
        String projectStatus = restTemplate.getForObject(uri, String.class);
        return projectStatus;
    }
}
