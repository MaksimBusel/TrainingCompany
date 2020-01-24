package com.epam.training.entity.delete;

import com.epam.training.entity.Course;

import java.util.List;

public class TrainingSchool {
    List<Course> courses;

    public TrainingSchool(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "TrainingSchool{" +
                "courses=" + courses +
                '}';
    }
}
