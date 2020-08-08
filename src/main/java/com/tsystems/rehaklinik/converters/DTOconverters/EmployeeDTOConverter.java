package com.tsystems.rehaklinik.converters.DTOconverters;

import com.tsystems.rehaklinik.dto.AuthenticationDataDTO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.Prescription;

public class EmployeeDTOConverter {

    public static Employee fromDTO (EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setPassportId(employeeDTO.getPassportId());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setEmail(employeeDTO.getEmail());

        AuthenticationData authenticationData = new AuthenticationData();
        authenticationData.setAuthenticationDataId(employeeDTO.getAuthenticationDataEmployee().getAuthenticationDataId());
        authenticationData.setUsername(employeeDTO.getAuthenticationDataEmployee().getUsername());
        authenticationData.setPassword(employeeDTO.getAuthenticationDataEmployee().getPassword());
        employee.setAuthenticationDataEmployee(authenticationData);

        employee.setPosition(employeeDTO.getPosition());
        employee.setQualificationCategory(employeeDTO.getQualificationCategory());
        employee.setOfficeOrWardNumber(employeeDTO.getOfficeOrWardNumber());
        employee.setRole(employeeDTO.getRole());

        return employee;
    }


    public static EmployeeDTO toDTO (Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setMiddleName(employee.getMiddleName());
        employeeDTO.setSurname(employee.getSurname());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setPassportId(employee.getPassportId());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setEmail(employee.getEmail());

        AuthenticationDataDTO authenticationDataDTO = new AuthenticationDataDTO();
        authenticationDataDTO.setAuthenticationDataId(employee.getAuthenticationDataEmployee().getAuthenticationDataId());
        authenticationDataDTO.setUsername(employee.getAuthenticationDataEmployee().getUsername());
        authenticationDataDTO.setPassword(employee.getAuthenticationDataEmployee().getPassword());
        employeeDTO.setAuthenticationDataEmployee(authenticationDataDTO);

        employeeDTO.setPosition(employee.getPosition());
        employeeDTO.setQualificationCategory(employee.getQualificationCategory());
        employeeDTO.setOfficeOrWardNumber(employee.getOfficeOrWardNumber());
        employeeDTO.setRole(employee.getRole());

        return employeeDTO;
    }
}
