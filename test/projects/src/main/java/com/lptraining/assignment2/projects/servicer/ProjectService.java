package com.lptraining.assignment2.projects.servicer;

import com.lptraining.assignment2.projects.model.Project;

import java.util.List;


public interface ProjectService {

    Project save(Project project);
    Project update(Project project,int id);
    Project delete(int id);
    List<Project> getAllProjects();
    Project getProjectById(int id);
    Project isProjectActive(int id);
}
