package com.tsystems.rehaklinik.dao.implementations;

import com.tsystems.rehaklinik.dao.interfaces.EmployeeDAO;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.types.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {
    private static Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("MedHelper_LOGS: EmployeeDAO: Add new employee");
        entityManager.persist(employee);
        logger.info("MedHelper_LOGS: EmployeeDAO: Add new employee");
        return employee;
    }


    @Override
    public Employee updateEmployee(Employee employee) {
        logger.info("MedHelper_LOGS: EmployeeDAO: Data about an employee with the id = {} is updated", employee.getEmployeeId());
        if (employee.getEmployeeId() != 0 && entityManager.find(Employee.class, employee.getEmployeeId()) != null) {
            try {
                Employee editedEmployee = entityManager.merge(employee);
                logger.info("MedHelper_LOGS: EmployeeDAO: Successful attempt to edit an employee with an id = {}", employee.getEmployeeId());
                return editedEmployee;
            } catch (PersistenceException exception) {
                logger.info(exception.getMessage());
            }
        }
        logger.info("MedHelper_LOGS: EmployeeDAO: Failed attempt to edit an employee with an id = {} ", employee.getEmployeeId());
        return null;
    }


    @Override
    public boolean deleteEmployee(int employeeId) {
        logger.info("MedHelper_LOGS: EmployeeDAO: Delete an employee by id");
        Employee employee = entityManager.find(Employee.class, employeeId);
        if (employee != null) {
            entityManager.remove(employee);
            logger.info("MedHelper_LOGS: EmployeeDAO: Employee with id = {} deleted", employeeId);
            return true;
        }
        logger.info("MedHelper_LOGS: EmployeeDAO: Employee with id = {} does not exist", employeeId);
        return false;
    }


    @Override
    public List<Employee> findAll() {
        logger.info("MedHelper_LOGS: EmployeeDAO: Find all employees");
        return entityManager.createQuery("SELECT e FROM Employee e ORDER BY e.surname", Employee.class).getResultList();
    }


    @Override
    public Employee findEmployeeById(int employeeId) {
        logger.info("MedHelper_LOGS: EmployeeDAO: Find an employee by id");
        Employee employeeFoundById = entityManager.find(Employee.class, employeeId);
        if (employeeFoundById != null) {
            logger.info("MedHelper_LOGS: EmployeeDAO: Employee with id = {} found successfully", employeeId);
            return employeeFoundById;
        }
        logger.info("MedHelper_LOGS: EmployeeDAO: Employee with id = {} not found", employeeId);
        return null;
    }


    @Override
    public List<Employee> findEmployeeBySurname(String employeeSurname) {
        logger.info("MedHelper_LOGS: EmployeeDAO: Find an employee by surname");
        return entityManager.createQuery(
                "select e from Employee e where lower(e.surname) LIKE lower(:employeeSurname)", Employee.class)
                .setParameter("employeeSurname", "%" + employeeSurname + "%").getResultList();
    }


    @Override
    public List<Employee> findAllDoctors() {
        logger.info("MedHelper_LOGS: EmployeeDAO: Find all doctors");
        return entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.role = :doctor ORDER BY e.position", Employee.class)
                .setParameter("doctor", Roles.DOCTOR).getResultList();
    }

}
