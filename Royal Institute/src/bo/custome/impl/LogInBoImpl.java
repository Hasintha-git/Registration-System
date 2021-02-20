package bo.custome.impl;

import bo.custome.LogInBo;
import dao.DaoFactory;
import dao.DaoType;
import dao.custome.LogInDao;
import dao.custome.impl.LogInDaoImpl;
import dto.CourseDto;
import dto.LogInDto;
import entity.LogIn;

public class LogInBoImpl implements LogInBo {
    LogInDao logInDao;
    public LogInBoImpl(){
        logInDao= DaoFactory.getInstance().getDao(DaoType.LOGIN);
    }


    @Override
    public boolean addUser(LogInDto log) throws Exception {
        return logInDao.add(new LogIn(log.getMail(),log.getPassword()));
    }

    @Override
    public boolean updateUser(LogInDto log) throws Exception {
        return logInDao.update(new LogIn(log.getMail(),log.getPassword()));
    }

    @Override
    public LogIn findUser(String id) throws Exception {
        return logInDao.find(id);
    }
}
