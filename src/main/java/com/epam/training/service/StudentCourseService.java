package main.java.com.epam.training.service;

import main.java.com.epam.training.dao.DaoHelper;
import main.java.com.epam.training.dao.DaoHelperFactory;
import main.java.com.epam.training.dao.api.StudentCourseDao;
import main.java.com.epam.training.dto.CourseDto;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

public class StudentCourseService {
    private DaoHelperFactory daoHelperFactory;
    private static final String COURSE_FINISHED = "message.enrollfailed";
    private static final String ENROLL_SUCCESS = "message.enrollsuccess";
    private static final String REPEATED_REQUEST = "message.enrollrepeat";


    public StudentCourseService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<CourseDto> showMyCourses(long id) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentCourseDao dao = helper.createStudentCourseDao();
            return dao.findCoursesByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public String enroll(long userId, long courseId, LocalDate dateTo) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            if (LocalDate.now().isBefore(dateTo)) {
                StudentCourseDao dao = helper.createStudentCourseDao();
                boolean resultCheck = dao.findStudentEnrolledInCourse(userId, courseId);
                if (!resultCheck) {
                    dao.enrollStudentInCourse(userId, courseId);
                    return ENROLL_SUCCESS;
                }
                return REPEATED_REQUEST;
            } else {
                return COURSE_FINISHED;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void unenroll(long userId, long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentCourseDao dao = helper.createStudentCourseDao();
            dao.unenrollStudentFromCourse(userId, courseId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}