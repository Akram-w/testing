package com.lptraining.assignment2.projects.servicer;

import com.lptraining.assignment2.projects.model.Project;
import com.lptraining.assignment2.projects.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository repository;

    @Override
    public Project save(Project project) {
        return repository.save(project);
    }

    @Override
    public Project update(Project project, int id) {
        Optional<Project> projectById = repository.findById(id);

        if (projectById.isPresent()) {
            Project fetchedProject = projectById.get();
            fetchedProject.setProjectName(project.getProjectName());
            fetchedProject.setProjectDescription(project.getProjectDescription());
            fetchedProject.setProjectLeadName(project.getProjectLeadName());
            fetchedProject.setIsActive(project.getIsActive());
            return repository.save(fetchedProject);

        } else {
            return null;
        }

    }

    @Override
    public Project delete(int id) {
        Optional<Project> projectById = repository.findById(id);

        if (projectById.isPresent()) {
            repository.deleteById(id);
            return projectById.get();
        }
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    @Override
    public Project getProjectById(int id) {
        Optional<Project> projectById = repository.findById(id);

        if (projectById.isPresent()) {
            return projectById.get();
        }
        return null;
    }

    @Override
    public Project isProjectActive(int id) {
        return getProjectById(id);
    }
}