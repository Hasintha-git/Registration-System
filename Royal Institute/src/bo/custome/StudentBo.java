package bo.custome;

import bo.SuperBo;
import dto.StudentDto;
import entity.Student;

import java.util.List;

public interface StudentBo extends SuperBo {
    boolean addStudent(StudentDto student) throws Exception;

    boolean deleteStudent(String id) throws Exception;

    boolean updateStudent(StudentDto student) throws Exception;

    public Student findStudent(String id) throws Exception;

    List<Student> findAll() throws Exception;
    String getLastId()throws Exception;
}
