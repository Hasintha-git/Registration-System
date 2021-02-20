package bo.custome;

import bo.SuperBo;
import dto.CourseDto;
import dto.StudentDto;
import entity.Course;
import entity.Student;

import java.util.List;

public interface CourseBo extends SuperBo {
    boolean addCourse(CourseDto course) throws Exception;

    boolean deleteCourse(String id) throws Exception;

    boolean updateCourse(CourseDto course) throws Exception;

    public Course findCourse(String id) throws Exception;

    List<Course> findAll() throws Exception;
    String getLastId()throws Exception;
}
