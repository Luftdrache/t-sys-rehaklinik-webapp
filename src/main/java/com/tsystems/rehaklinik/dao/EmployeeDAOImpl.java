package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO{
    private static final Log LOG = LogFactory.getLog(EmployeeDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {

    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(int employeeId) {
        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public Employee findBySurname(String employeeName) {
        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByEmployeePosition(String employeesPosition) {
        return null;
    }
}
