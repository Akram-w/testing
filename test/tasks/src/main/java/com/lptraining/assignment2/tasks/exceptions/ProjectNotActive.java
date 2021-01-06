package com.lptraining.assignment2.tasks.exceptions;

import java.sql.SQLException;

public class ProjectNotActive extends SQLException {
    public ProjectNotActive(String message) {
        super(message);
    }
}
