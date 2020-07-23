package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dao.EmployeeDAO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("AdminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    private EmployeeDAO employeeDAO;

    @Override
    public Employee addNewEmployee(Employee employee) {
        return employeeDAO.createEmployee(employee);
    }

    @Override
    public List<EmployeeDTO> showAllEmployees() {
        List<Employee> allEmployeesFound = employeeDAO.findAll();
        List<EmployeeDTO> employeesDTO = new ArrayList<>();

        if (!allEmployeesFound.isEmpty()) {
            for (Employee empl : allEmployeesFound) {
                employeesDTO.add(new EmployeeDTO(empl));
            }
            return employeesDTO;
        }
        return null;
    }


    public AdminServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}
