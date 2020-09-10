package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


/**
 * DTO for {@link Employee} objects
 *
 * @author Julia Dalskaya
 */
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull(message = "Employee's passport id must be set")
    @Pattern(regexp = "^[0-9\\sA-Z]+$", message = "Wrong characters in passport id")
    private String passportId;

    @NotBlank(message = "Employee's address mustn't be blank or null")
    @Size(min = 10, max = 255, message = "Employee's address length must be no less than 10 and no more than 255 characters")
    private String address;

    @NotBlank(message = "Employee's phone number must be set")
    @Size(min = 10, message = "Phone number length must be no less than 10 digits")
    @Pattern(regexp = "^[0-9\\s\\-\\(\\)]+$", message = "Wrong characters in phone number")
    private String phoneNumber;

    @Pattern(regexp = "(\\w+\\.)*\\w+@(\\w+\\.)+[a-zA-z]{2,}", message = "Wrong employee's email")
    private String email;

    @NotNull(message = "Employee's authentication data must be set")
    private AuthenticationDataDTO authenticationDataEmployee;

    @Size(max = 50, message = "Position length must be no more than 50 characters")
    private String position;

    @NotNull(message = "Employee's qualification category mustn't be blank or null")
    private QualificationCategories qualificationCategory;

    @PositiveOrZero(message = "Doctor's office or ward number must be zero or positive")
    private int officeOrWardNumber;

    @NotNull(message = "Role must be set")
    private Roles role;

    private List<PatientDTO> patients;
}
