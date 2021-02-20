package dao.custome;

import dao.SuperDao;
import entity.Registration;

public interface RegistrationDao extends SuperDao<Registration, Integer> {
    String getLastId()throws Exception;
}
