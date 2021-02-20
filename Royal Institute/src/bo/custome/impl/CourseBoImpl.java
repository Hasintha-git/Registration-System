package bo.custome.impl;

import bo.custome.CourseBo;
import dao.DaoFactory;
import dao.DaoType;
import dao.SuperDao;
import dao.custome.CourseDao;
import dto.CourseDto;
import entity.Course;

import java.util.List;

public class CourseBoImpl implements CourseBo {
    CourseDao courseDao;

    public CourseBoImpl(){
        this.courseDao = DaoFactory.getInstance().getDao(DaoType.COURSE);
    }
    @Override
    public boolean addCourse(CourseDto course) throws Exception {
        return courseDao.add(new Course(course.getCode(),course.getCourseName(),course.getFee(),course.getDuration()));
    }

    @Override
    public boolean deleteCourse(String id) throws Exception {
        return courseDao.delete(id);
    }

    @Override
    public boolean updateCourse(CourseDto course) throws Exception {
        return courseDao.update(new Course(course.getCode(),course.getCourseName(),course.getFee(),course.getDuration()));
    }

    @Override
    public Course findCourse(String id) throws Exception {
        return courseDao.find(id);
    }

    @Override
    public List<Course> findAll() throws Exception {
        return courseDao.loadAll();
    }

    @Override
    public String getLastId() throws Exception {
        return courseDao.getLastId();
    }
}
