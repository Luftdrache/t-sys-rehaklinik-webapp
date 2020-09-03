package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;

import java.time.LocalDate;

import static com.tsystems.rehaklinik.fillers.AuthenticationDataFiller.getAuthenticationData;


public class EmployeeFiller {
    private static final int ID = 1;
    private static final String FIRST_NAME = "Albert";
    private static final String SURNAME = "Young";
    private static final LocalDate BIRTHDAY = LocalDate.of(1988, 10, 12);
    private static final String PHONE_NUMBER = "(020) 1234-1726";
    private static final String ADDRESS = "294 King Street London SE47 3FO";
    private static final String EMAIL = "robertyoung@rehaklinik.com";
    private static final String PASSPORT_ID = "1234879345";
    private static final String POSITION = "Cardiologist";
    private static final QualificationCategories CATEGORY = QualificationCategories.FIRST;
    private static final Roles ROLE = Roles.DOCTOR;

    public EmployeeFiller() {
    }

    public static Employee getEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId(ID);
        employee.setFirstName(FIRST_NAME);
        employee.setSurname(SURNAME);
        employee.setDateOfBirth(BIRTHDAY);
        employee.setPhoneNumber(PHONE_NUMBER);
        employee.setAddress(ADDRESS);
        employee.setEmail(EMAIL);
        employee.setPassportId(PASSPORT_ID);
        employee.setPosition(POSITION);
        employee.setQualificationCategory(CATEGORY);
        employee.setRole(ROLE);
        employee.setAuthenticationDataEmployee(getAuthenticationData());
        return employee;
    }
}

