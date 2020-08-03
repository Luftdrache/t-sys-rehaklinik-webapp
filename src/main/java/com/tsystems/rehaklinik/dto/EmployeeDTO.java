package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.entities.WorkingSchedule;
import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private int employeeId;

    @NotBlank(message = "Employee's first name mustn't be blank or null")
    @Size(min = 2, max = 50, message = "Employees first name length must be no less than 2 and no more than 50 characters")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Employee's surname mustn't be blank or null")
    @Size(min = 2, max = 50, message = "Employees surname length must be no less than 2 and no more than 50 characters")
    private String surname;

    @NotNull(message = "Employee's date of birth must be set")
    @Past(message = "Employee's date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Employee's passport id must be set")
    private String passportId;

    @NotBlank(message = "Employee's address mustn't be blank or null")
    @Size(min = 10, max = 255, message = "Employee's address length must be no less than 10 and no more than 255 characters")
    private String address;

    @NotBlank(message = "Employee's phone number must be set")
    private String phoneNumber;

    @Pattern(regexp = "(\\w+\\.)*\\w+@(\\w+\\.)+[a-zA-z]{2,}", message = "Wrong employee's email")
    private String email;

    @NotNull(message = "Employee's authentication data must be set")
    private AuthenticationData authenticationDataEmployee;

    @Size(max=50, message = "Position length must be no more than 50 characters")
    private String position;

    @NotNull(message = "Employee's qualification category mustn't be blank or null")
    private QualificationCategories qualificationCategory;

    @Positive(message = "Doctor's office or ward number must be positive")
    private int officeOrWardNumber;

    private WorkingSchedule workingSchedule;

    @NotNull(message = "Role must be set")
    private Roles role;

    private List<PatientDTO> patients;
}
