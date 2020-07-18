package com.tsystems.rehaklinik.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees", schema = "rehaklinik",
        uniqueConstraints =
                {@UniqueConstraint(columnNames = "passport_id", name = "UNQ_EMPLOYEE_PASSPORT_ID"),
                        @UniqueConstraint(columnNames = "email", name = "UNQ_EMPLOYEE_EMAIL")})
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
    @Column(name = "second_name", length = 50)
    private String secondName;

    @NotBlank(message = "Employee's surname mustn't be blank or null")
    @Size(min = 2, max = 50, message = "Employees surname length must be no less than 2 and no more than 50 characters")
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @NotNull(message = "Employee's date of birth must be set")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotNull(message = "Employee's passport id must be set")
    @Pattern(regexp = "\\d{4}\\s\\d{6}", message = "Wrong employees's passport id")
    @Column(name = "passport_id", nullable = false)
    private int passportId;

    @NotBlank(message = "Employee's address mustn't be blank or null")
    @Size(min = 10, max = 255, message = "Employee's address length must be no less than 10 and no more than 255 characters")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "Employee's phone number must be set")
    @Column(name = "phone_number", nullable = false, length = 25)
    private String phoneNumber;

    @Pattern(regexp ="(\\w+\\.)*\\w+@(\\w+\\.)+[a-zA-z]{2,}", message = "Wrong employee's email")
    @Column(name = "email", length = 100)
    private String email;

    @Positive(message = "Cabinet or ward number must be positive")
    @Column(name = "cabinet_or_ward_number", length = 3)
    private int cabinetOrWardNumber;

    @NotNull (message = "Employee's authentication data must be set")
    @OneToOne
    @JoinColumn(name = "employee_authentication_data_id", referencedColumnName = "authentication_data_id", nullable = false)
    private AuthenticationData authenticationDataEmployee;

    @NotNull(message = "Employee's position must be set")
    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "position_id", nullable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "qualification_category_id", referencedColumnName = "qualification_category_id")
    private QualificationCategory qualificationCategory;

    @ManyToOne
    @JoinColumn(name = "working_schedule_id", referencedColumnName = "working_schedule_id")
    private WorkingSchedule workingSchedule;

    @NotNull(message = "Employee's role must be set")
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "attendingDoctorId")
    private List<Patient> patients;

}
