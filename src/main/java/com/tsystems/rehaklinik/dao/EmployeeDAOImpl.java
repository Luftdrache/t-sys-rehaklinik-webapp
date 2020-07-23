package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;
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
    public Employee createEmployee(Employee employee) {
        logger.info("--- DAO: Add new employee ---");
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        logger.info("--- Find all employees ---");
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }


    @Override
    public void deleteEmployee(int employeeId) {

    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public List<Employee> findByEmployeePosition(String employeesPosition) {
        return null;
    }


    @Override
    public Employee findEmployeeById(int employeeId) {
        return null;
    }


    @Override
    public Employee findEmployeeBySurname(String employeeName) {
        return null;
    }



}
