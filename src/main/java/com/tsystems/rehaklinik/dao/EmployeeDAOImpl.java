package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employees;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO{
    private static Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertEmployee(Employees employee) {
        logger.info("--- Add new employee ---");
        entityManager.persist(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {

    }

    @Override
    public void updateEmployee(Employees employee) {

    }

    @Override
    @Transactional(readOnly = true)
    public Employees findById(int employeeId) {
        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public Employees findBySurname(String employeeName) {
        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Employees> findAll() {
        logger.info("--- Find all employees ---");
        return entityManager.createQuery("SELECT e FROM Employees e", Employees.class).getResultList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<Employees> findByEmployeePosition(String employeesPosition) {
        return null;
    }
}
