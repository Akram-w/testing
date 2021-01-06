package com.lptraining.assignment2.projects.repository;

import com.lptraining.assignment2.projects.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
