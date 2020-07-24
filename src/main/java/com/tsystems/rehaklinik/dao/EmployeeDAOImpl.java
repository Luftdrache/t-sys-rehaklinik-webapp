package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public String updateEmployee(Employee employee) {
        if(employee.getEmployeeId() !=0 && entityManager.find(Employee.class, employee.getEmployeeId())!=null) {
            entityManager.merge(employee);
            logger.info("MedHelper_LOGS: EmployeeDAO: Employee updated with id: " + employee.getEmployeeId());
            return "Employee updated with id: " + employee.getEmployeeId();
        }
        logger.info("MedHelper_LOGS: EmployeeDAO: Failed attempt to edit an employee with an id = " + employee.getEmployeeId());
        return "Failed attempt to edit an employee with an id = " + employee.getEmployeeId();
    }


    @Override
    public String deleteEmployee(int employeeId) {
        logger.info("MedHelper_LOGS: EmployeeDAO: Delete an employee by id");
        String deletingEmployeeMessage;
//        int count = entityManager.createQuery("SELECT count(e) from Employee e WHERE e.employee_id = employeeId");
        Employee employee = entityManager.find(Employee.class, employeeId);
        if (employee != null) {
            entityManager.remove(employee);
            logger.info("MedHelper_LOGS: EmployeeDAO: Employee with id = " + employeeId + " deleted");
            return deletingEmployeeMessage = "Employee deleted successfully";
        }
        logger.info("MedHelper_LOGS: EmployeeDAO: Employee with id = " + employeeId + " does not exist");
        return deletingEmployeeMessage = "The specified employee with id= " + employeeId + " does not exist";
    }


    @Override
    public List<Employee> findAll() {
        logger.info("MedHelper_LOGS: EmployeeDAO: Find all employees");
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
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
        query.setParameter("employeeSurname", "%"+employeeSurname+"%");
        return query.getResultList();
    }

    @Override
    public List<Employee> findByEmployeePosition(String employeesPosition) {
        return null;
    }


}
