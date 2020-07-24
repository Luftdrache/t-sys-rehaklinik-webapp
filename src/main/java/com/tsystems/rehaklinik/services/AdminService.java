package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.entities.Employee;

import java.util.List;

public interface AdminService {

    Employee getEmployee(int employeeId);

    List<EmployeeDTO> showAllEmployees();

    Employee addNewEmployee(Employee employee);

    String deleteEmployeeById(int id);

    EmployeeDTO findEmployeeById(int id);

    List<EmployeeDTO> findEmployeeBySurname(String surname);

    String editEmployee(Employee editedEmployee);

}
