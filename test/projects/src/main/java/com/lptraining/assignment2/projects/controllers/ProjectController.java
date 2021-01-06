package com.lptraining.assignment2.projects.controllers;

import com.lptraining.assignment2.projects.model.Project;
import com.lptraining.assignment2.projects.servicer.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping(value = "/projects")
    public Project saveProject(@RequestBody Project project) {
        System.out.println("project : " + project.getIsActive());
        return projectService.save(project);
    }

    @PutMapping(value = "/projects/{id}")
    public ResponseEntity<Project> updateProject(@RequestBody Project project, @PathVariable int id) {
        Project updatedProject = projectService.update(project, id);
        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(updatedProject);
        }
    }

    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable int id) {

        Project removedTask = projectService.delete(id);
        if (removedTask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(removedTask);
    }

    @GetMapping(value = "/projects")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping(value = "/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        Project projectById = projectService.getProjectById(id);

        if (projectById == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(projectById);
        }
    }

    @GetMapping(value = "/projects/{id}/isActive")
    public String checkProjectIsActive(@PathVariable int id) {
        System.out.println("inside it");
        Project isProjectActive = projectService.isProjectActive(id);

        if (isProjectActive == null) {
            return "NOT-FOUND";
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't find Give Project");
        }
        return isProjectActive.getIsActive() ? "ACTIVE" : "NOT-ACTIVE";
//        return ResponseEntity.ok().body(isProjectActive.getIsActive()?"ACTIVE":"NOT_ACTIVE");
    }
}
