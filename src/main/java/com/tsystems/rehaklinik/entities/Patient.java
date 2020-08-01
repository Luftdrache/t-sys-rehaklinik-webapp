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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", nullable = false, length = 11)
    private int patientId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "second_name", length = 50)
    private String middleName;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "ENUM('MALE', 'FEMALE')", nullable = false)
    private Gender gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "passport_id", nullable = false)
    private String passportId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false, length = 25)
    private String phoneNumber;

    @Column(name = "email", length = 80)
    private String email;

    @Column(name = "insurance_policy_code", nullable = false, length = 50)
    private String insurancePolicyCode;

    @Column(name = "insurance_company", nullable = false, length = 50)
    private String insuranceCompany;

    @Column(name = "consent_to_personal_data_processing", nullable = false)
    private boolean consentToPersonalDataProcessing;

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
