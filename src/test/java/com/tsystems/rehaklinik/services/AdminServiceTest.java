package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.fillers.EmployeeFiller;
import com.tsystems.rehaklinik.dao.interfaces.EmployeeDAO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.services.implementations.AdminServiceImpl;
import com.tsystems.rehaklinik.services.interfaces.AdminService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private EmployeeDAO employeeDAO;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private Logger mockLogger;

    @InjectMocks
    private AdminService adminService = new AdminServiceImpl(employeeDAO, passwordEncoder);


    @Test
    void addNewEmployee_should_return_created_employee() {
        mockLogger.info("Logging");
        when(employeeDAO.createEmployee(any(Employee.class))).thenAnswer((Answer<Employee>) invocation -> {
            Employee employee = (Employee) invocation.getArguments()[0];
            employee.setFirstName("Albert");
            employee.setSurname("Young");
            employee.setPosition("Cardiologist");
            return employee;
        });
        EmployeeDTO employeeDTO = EmployeeFiller.getEmployeeDTO();
        EmployeeDTO createdEmployee = adminService.addNewEmployee(employeeDTO);
        assertNotNull(createdEmployee);
        assertEquals("Albert", createdEmployee.getFirstName());
        assertEquals("Young", createdEmployee.getSurname());
        assertEquals("Cardiologist", createdEmployee.getPosition());
    }


    @Test
    void editEmployee_should_return_edited_employee() {
        mockLogger.info("Logging");
        when(employeeDAO.updateEmployee(any(Employee.class))).thenAnswer((Answer<Employee>) invocation -> {
            Employee employee = (Employee) invocation.getArguments()[0];
            employee.setSurname("Robertson");
            return employee;
        });
        EmployeeDTO employeeDTO = EmployeeFiller.getEmployeeDTO();
        assertEquals("Young", employeeDTO.getSurname());
        EmployeeDTO editedEmployee = adminService.editEmployee(employeeDTO);
        assertNotNull(editedEmployee);
        assertEquals("Robertson", editedEmployee.getSurname());
    }

    @Test
    void deleteEmployeeById_should_return_successful_message() {
        mockLogger.info("Logging");
        given(employeeDAO.deleteEmployee(1)).willReturn(true);
        String deletingResult = adminService.deleteEmployeeById(1);
        assertEquals("Employee's data was successfully deleted", deletingResult);
    }

    @Test
    void deleteEmployeeById_should_return_failed_message() {
        mockLogger.info("Logging");
        given(employeeDAO.deleteEmployee(1)).willReturn(false);
        String deletingResult = adminService.deleteEmployeeById(1);
        assertEquals("Failed attempt to delete employee's data", deletingResult);
    }

    @Test
    void findEmployeeBySurname_should_return_emptyList() {
        mockLogger.info("Logging");
        List<Employee> testEmployeesList = new ArrayList<>();
        given(employeeDAO.findEmployeeBySurname("Young")).willReturn(testEmployeesList);
        List<EmployeeShortViewDTO> foundEmployees = adminService.findEmployeeBySurname("Young");
        assertTrue(foundEmployees.isEmpty());
    }

    @Test
    void findEmployeeBySurname_should_return_found_employee() {
        mockLogger.info("Logging");
        List<Employee> testEmployeesList = new ArrayList<>();
        testEmployeesList.add(EmployeeFiller.getEmployee());
        given(employeeDAO.findEmployeeBySurname("Young")).willReturn(testEmployeesList);
        List<EmployeeShortViewDTO> foundEmployees = adminService.findEmployeeBySurname("Young");
        assertEquals(testEmployeesList.size(), foundEmployees.size());
        assertEquals(testEmployeesList.get(0).getEmployeeId(), foundEmployees.get(0).getEmployeeId());
    }


    @Test
    void showAllEmployees_should_return_correct_employees_count() {
        mockLogger.info("Logging");
        List<Employee> testEmployeesList = new ArrayList<>();
        testEmployeesList.add(EmployeeFiller.getEmployee());
        given(employeeDAO.findAll()).willReturn(testEmployeesList);
        List<EmployeeShortViewDTO> returnedList = adminService.showAllEmployees();
        assertEquals(testEmployeesList.size(), returnedList.size());
    }

    @Test
    void showAllEmployees_should_return_empty_list() {
        mockLogger.info("Logging");
        List<Employee> testEmployeesList = new ArrayList<>();
        given(employeeDAO.findAll()).willReturn(testEmployeesList);
        List<EmployeeShortViewDTO> returnedList = adminService.showAllEmployees();
        assertTrue(returnedList.isEmpty());
    }

    @Test
    @DisplayName("Test successful getting an employee by id")
    void getEmployeeById_should_return_found_employee() {
        mockLogger.info("Logging");
        Employee testEmployee = EmployeeFiller.getEmployee();
        given(employeeDAO.findEmployeeById(1)).willReturn(testEmployee);
        EmployeeDTO found = adminService.getEmployeeById(1);
        assertNotNull(found);
    }

    @Test
    @DisplayName("Test does not find an employee by id")
    void getEmployeeById_should_return_null() {
        mockLogger.info("Logging");
        given(employeeDAO.findEmployeeById(2)).willReturn(null);
        EmployeeDTO found = adminService.getEmployeeById(2);
        assertNull(found);
    }

    @Test
    @DisplayName("Successful employee deleting")
    void deleteEmployeeById_should_return_success() {
        mockLogger.info("Logging");
        given(employeeDAO.deleteEmployee(1)).willReturn(true);
        assertEquals("Employee's data was successfully deleted", adminService.deleteEmployeeById(1));
    }

    @Test
    @DisplayName("Failed employee deleting")
    void deleteEmployeeById_should_return_failed_attempt() {
        mockLogger.info("Logging");
        given(employeeDAO.deleteEmployee(2)).willReturn(false);
        assertEquals("Failed attempt to delete employee's data", adminService.deleteEmployeeById(2));
    }

}