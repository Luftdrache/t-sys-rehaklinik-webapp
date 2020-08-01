package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.entities.Employee;

import java.util.List;

public interface AdminService {

    Employee getEmployee(int employeeId);

    List<EmployeeShortViewDTO> showAllEmployees();

    Employee addNewEmployee(Employee employee);

    String deleteEmployeeById(int id);

    EmployeeShortViewDTO findEmployeeByIdDTO(int id);

    List<EmployeeShortViewDTO> findEmployeeBySurname(String surname);

    Employee editEmployee(Employee editedEmployee);

}
