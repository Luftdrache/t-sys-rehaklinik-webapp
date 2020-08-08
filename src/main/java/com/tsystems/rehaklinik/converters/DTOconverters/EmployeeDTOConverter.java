package com.tsystems.rehaklinik.converters.DTOconverters;

import com.tsystems.rehaklinik.dto.AuthenticationDataDTO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.Prescription;

public class EmployeeDTOConverter {
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

    public static Employee fromDTO (EmployeeDTO employeeDTO) {

        return null;
    }
}
