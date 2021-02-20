package dao.custome;

import dao.SuperDao;
import entity.Student;

public interface StudentDao extends SuperDao<Student,String> {
    String getLastId()throws Exception;
}
