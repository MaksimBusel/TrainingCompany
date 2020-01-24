package com.epam.training.entity.delete;

import com.epam.training.entity.Course;
import com.epam.training.entity.User;
import com.epam.training.entity.UserRole;

import java.util.List;

public class Teacher extends User {
    List<Course> courses;

    public Teacher(long id, String login, String password, String firstName, String lastName, UserRole role) {
        super(id, login, password, firstName, lastName, role);
    }

    @Override
    public String toString() {
        return super.toString()+ " Teacher{" +
                '}';
    }
}
