package main.java.com.epam.training.mapper;

import main.java.com.epam.training.entity.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Course.ID);
        String name = resultSet.getString(Course.COURSE_NAME);
        String description = resultSet.getString(Course.DESCRIPTION);
        String dateFrom = resultSet.getString(Course.DATE_FROM);
        String dateTo = resultSet.getString(Course.DATE_TO);
        String teacherId = resultSet.getString(Course.TEACHER_ID);
        Course course = new Course(id, name, description, dateFrom, dateTo, teacherId);

        return course;
    }
}
