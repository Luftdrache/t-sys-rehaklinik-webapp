package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.converters.DTOconverters.EmployeeDTOConverter;
import com.tsystems.rehaklinik.converters.DTOconverters.EmployeeMapper;
import com.tsystems.rehaklinik.converters.DTOconverters.PrescriptionMapper;
import com.tsystems.rehaklinik.dao.EmployeeDAO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.dto.PrescriptionDTO;
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
    public EmployeeDTO editEmployee(EmployeeDTO employeeDTO) {
        Employee employeeToEdit =  EmployeeMapper.INSTANCE.fromDTO(employeeDTO);
        Employee editedEmployee = employeeDAO.updateEmployee(employeeToEdit);
        EmployeeDTO editedEmployeeDTO = EmployeeMapper.INSTANCE.toDTO(editedEmployee);
        return editedEmployeeDTO;
    }



    //**************************DONE ********************

    @Override
    public EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.getAuthenticationDataEmployee()
                .setPassword(passwordEncoder.encode(employeeDTO.getAuthenticationDataEmployee().getPassword()));
        Employee newEmployeeToSave = EmployeeMapper.INSTANCE.fromDTO(employeeDTO);
        Employee newEmployee = employeeDAO.createEmployee(newEmployeeToSave);
        EmployeeDTO savedEmployee = EmployeeMapper.INSTANCE.toDTO(newEmployeeToSave);
        return savedEmployee;
    }

    @Override
    public String deleteEmployeeById(int id) {
        boolean actionResult = employeeDAO.deleteEmployee(id);
        return !actionResult
                ? "Failed attempt to delete employee's data"
                : "Employee's data was successfully deleted";
    }


    @Override
    public List<EmployeeShortViewDTO> showAllEmployees() {
        List<Employee> allEmployeesFound = employeeDAO.findAll();
        List<EmployeeShortViewDTO> employeesDTO = new ArrayList<>();
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
        if (!allEmployeesFound.isEmpty()) {
            for (Employee empl : allEmployeesFound) {
                employeesDTO.add(new EmployeeShortViewDTO(empl));
            }
            return employeesDTO;
        }
        return null;
    }


    @Override
    public EmployeeDTO getEmployeeById(int employeeId) {
        Employee foundEmployee = employeeDAO.findEmployeeById(employeeId);
        if(foundEmployee != null) {
            return EmployeeDTOConverter.toDTO(foundEmployee);
        }
        return null;
    }


    public AdminServiceImpl(EmployeeDAO employeeDAO, PasswordEncoder passwordEncoder) {
        this.employeeDAO = employeeDAO;
        this.passwordEncoder = passwordEncoder;
    }
}
