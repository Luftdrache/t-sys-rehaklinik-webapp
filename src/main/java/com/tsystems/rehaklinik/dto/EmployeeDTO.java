package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;

import java.time.LocalDate;

public class EmployeeDTO {
    private int employeeId;
    private String name;
    private String position;
    private QualificationCategories qualificationCategory;
    private String phone;
    private String email;
    private Roles role;

    public EmployeeDTO(Employee employeeFromDao) {
        this.employeeId = employeeFromDao.getEmployeeId();
        this.name = employeeFromDao.getFirstName() + " " + employeeFromDao.getMiddleName() + " " + employeeFromDao.getSurname();
        this.position = employeeFromDao.getPosition();
        this.qualificationCategory = employeeFromDao.getQualificationCategory();
        this.phone = employeeFromDao.getPhoneNumber();
        this.email = employeeFromDao.getEmail();
        this.role = employeeFromDao.getRole();
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
                ", position='" + position + '\'' +
                ", qualificationCategory=" + qualificationCategory +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
