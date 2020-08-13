package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;

import java.util.List;


/**
 * DAO for {@link Employee} objects
 *
 * @author Julia Dalskaya
 */
public interface EmployeeDAO {

    /**
     * Saves new employee to database
     *
     * @param employee employee from an add form
     * @return new saved employee
     */
    Employee createEmployee(Employee employee);


    /**
     * Deletes employee from database
     *
     * @param employeeId employee id to delete
     * @return a boolean about delete action result
     */
    boolean deleteEmployee(int employeeId);


    /**
     * Updating an existing employee in the database
     *
     * @param employee employee to update
     * @return an updated employee
     */
    Employee updateEmployee(Employee employee);


    /**
     * Searches for employee by their id
     *
     * @param employeeId employee id
     * @return an employee found by id
     */
    Employee findEmployeeById(int employeeId);


    /**
     * Searches employee by their surname
     *
     * @param employeeSurname employee surname
     * @return an employee found by surname
     */
    List<Employee> findEmployeeBySurname(String employeeSurname);


    /**
     * Searches for all employees in database
     *
     * @return list of all employees from database
     */
    List<Employee> findAll();


    /**
     * Searches for all doctors from the database
     *
     * @return List of all doctors from the database
     */
    List<Employee> findAllDoctors();

}
