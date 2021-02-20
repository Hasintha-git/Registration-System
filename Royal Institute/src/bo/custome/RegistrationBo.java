package bo.custome;

import bo.SuperBo;
import dto.RegistrationDto;
import dto.StudentDto;
import entity.Registration;
import entity.Student;

import java.util.List;

public interface RegistrationBo extends SuperBo {
    boolean addRegistration(RegistrationDto registrationDto) throws Exception;

    boolean deleteRegistration(int id) throws Exception;

    boolean updateRegistration(RegistrationDto registrationDto) throws Exception;

    public Registration findRegistration(int id) throws Exception;

    List<Registration> findAll() throws Exception;
    String getLastId()throws Exception;
}
