package com.epam.training.entity.delete;

import com.epam.training.entity.User;
import com.epam.training.entity.UserRole;

public class Administrator extends User {

    public Administrator(long id, String login, String password, String firstName, String lastName, UserRole role) {
        super(id, login, password, firstName, lastName, role);
    }
}
