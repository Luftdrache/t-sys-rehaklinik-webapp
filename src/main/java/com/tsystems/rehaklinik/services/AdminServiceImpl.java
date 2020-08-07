package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dao.EmployeeDAO;
import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("AdminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    private static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    private EmployeeDAO employeeDAO;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Employee getEmployee(int employeeId) {
        return employeeDAO.findEmployeeById(employeeId);
    }

    @Override
    public Employee addNewEmployee(Employee employee) {
        employee.getAuthenticationDataEmployee()
                .setPassword(passwordEncoder.encode(employee.getAuthenticationDataEmployee().getPassword()));
        return employeeDAO.createEmployee(employee);
    }


    @Override
    public Employee editEmployee(Employee editedEmployee) {
        return employeeDAO.updateEmployee(editedEmployee);
    }


    @Override
    public String deleteEmployeeById(int id) {
        return employeeDAO.deleteEmployee(id);
    }


    @Override
    public EmployeeShortViewDTO findEmployeeByIdDTO(int id) {
        Employee employee = employeeDAO.findEmployeeById(id);
        if (employee != null) {
            EmployeeShortViewDTO employeeShortViewDTO = new EmployeeShortViewDTO(employee);
            logger.info("MedHelper_LOGS: AdminService: EmployeeDTO found by id: " + employeeShortViewDTO.toString());
            return employeeShortViewDTO;
        }
        return null;
    }


    @Override
    public List<EmployeeShortViewDTO> showAllEmployees() {
        List<Employee> allEmployeesFound = employeeDAO.findAll();
        List<EmployeeShortViewDTO> employeesDTO = new ArrayList<>();
//convertToDTO()!
        if (!allEmployeesFound.isEmpty()) {
            for (Employee empl : allEmployeesFound) {
                employeesDTO.add(new EmployeeShortViewDTO(empl));
            }
            return employeesDTO;
        }
        return null;
    }


    @Override
    public List<EmployeeShortViewDTO> findEmployeeBySurname(String surname) {
        List<Employee> allEmployeesFound = employeeDAO.findEmployeeBySurname(surname);
        List<EmployeeShortViewDTO> employeesDTO = new ArrayList<>();
//convertToDTO()!
        if (!allEmployeesFound.isEmpty()) {
            for (Employee empl : allEmployeesFound) {
                employeesDTO.add(new EmployeeShortViewDTO(empl));
            }
            return employeesDTO;
        }
        return null;
    }


    public AdminServiceImpl(EmployeeDAO employeeDAO, PasswordEncoder passwordEncoder) {
        this.employeeDAO = employeeDAO;
        this.passwordEncoder = passwordEncoder;
    }
}
