package bo.custome.impl;

import bo.custome.RegistrationBo;
import dao.DaoFactory;
import dao.DaoType;
import dao.custome.RegistrationDao;
import dto.RegistrationDto;
import dto.StudentDto;
import entity.Registration;
import entity.Student;

import java.util.List;

public class RegistrationBoImpl implements RegistrationBo {
    RegistrationDao registrationDao;
    public RegistrationBoImpl(){
        registrationDao= DaoFactory.getInstance().getDao(DaoType.REGISTRATION);

    }


    @Override
    public boolean addRegistration(RegistrationDto registrationDto) throws Exception {
        return registrationDao.add(new Registration(registrationDto.getRegNo(),registrationDto.getRegDate(),registrationDto.getRegFee(),registrationDto.getStudent(),registrationDto.getCourse()));
    }

    @Override
    public boolean deleteRegistration(int id) throws Exception {
        return false;
    }

    @Override
    public boolean updateRegistration(RegistrationDto registrationDto) throws Exception {
        return false;
    }

    @Override
    public Registration findRegistration(int id) throws Exception {
        return null;
    }

    @Override
    public List<Registration> findAll() throws Exception {
        return registrationDao.loadAll();
    }

    @Override
    public String getLastId() throws Exception {
        return registrationDao.getLastId();
    }
}
