package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.QualificationCategories;
import com.tsystems.rehaklinik.types.Roles;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees", schema = "rehaklinik",
        uniqueConstraints =
                {@UniqueConstraint(columnNames = "passport_id", name = "UNQ_EMPLOYEE_PASSPORT_ID"),
                        @UniqueConstraint(columnNames = "email", name = "UNQ_EMPLOYEE_EMAIL"),
                        @UniqueConstraint(columnNames = "employee_authentication_data_id", name = "UNQ_EMPLOYEE_AUTH_DATA")})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, length = 11)
    private int employeeId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "passport_id", nullable = false)
    private String passportId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false, length = 25)
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "employee_authentication_data_id", referencedColumnName = "authentication_data_id", nullable = false)
    private AuthenticationData authenticationDataEmployee;

    @Column(name = "position", nullable = false, length = 50)
    private String position;

    @Enumerated(EnumType.STRING)
    @Column(name = "qualification_category", columnDefinition = "ENUM('SECOND', 'FIRST', 'HIGHER', 'NONE')",
            nullable = false)
    private QualificationCategories qualificationCategory;

    @Column(name = "office_or_ward_number", length = 3)
    private int officeOrWardNumber;


    @ManyToOne (fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "working_schedule_id", referencedColumnName = "working_schedule_id")
    private WorkingSchedule workingSchedule;


    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('ADMIN', 'RECEPTIONIST', 'DOCTOR', 'NURSE')",
            nullable = false, length = 25)
    private Roles role;


    @OneToMany(mappedBy = "attendingDoctorId", fetch = FetchType.LAZY)
    private List<Patient> patients;
}
