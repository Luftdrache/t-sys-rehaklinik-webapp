package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employees;

import java.util.List;

public interface EmployeeDAO {
    void insertEmployee(Employees employee);

    void deleteEmployee(int employeeId);

    void updateEmployee(Employees employee);

    Employees findById(int employeeId);

    Employees findBySurname(String employeeName);

    List<Employees> findAll();

    List<Employees> findByEmployeePosition(String employeesPosition);

}
