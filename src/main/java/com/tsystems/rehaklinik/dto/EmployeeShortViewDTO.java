package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * DTO for {@link Employee} objects. It is a shortened representation of an object.
 *
 * @author Julia Dalskaya
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeShortViewDTO {
    private int employeeId;
    private String name;
    private String position;
    private QualificationCategories qualificationCategory;
    private String phone;
    private String email;
    private Roles role;


    public EmployeeShortViewDTO(Employee employeeFromDao) {
        this.employeeId = employeeFromDao.getEmployeeId();
        this.name = employeeFromDao.getFirstName() + " " + employeeFromDao.getMiddleName() + " " + employeeFromDao.getSurname();
        this.position = employeeFromDao.getPosition();
        this.qualificationCategory = employeeFromDao.getQualificationCategory();
        this.phone = employeeFromDao.getPhoneNumber();
        this.email = employeeFromDao.getEmail();
        this.role = employeeFromDao.getRole();
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
