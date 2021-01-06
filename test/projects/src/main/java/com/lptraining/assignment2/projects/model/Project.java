package com.lptraining.assignment2.projects.model;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue
    int id;

    String projectName;
    String projectLeadName;
    String projectDescription;
    boolean isActive;

    public Project() {
    }

    public Project(int id, String projectName, String projectLeadName, String projectDescription, boolean isActive) {
        this.id = id;
        this.projectName = projectName;
        this.projectLeadName = projectLeadName;
        this.projectDescription = projectDescription;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectLeadName() {
        return projectLeadName;
    }

    public void setProjectLeadName(String projectLeadName) {
        this.projectLeadName = projectLeadName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}
