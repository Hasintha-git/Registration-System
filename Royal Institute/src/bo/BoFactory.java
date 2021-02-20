package bo;

import bo.custome.impl.CourseBoImpl;
import bo.custome.impl.LogInBoImpl;
import bo.custome.impl.RegistrationBoImpl;
import bo.custome.impl.StudentBoImpl;
import dao.custome.impl.LogInDaoImpl;
import entity.SuperEntity;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }
    public static BoFactory getInstance(){
        return (boFactory==null)?boFactory=new BoFactory():boFactory;
    }

    public <T extends SuperBo>T getBo(BoType boType){
        switch (boType){
            case STUDENT:
                return (T) new StudentBoImpl();
            case REGISTRATION:
                return (T) new RegistrationBoImpl();
            case COURSE:
                return (T) new CourseBoImpl();
            case LOGIN:
                return (T) new LogInBoImpl();
            default:
                return null;
        }
    }
}
