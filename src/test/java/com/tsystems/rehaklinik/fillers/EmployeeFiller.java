package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;

import java.time.LocalDate;

import static com.tsystems.rehaklinik.fillers.AuthenticationDataFiller.getAuthenticationData;


public class EmployeeFiller {
    public static final int ID = 1;
    public static final String FIRST_NAME = "Robert";
    public static final String SURNAME = "Young";
    public static final LocalDate BIRTHDAY = LocalDate.of(1988, 07, 12);
    public static final String PHONE_NUMBER = "(020) 1234-1726";
    public static final String ADDRESS = "294 King Street London SE47 3FO";
    public static final String EMAIL = "robertyoung@rehaklinik.com";
    public static final String PASSPORT_ID = "1234879345";
    public static final String POSITION = "Cardiologist";
    public static final QualificationCategories CATEGORY = QualificationCategories.FIRST;
    public static final Roles ROLE = Roles.DOCTOR;

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

