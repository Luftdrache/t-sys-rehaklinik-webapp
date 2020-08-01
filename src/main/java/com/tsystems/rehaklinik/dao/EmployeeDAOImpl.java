package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.types.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {
    private static Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Finds all doctors from the database
     *
     * @return List of all doctors from the database
     */
    @Override
    public List<Employee> findAllDoctors() {
        logger.info("MedHelper_LOGS: EmployeeDAO: Find all doctors");
        return entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.role = :doctor", Employee.class
        ).setParameter("doctor", Roles.DOCTOR).getResultList();
    }


    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("MedHelper_LOGS: EmployeeDAO: Add new employee");
        entityManager.persist(employee);
        logger.info("MedHelper_LOGS: EmployeeDAO: Add new employee");
        return employee;
    }


    @Override
    public Employee updateEmployee(Employee employee) {
        logger.info("MedHelper_LOGS: EmployeeDAO: Data about an employee with the id = " + employee.getEmployeeId() + " is updated");
        if (employee.getEmployeeId() != 0 && entityManager.find(Employee.class, employee.getEmployeeId()) != null) {
            try {
                Employee editedEmployee = entityManager.merge(employee);
                logger.info("MedHelper_LOGS: EmployeeDAO: Successful attempt to edit an employee with an id = " + employee.getEmployeeId());
                return editedEmployee;
            } catch (PersistenceException exception) {
                logger.info(exception.getMessage());
            }
        }
        logger.info("MedHelper_LOGS: EmployeeDAO: Failed attempt to edit an employee with an id = " + employee.getEmployeeId());
        return null;
    }


    @Override
    public String deleteEmployee(int employeeId) {
        logger.info("MedHelper_LOGS: EmployeeDAO: Delete an employee by id");
        Employee employee = entityManager.find(Employee.class, employeeId);
        if (employee != null) {
            entityManager.remove(employee);
            logger.info("MedHelper_LOGS: EmployeeDAO: Employee with id = " + employeeId + " deleted");
            return "Employee deleted successfully";
        }
        logger.info("MedHelper_LOGS: EmployeeDAO: Employee with id = " + employeeId + " does not exist");
        return "The specified employee with id= " + employeeId + " does not exist";
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
            logger.info("MedHelper_LOGS: EmployeeDAO: Employee with id = " + employeeId + " found successfully");
            return employeeFoundById;
        }
        logger.info("MedHelper_LOGS: EmployeeDAO: Employee with id = " + employeeId + " not found");
        return null;
    }


    @Override
    public List<Employee> findEmployeeBySurname(String employeeSurname) {
        logger.info("MedHelper_LOGS: EmployeeDAO: Find an employee by surname");
        TypedQuery<Employee> query = entityManager.createQuery(
                "select e from Employee e where e.surname LIKE :employeeSurname", Employee.class);
        query.setParameter("employeeSurname", "%" + employeeSurname + "%");
        return query.getResultList();
    }

    @Override
    public List<Employee> findByEmployeePosition(String employeesPosition) {
        throw new UnsupportedOperationException("Not implemented yet");
    }


}
