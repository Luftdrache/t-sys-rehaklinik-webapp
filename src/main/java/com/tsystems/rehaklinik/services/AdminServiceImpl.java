package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.converters.DTOconverters.EmployeeDTOConverter;
import com.tsystems.rehaklinik.converters.DTOconverters.EmployeeMapper;
import com.tsystems.rehaklinik.dao.EmployeeDAO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
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


/**
 * Admin service implementation.
 *
 * @author Julia Dalskaya
 */
@Service("AdminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    private static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    private EmployeeDAO employeeDAO;
    private PasswordEncoder passwordEncoder;


    @Override
    public EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO) {
        logger.info("MedHelper_LOGS: AdminServiceImpl --> in addNewEmployee() method");
        employeeDTO.getAuthenticationDataEmployee()
                .setPassword(passwordEncoder.encode(employeeDTO.getAuthenticationDataEmployee().getPassword()));
        Employee newEmployeeToSave = EmployeeMapper.INSTANCE.fromDTO(employeeDTO);
        Employee newEmployee = employeeDAO.createEmployee(newEmployeeToSave);
        return EmployeeMapper.INSTANCE.toDTO(newEmployee);
    }

    @Override
    public EmployeeDTO editEmployee(EmployeeDTO employeeDTO) {
        logger.info("MedHelper_LOGS: AdminServiceImpl --> in editEmployee() method");
        Employee employeeToEdit = EmployeeMapper.INSTANCE.fromDTO(employeeDTO);
        Employee editedEmployee = employeeDAO.updateEmployee(employeeToEdit);
        return EmployeeMapper.INSTANCE.toDTO(editedEmployee);
    }

    @Override
    public String deleteEmployeeById(int id) {
        logger.info("MedHelper_LOGS: AdminServiceImpl --> in deleteEmployeeById() method");
        boolean actionResult = employeeDAO.deleteEmployee(id);
        return !actionResult
                ? "Failed attempt to delete employee's data"
                : "Employee's data was successfully deleted";
    }


    @Override
    public List<EmployeeShortViewDTO> showAllEmployees() {
        logger.info("MedHelper_LOGS: AdminServiceImpl --> in showAllEmployees() method");
        List<Employee> allEmployeesFound = employeeDAO.findAll();
        List<EmployeeShortViewDTO> employeesDTO = new ArrayList<>();
        if (!allEmployeesFound.isEmpty()) {
            for (Employee empl : allEmployeesFound) {
                employeesDTO.add(new EmployeeShortViewDTO(empl));
            }
        }
        logger.info("MedHelper_LOGS: AdminServiceImpl: findEmployeeBySurname() --> operation returned empty list");
        return employeesDTO;
    }


    @Override
    public List<EmployeeShortViewDTO> findEmployeeBySurname(String surname) {
        logger.info("MedHelper_LOGS: AdminServiceImpl --> in findEmployeeBySurname() method");
        List<Employee> allEmployeesFound = employeeDAO.findEmployeeBySurname(surname);
        List<EmployeeShortViewDTO> employeesDTO = new ArrayList<>();
        if (!allEmployeesFound.isEmpty()) {
            for (Employee empl : allEmployeesFound) {
                employeesDTO.add(new EmployeeShortViewDTO(empl));
            }
        } else {
            logger.info("MedHelper_LOGS: AdminServiceImpl: findEmployeeBySurname() --> there is no employee with such surname in database");
        }
        return employeesDTO;
    }


    @Override
    public EmployeeDTO getEmployeeById(int employeeId) {
        logger.info("MedHelper_LOGS: AdminServiceImpl --> in getEmployeeById() method");
        Employee foundEmployee = employeeDAO.findEmployeeById(employeeId);
        if (foundEmployee != null) {
            logger.info("MedHelper_LOGS: AdminServiceImpl: getEmployeeById() --> employee was not found");
            return EmployeeDTOConverter.toDTO(foundEmployee);
        }
        return null;
    }


    @Autowired
    public AdminServiceImpl(EmployeeDAO employeeDAO, PasswordEncoder passwordEncoder) {
        this.employeeDAO = employeeDAO;
        this.passwordEncoder = passwordEncoder;
    }
}
