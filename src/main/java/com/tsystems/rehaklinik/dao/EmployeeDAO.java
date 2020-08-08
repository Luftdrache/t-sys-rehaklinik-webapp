package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;

import java.util.List;



public interface EmployeeDAO {
    /**
     * Saves new employee to database
     * @param employee employee from an add form
     * @return new saved employee
     */
    Employee createEmployee(Employee employee);

    /**
     * Deletes employee from database
     * @param employeeId employee id to delete
     * @return a boolean about delete action result
     */
    boolean deleteEmployee(int employeeId);

    /**
     * Updating an existing employee in the database
     * @param employee employee to update
     * @return an updated employee
     */
    Employee updateEmployee(Employee employee);

    /**
     * Finds employee by their id
     * @param employeeId employee id
     * @return an employee found by id
     */
    Employee findEmployeeById(int employeeId);

    /**
     * Finds employee by their surname
     * @param employeeSurname employee surname
     * @return an employee found by surname
     */
    List<Employee> findEmployeeBySurname(String employeeSurname);

    /**
     * Finds all employees from the database
     * @return list of all employees from database
     */
    List<Employee> findAll();

    /**
     * Finds all doctors from the database
     * @return List of all doctors from the database
     */
    List<Employee> findAllDoctors();



    List<Employee> findByEmployeePosition(String employeesPosition);

}
