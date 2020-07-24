package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee createEmployee(Employee employee);

    String deleteEmployee(int employeeId);

    String updateEmployee(Employee employee);

    Employee findEmployeeById(int employeeId);

    List<Employee> findEmployeeBySurname(String employeeSurname);

    List<Employee> findAll();

    List<Employee> findByEmployeePosition(String employeesPosition);

}
