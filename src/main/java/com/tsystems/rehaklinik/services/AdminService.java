package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.entities.Employee;

import java.util.List;

public interface AdminService {

    EmployeeDTO getEmployeeById(int employeeId);

    List<EmployeeShortViewDTO> showAllEmployees();

    EmployeeDTO addNewEmployee(EmployeeDTO employee);

    String deleteEmployeeById(int id);

    List<EmployeeShortViewDTO> findEmployeeBySurname(String surname);

    EmployeeDTO editEmployee(EmployeeDTO editedEmployee);

}
