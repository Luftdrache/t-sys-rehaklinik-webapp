package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.dto.AuthenticationDataDTO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;

import java.time.LocalDate;


public class EmployeeDTOFiller {
    private static final int ID = 1;
    private static final String FIRST_NAME = "Albert";
    private static final String SURNAME = "Young";
    private static final LocalDate BIRTHDAY = LocalDate.of(1988, 11, 12);
    private static final String PHONE_NUMBER = "(020) 1234-1726";
    private static final String ADDRESS = "294 King Street London SE47 3FO";
    private static final String EMAIL = "robertyoung@rehaklinik.com";
    private static final String PASSPORT_ID = "1234879345";
    private static final String POSITION = "Cardiologist";
    private static final QualificationCategories CATEGORY = QualificationCategories.FIRST;
    private static final Roles ROLE = Roles.DOCTOR;

    public EmployeeDTOFiller() {
    }

    public static EmployeeDTO getEmployeeDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(ID);
        employeeDTO.setFirstName(FIRST_NAME);
        employeeDTO.setSurname(SURNAME);
        employeeDTO.setDateOfBirth(BIRTHDAY);
        employeeDTO.setPhoneNumber(PHONE_NUMBER);
        employeeDTO.setAddress(ADDRESS);
        employeeDTO.setEmail(EMAIL);
        employeeDTO.setPassportId(PASSPORT_ID);
        employeeDTO.setPosition(POSITION);
        employeeDTO.setQualificationCategory(CATEGORY);
        employeeDTO.setRole(ROLE);
        AuthenticationDataDTO authenticationDataDTO = new AuthenticationDataDTO();
        authenticationDataDTO.setAuthenticationDataId(1);
        authenticationDataDTO.setUsername("albertyoung");
        authenticationDataDTO.setPassword("albertyoung");
        employeeDTO.setAuthenticationDataEmployee(authenticationDataDTO);
        return employeeDTO;
    }

}
