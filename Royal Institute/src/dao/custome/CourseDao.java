package dao.custome;

import dao.SuperDao;
import entity.Course;

public interface CourseDao extends SuperDao<Course,String> {
    String getLastId()throws Exception;
}
