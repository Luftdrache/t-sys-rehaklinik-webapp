package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.fillers.EmployeeFiller;
import com.tsystems.rehaklinik.dao.EmployeeDAO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    EmployeeDAO employeeDAO;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminService adminService = new AdminServiceImpl(employeeDAO, passwordEncoder);

    @Test
    @DisplayName("Test successful getting an employee by id")
    void getEmployeeById_should_return_found_employee() {
        Employee testEmployee = EmployeeFiller.getEmployee();
        given(employeeDAO.findEmployeeById(1)).willReturn(testEmployee);
        EmployeeDTO found = adminService.getEmployeeById(1);
        assertNotNull(found);
    }

    @Test
    @DisplayName("Test does not find an employee by id")
    void getEmployeeById_should_return_null() {
        given(employeeDAO.findEmployeeById(2)).willReturn(null);
        EmployeeDTO found = adminService.getEmployeeById(2);
        assertNull(found);
    }

    @Test
    @DisplayName("Successful employee deleting")
    void deleteEmployeeById_should_return_success() {
        given(employeeDAO.deleteEmployee(1)).willReturn(true);
        assertEquals(adminService.deleteEmployeeById(1), "Employee's data was successfully deleted");
    }

    @Test
    @DisplayName("Failed employee deleting")
    void deleteEmployeeById_should_return_failed_attempt() {
        given(employeeDAO.deleteEmployee(2)).willReturn(false);
        assertEquals(adminService.deleteEmployeeById(2), "Failed attempt to delete employee's data");
    }

    @Test
    void showAllEmployees() {

//        List<Employee> employees = employeeDAO.findAll();
//        employees.add(EmployeeFiller.getEmployee());
//        given(employeeDAO.findAll()).willReturn(employees);
//        employees.add(EmployeeFiller.getEmployee());
//        given(employeeDAO.findAll()).willReturn(employees);


    }


//
//    @Test
//    void findEmployeeBySurname() {
//        List<Employee> employees = employeeDAO.findAll();
//        employees.add(EmployeeFiller.getEmployee());
//        given(employeeDAO.findEmployeeBySurname("Young")).willReturn(new List<Employee>);
//        assertNotNu
//    }


//    @Test
//    void addNewEmployee_shoud_create_new_employee() {
//        Employee testEmployee = EmployeeFiller.getEmployee();
//        given(employeeDAO.createEmployee(testEmployee)).willReturn(testEmployee);
//
//    }



//
//    @Test
//    void editEmployee() {
//    }
}