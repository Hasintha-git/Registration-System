package bo.custome;

import bo.SuperBo;
import dto.CourseDto;
import dto.LogInDto;
import entity.Course;
import entity.LogIn;

public interface LogInBo extends SuperBo {
    boolean addUser(LogInDto log) throws Exception;

    boolean updateUser(LogInDto log) throws Exception;

    public LogIn findUser(String id) throws Exception;
}
