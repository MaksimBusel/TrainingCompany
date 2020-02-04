package main.java.com.epam.training.mapper;

import main.java.com.epam.training.dto.CourseDto;
import main.java.com.epam.training.entity.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDtoRowMapper implements RowMapper<CourseDto>  {

    @Override
    public CourseDto map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Course.ID);
        String name = resultSet.getString(Course.COURSE_NAME);
        String description = resultSet.getString(Course.DESCRIPTION);
        String dateFrom = resultSet.getString(Course.DATE_FROM);
        String dateTo = resultSet.getString(Course.DATE_TO);
        String teacherFirstName = resultSet.getString(CourseDto.FIRST_NAME);
        String teacherLastName = resultSet.getString(CourseDto.LAST_NAME);
        long teacherId = resultSet.getLong(CourseDto.TEACHER_ID);
        CourseDto course = new CourseDto(id, name, description, dateFrom, dateTo, teacherFirstName, teacherLastName, teacherId);

        return course;
    }
}
