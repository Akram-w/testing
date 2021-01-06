package com.lptraining.assignment2.tasks.servicer;

import com.lptraining.assignment2.tasks.exceptions.ProjectNotActive;
import com.lptraining.assignment2.tasks.exceptions.ProjectNotExists;
import com.lptraining.assignment2.tasks.models.Task;

import java.util.List;

public interface TaskService {

    Task save(Task task) throws ProjectNotActive, ProjectNotExists;
    Task update(Task task,int id) throws ProjectNotActive, ProjectNotExists;
    List<Task> fetchAllTasks();
    Task fetchTaskById(int id);
    Task removeTaskById(int id);
}
