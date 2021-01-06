package com.lptraining.assignment2.tasks.controllers;

import com.lptraining.assignment2.tasks.exceptions.ProjectNotActive;
import com.lptraining.assignment2.tasks.exceptions.ProjectNotExists;
import com.lptraining.assignment2.tasks.models.Task;
import com.lptraining.assignment2.tasks.servicer.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping(value = "/tasks")
    public ResponseEntity<Task> save(@RequestBody Task task) {
        try {
            Task responseTask = taskService.save(task);
            return ResponseEntity.ok().body(responseTask);
        } catch (ProjectNotActive throwables) {
            return ResponseEntity.noContent().build();
        } catch (ProjectNotExists throwables) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity<Task> update(@RequestBody Task task, @PathVariable int id) {
        try {
            Task responseTask = taskService.update(task, id);
            return ResponseEntity.ok().body(responseTask);
        } catch (ProjectNotActive throwables) {
            return ResponseEntity.noContent().build();
        } catch (ProjectNotExists throwables) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/tasks")
    public List<Task> findAllTasks() {
        return taskService.fetchAllTasks();
    }

    @GetMapping(value = "/tasks/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable int id) {
        Task fetchedTask = taskService.fetchTaskById(id);
        if (fetchedTask == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(fetchedTask);
        }
    }
    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<Task> removeTask(@PathVariable int id){
        Task removedTask = taskService.removeTaskById(id);
        if(removedTask==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(removedTask);
    }
}
