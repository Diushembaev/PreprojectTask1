package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction tx;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users(" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "name TEXT NOT NULL," +
                    "lastname TEXT NOT NULL," +
                    "age INTEGER," +
                    "PRIMARY KEY (`id`)," +
                    "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction tx;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sql);
            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        /*Transaction tx;
        Session session = Util.getSessionFactory().openSession();
        try {
            User user = new User(name,lastName,age);
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }*/

        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            Query addQuery = session.createSQLQuery("INSERT INTO users ( name, lastname, age) VALUES (?1, ?2, ?3);");
            addQuery.setParameter(1,name);
            addQuery.setParameter(2,lastName);
            addQuery.setParameter(3,age);
            addQuery.executeUpdate();
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction tx;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            String sql = "DELETE FROM users WHERE id = id" ;
            session.createSQLQuery(sql);
            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = null;
        Transaction tx;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            String sql = "select * from users" ;
            users = session.createSQLQuery(sql).list();
            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx;
        Session session = Util.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            String sql = "delete from users" ;
            session.createSQLQuery(sql).executeUpdate();

            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
