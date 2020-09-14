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
     * Finds all employees
     *
     * @return list with EmployeeShortViewDTO objects
     */
    List<EmployeeShortViewDTO> showAllEmployees();


    /**
     * Business logic for adding a new employee
     *
     * @param employee EmployeeDTO
     * @return EmployeeDTO
     */
    EmployeeDTO addNewEmployee(EmployeeDTO employee);


    /**
     * Business logic for deleting an employee
     *
     * @param id employee id
     * @return message about operation result
     */
    String deleteEmployeeById(int id);


    /**
     * Business logic for finding an employee by surname
     *
     * @param surname employee's surname
     * @return EmployeeShortViewDTO
     */
    List<EmployeeShortViewDTO> findEmployeeBySurname(String surname);


    /**
     * Business logic for editing an employee
     *
     * @param editedEmployee Employee to edit
     * @return EmployeeDTO - edited
     */
    EmployeeDTO editEmployee(EmployeeDTO editedEmployee);

}
