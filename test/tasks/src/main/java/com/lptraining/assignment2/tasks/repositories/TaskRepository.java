package com.lptraining.assignment2.tasks.repositories;

import com.lptraining.assignment2.tasks.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task,Integer> {
}
