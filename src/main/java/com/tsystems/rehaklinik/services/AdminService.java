package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.entities.Employee;

import java.util.List;

public interface AdminService{
    List<EmployeeDTO> showAllEmployees();

    Employee addNewEmployee(Employee employee);
}
