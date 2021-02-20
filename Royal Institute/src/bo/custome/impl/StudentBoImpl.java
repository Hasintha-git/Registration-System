package bo.custome.impl;

import bo.custome.StudentBo;
import dao.DaoFactory;
import dao.DaoType;
import dao.custome.StudentDao;
import dto.StudentDto;
import entity.Student;

import java.util.List;

public class StudentBoImpl implements StudentBo {
    StudentDao studentDao;
    public StudentBoImpl(){
         studentDao= DaoFactory.getInstance().getDao(DaoType.STUDENT);
    }
    @Override
    public boolean addStudent(StudentDto student) throws Exception {
        return studentDao.add(new Student(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getDate(),student.getGender()));
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDao.delete(id);
    }

    @Override
    public boolean updateStudent(StudentDto student) throws Exception {
        return studentDao.update(new Student(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getDate(),student.getGender()));
    }

    @Override
    public Student findStudent(String id) throws Exception {
        return studentDao.find(id);
    }

    @Override
    public List<Student> findAll() throws Exception {
        return studentDao.loadAll();
    }

    @Override
    public String getLastId() throws Exception {
        return studentDao.getLastId();
    }
}
