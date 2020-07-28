package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.Gender;
import com.tsystems.rehaklinik.types.Roles;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "patients", schema = "rehaklinik",
        uniqueConstraints =
                {@UniqueConstraint(columnNames = "passport_id", name = "UNQ_PATIENT_PASSPORT_ID"),
                        @UniqueConstraint(columnNames = "email", name = "UNQ_PATIENT_EMAIL"),
                        @UniqueConstraint(columnNames = "insurance_policy_code", name = "UNQ_INSURANCE_POLICY_CODE")})
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", nullable = false, length = 11)
    private int patientId;

    @NotBlank(message = "Patient's first name mustn't be blank or null")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "second_name", length = 50)
    private String middleName;

    @NotBlank(message = "Patient's surname mustn't be blank or null")
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @NotNull(message = "Patient's gender mustn't be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "ENUM('MALE', 'FEMALE')", nullable = false)
    private Gender gender;

    @NotNull(message = "Patient's date of birth must be set")
    @Past(message = "Patient's date of birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotNull(message = "Patient's passport id must be set")
    @Column(name = "passport_id", nullable = false)
    private String passportId;

    @NotBlank(message = "Patient's address mustn't be blank or null")
    @Size(min = 10, max = 255, message = "Patient's address length must be no less than 10 and no more than 255 characters")
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull(message = "Patient's phone number must be set")
    @Column(name = "phone_number", nullable = false, length = 25)
    private String phoneNumber;

    @Pattern(regexp = "(\\w+\\.)*\\w+@(\\w+\\.)+[a-zA-z]{2,}|[ \t]+", message = "Wrong patient's email")
    @Column(name = "email", length = 80)
    private String email;

    @NotNull
    @Column(name = "insurance_policy_code", nullable = false, length = 50)
    private String insurancePolicyCode;

    @NotBlank(message = "Patient's insurance company mustn't be blank or null")
    @Size(min = 5, max = 50, message = "Patient's insurance company length must be no less than 5 and no more than 50 characters")
    @Column(name = "insurance_company", nullable = false, length = 50)
    private String insuranceCompany;


    @NotNull(message = "Patient's consent to personal data processing must be set")
    @Column(name = "consent_to_personal_data_processing", nullable = false)
    private boolean consentToPersonalDataProcessing;

    @NotNull(message = "Role must be set")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('PATIENT')",
            nullable = false, length = 25)
    private Roles role;

    @OneToOne (cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_authentication_data_id", referencedColumnName = "authentication_data_id", nullable = false)
    private AuthenticationData authenticationDataPatient;

    @ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "attending_doctor_id", referencedColumnName = "employee_id")
    private Employee attendingDoctorId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_record_id", referencedColumnName = "medical_record_id",  nullable = false)
    private MedicalRecord medicalRecord;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<TreatmentEvent> treatmentEvents;
}
