package com.epam.training.entity.delete;

import com.epam.training.entity.Course;
import com.epam.training.entity.User;
import com.epam.training.entity.UserRole;

import java.util.List;

public class Student extends User {
    private List<Course> courses;

    public Student(long id, String login, String password, String firstName, String lastName, UserRole role) {
        super(id, login, password, firstName, lastName, role);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return super.toString()+ " Student{" +
                "courses=" + courses +
                '}';
    }
}
