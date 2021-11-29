package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.createSQLQuery("create table IF NOT EXISTS user" +
                    "(id BIGINT primary key auto_increment, " +
                    "name varchar(50), " +
                    "lastname varchar(50), " +
                    "age tinyint);").executeUpdate();
            t.commit();
        } catch (HibernateException e) {
            t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.createSQLQuery("drop table IF EXISTS user;").executeUpdate();
            t.commit();
        } catch (HibernateException e) {
            t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User users = new User(name, lastName, age);
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(users);
            t.commit();
        } catch (HibernateException e) {

            t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        User users = (User) session.get(User.class, id);
        Transaction t = session.beginTransaction();
        try {
            session.delete(users);
            t.commit();
        } catch (HibernateException e) {
            t.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        List<User> users = new ArrayList<>();
        try {
            users = session.createQuery("FROM User ").list();
            t.commit();
        } catch (HibernateException e) {
            t.rollback();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.createSQLQuery("TRUNCATE TABLE user;").executeUpdate();
            t.commit();
        } catch (HibernateException e) {
            t.rollback();
        } finally {
            session.close();
        }
    }
}
