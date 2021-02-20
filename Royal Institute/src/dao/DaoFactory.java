package dao;

import dao.custome.impl.CourseDaoImpl;
import dao.custome.impl.LogInDaoImpl;
import dao.custome.impl.RegistrationDaoImpl;
import dao.custome.impl.StudentDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getInstance(){
        return (daoFactory==null)? daoFactory=new DaoFactory() : daoFactory;
    }
    public <T extends SuperDao> T getDao(DaoType daoType) {
        switch (daoType){
            case STUDENT:
                return (T) new StudentDaoImpl();
            case COURSE:
                return (T) new CourseDaoImpl();
            case REGISTRATION:
                return (T) new RegistrationDaoImpl();
            case LOGIN:
                return (T) new LogInDaoImpl();
            default:
                return null;
        }
    }
}
