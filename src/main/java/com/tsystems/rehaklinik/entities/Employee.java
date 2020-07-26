package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees", schema = "rehaklinik",
        uniqueConstraints =
                {@UniqueConstraint(columnNames = "passport_id", name = "UNQ_EMPLOYEE_PASSPORT_ID"),
                        @UniqueConstraint(columnNames = "email", name = "UNQ_EMPLOYEE_EMAIL"),
                        @UniqueConstraint(columnNames = "employee_authentication_data_id", name = "UNQ_EMPLOYEE_AUTH_DATA")})
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, length = 11)
    private int employeeId;


    @NotBlank(message = "Employee's first name mustn't be blank or null")
    @Size(min = 2, max = 50, message = "Employees first name length must be no less than 2 and no more than 50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;


    @Size(min = 2, max = 50, message = "Employees second name length must be no less than 2 and no more than 50 characters")
    @Column(name = "middle_name", length = 50)
    private String middleName;


    @NotBlank(message = "Employee's surname mustn't be blank or null")
    @Size(min = 2, max = 50, message = "Employees surname length must be no less than 2 and no more than 50 characters")
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;


    @NotNull(message = "Employee's date of birth must be set")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;


    @NotNull(message = "Employee's passport id must be set")
    @Column(name = "passport_id", nullable = false)
    private String passportId;


    @NotBlank(message = "Employee's address mustn't be blank or null")
    @Size(min = 10, max = 255, message = "Employee's address length must be no less than 10 and no more than 255 characters")
    @Column(name = "address", nullable = false)
    private String address;


    @NotBlank(message = "Employee's phone number must be set")
    @Column(name = "phone_number", nullable = false, length = 25)
    private String phoneNumber;


    @Pattern(regexp = "(\\w+\\.)*\\w+@(\\w+\\.)+[a-zA-z]{2,}", message = "Wrong employee's email")
    @Column(name = "email", length = 100)
    private String email;


    @NotNull(message = "Employee's authentication data must be set")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "employee_authentication_data_id", referencedColumnName = "authentication_data_id", nullable = false)
    private AuthenticationData authenticationDataEmployee;


    @NotBlank(message = "Employee's position mustn't be blank or null")
    @Size(max=50, message = "Position length must be no more than 50 characters")
    @Column(name = "position", nullable = false, length = 50)
    private String position;


    @NotBlank(message = "Employee's qualification category mustn't be blank or null")
    @Enumerated(EnumType.STRING)
    @Column(name = "qualification_category", columnDefinition = "ENUM('SECOND', 'FIRST', 'HIGHER', 'NONE')",
            nullable = false)
    private QualificationCategories qualificationCategory;


    @Positive(message = "Doctor's office or ward number must be positive")
    @Column(name = "office_or_ward_number", length = 3)
    private int officeOrWardNumber;


    @ManyToOne (fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "working_schedule_id", referencedColumnName = "working_schedule_id")
    private WorkingSchedule workingSchedule;


    @NotNull(message = "Role must be set")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('ADMIN', 'RECEPTIONIST', 'DOCTOR', 'NURSE')",
            nullable = false, length = 25)
    private Roles role;


    @OneToMany(mappedBy = "attendingDoctorId", fetch = FetchType.LAZY)
    private List<Patient> patients;
}
