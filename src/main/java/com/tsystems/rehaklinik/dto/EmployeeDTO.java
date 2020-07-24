package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;

import java.time.LocalDate;

public class EmployeeDTO {
    private int employeeId;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String position;
    private QualificationCategories qualificationCategory;
    private String address;
    private String phone;
    private String email;
    private Roles role;

    public EmployeeDTO(Employee employeeFromDao) {
        this.employeeId = employeeFromDao.getEmployeeId();
        this.name = employeeFromDao.getFirstName() + " " + employeeFromDao.getMiddleName();
        this.surname = employeeFromDao.getSurname();
        this.dateOfBirth = employeeFromDao.getDateOfBirth();
        this.position = employeeFromDao.getPosition().getPositionName();
        this.qualificationCategory = employeeFromDao.getQualificationCategory().getQualificationCategoryName();
        this.address = employeeFromDao.getAddress();
        this.phone = employeeFromDao.getPhoneNumber();
        this.email = employeeFromDao.getEmail();
        this.role = employeeFromDao.getRole().getRoleName();
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public QualificationCategories getQualificationCategory() {
        return qualificationCategory;
    }

    public void setQualificationCategory(QualificationCategories qualificationCategory) {
        this.qualificationCategory = qualificationCategory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", position='" + position + '\'' +
                ", qualificationCategory=" + qualificationCategory +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
