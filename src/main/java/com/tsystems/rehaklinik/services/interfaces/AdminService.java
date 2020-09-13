package com.tsystems.rehaklinik.services.interfaces;


import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;

import java.util.List;

/**
 * Admin service.
 *
 * @author Julia Dalskaya
 */
public interface AdminService {

    /**
     * Finds employee by employee's id
     *
     * @param employeeId employee id
     * @return EmployeeDTO
     */
    EmployeeDTO getEmployeeById(int employeeId);


    /**
     *
     *
     * @return list with EmployeeShortViewDTO objects
     */
    List<EmployeeShortViewDTO> showAllEmployees();

    EmployeeDTO addNewEmployee(EmployeeDTO employee);

    String deleteEmployeeById(int id);

    List<EmployeeShortViewDTO> findEmployeeBySurname(String surname);

    EmployeeDTO editEmployee(EmployeeDTO editedEmployee);

}
