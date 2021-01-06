package com.lptraining.assignment2.tasks.exceptions;

import java.sql.SQLException;

public class ProjectNotExists extends SQLException {
    public ProjectNotExists(String reason) {
        super(reason);
    }
}
