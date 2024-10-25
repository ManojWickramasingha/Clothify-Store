package repository.custom.impl;


import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.custom.EmployeeDao;
import util.CrudUtil;
import util.HibernateUtil;

import java.sql.ResultSet;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private final CrudUtil util = CrudUtil.getInstance();
    @Override
    public Boolean save(EmployeeEntity employee) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(employee);
        session.getTransaction().commit();
        session.close();

        return true;



    }

    @Override
    public Boolean update(EmployeeEntity employee) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {

            transaction = session.beginTransaction();

            session.update(employee);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback in case of error
            e.printStackTrace();
            return false;
        }
        // Ensure the session is closed


    }

    @Override
    public ResultSet genarateIdms() {
        String sql = "SELECT id from employee order by id DESC LIMIT 1";
        return util.execute(sql);
    }


    @Override
    public ObservableList<EmployeeEntity> getAll() {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            ObservableList<EmployeeEntity> employees = FXCollections.observableArrayList();
            transaction = session.beginTransaction();

            // Use HQL to fetch all employee records
            String hql = "FROM EmployeeEntity";
            Query<EmployeeEntity> query = session.createQuery(hql, EmployeeEntity.class);

            // Get list of employees and add them to the ObservableList
            List<EmployeeEntity> resultList = query.getResultList();
            employees.addAll(resultList);

            transaction.commit();

            // Return the list if it's not empty
            if (!employees.isEmpty()) {
                return employees;
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return null;


    }

    @Override
    public Boolean deleteById(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // HQL query to delete the employee by ID
            String hql = "DELETE FROM EmployeeEntity e WHERE e.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);

            int result = query.executeUpdate();

            transaction.commit();


            return result > 0;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }







    }

    @Override
    public String genarateId() {
        return null;
    }
}
