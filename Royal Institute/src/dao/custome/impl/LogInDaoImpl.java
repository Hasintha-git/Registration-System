package dao.custome.impl;

import dao.custome.LogInDao;
import entity.Course;
import entity.LogIn;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.util.List;

public class LogInDaoImpl implements LogInDao {

    @Override
    public boolean add(LogIn entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(LogIn entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public LogIn find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        LogIn logIn = session.get(LogIn.class, s);
        transaction.commit();
        session.close();
        return logIn;
    }

    @Override
    public List<LogIn> loadAll() throws Exception {
        return null;
    }
}
