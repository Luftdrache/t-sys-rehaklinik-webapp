package com.tsystems.rehaklinik.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "patients", schema = "rehaklinik",
        uniqueConstraints =
                {@UniqueConstraint(columnNames = "passport_id", name = "UNQ_PATIENT_PASSPORT_ID"),
                        @UniqueConstraint(columnNames = "email", name = "UNQ_PATIENT_EMAIL"),
                        @UniqueConstraint(columnNames = "insurance_policy_code", name = "UNQ_INSURANCE_POLICY_CODE")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", nullable = false, length = 11)
    private int patientId;

    @NotNull(message = "Patient's first name must be set")
    @NotBlank(message = "Patient's first name mustn't be blank")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Patient's second name mustn't be blank")
    @Column(name = "second_name", length = 50)
    private String secondName;

    @NotNull(message = "Patient's surname must be set")
    @NotBlank(message = "Patient's surname mustn't be blank")
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @NotNull(message = "Patient's date of birth must be set")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotNull(message = "Patient's gender must be set")
    @Column(name = "gender", nullable = false, length = 6)
    private String gender;

    @NotNull(message = "Patient's phone number must be set")
    @Column(name = "phone_number", nullable = false, length = 25)
    private String phoneNumber;

    @Pattern(regexp ="(\\w+\\.)*\\w+@(\\w+\\.)+[a-zA-z]{2,}", message = "Wrong patient's email")
    @Column(name = "email", length = 80)
    private String email;

    @NotBlank(message = "Patient's address mustn't be blank or null")
    @Size(min = 10, max = 255, message = "Patient's address length must be no less than 10 and no more than 255 characters")
    @Column(name = "address", nullable = false, length = 250)
    private String address;

    @NotNull(message = "Patient's passport id must be set")
    @Pattern(regexp = "\\d{4}\\s\\d{6}", message = "Wrong patient's passport id")
    @Column(name = "passport_id", nullable = false)
    private int passportId;

    @Column(name = "insurance_policy_code", nullable = false, length = 50)
    private String insurancePolicyCode;

    @NotBlank(message = "Patient's insurance company mustn't be blank or null")
    @Size(min = 5, max = 50, message = "Patient's insurance company length must be no less than 5 and no more than 50 characters")
    @Column(name = "insurance_company", nullable = false, length = 50)
    private String insuranceCompany;

    @NotNull(message = "Patient's consent to personal data processing must be set")
    @Column(name = "consent_to_personal_data_processing", nullable = false)
    private boolean consentToPersonalDataProcessing;

    @OneToOne
    @JoinColumn(name = "patient_authentication_data_id", referencedColumnName = "authentication_data_id", nullable = false)
    private AuthenticationData authenticationDataPatient;

    @NotNull(message = "Patient's attending doctor must be set")
    @ManyToOne
    @JoinColumn(name = "attending_doctor_id", referencedColumnName = "employee_id", nullable = false)
    private Employee attendingDoctorId;

    @ManyToOne
    @JoinColumn(name = "medical_record_id", referencedColumnName = "medical_record_id")
    private MedicalRecord medicalRecord;

    @OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "patient")
    private List<TreatmentEvent> treatmentEvents;

}
