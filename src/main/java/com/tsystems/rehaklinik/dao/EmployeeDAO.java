package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee createEmployee(Employee employee);

    void deleteEmployee(int employeeId);

    void updateEmployee(Employee employee);

    Employee findEmployeeById(int employeeId);

    Employee findEmployeeBySurname(String employeeName);

    List<Employee> findAll();

    List<Employee> findByEmployeePosition(String employeesPosition);

}
