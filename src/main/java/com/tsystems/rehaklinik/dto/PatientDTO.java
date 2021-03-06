package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.types.Gender;
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
 * DTO for {@link Patient} objects
 *
 * @author Julia Dalskaya
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private int patientId;

    @NotBlank(message = "Patient's first name mustn't be blank or null")
    @Size(min = 2, max = 50, message = "Patient's first name length must be no less than 2 and no more than 50 characters")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Patient's surname mustn't be blank or null")
    private String surname;

    @NotNull(message = "Patient's gender mustn't be null")
    private Gender gender;

    @NotNull(message = "Patient's date of birth must be set")
    @Past(message = "Patient's date of birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Patient's passport id must be set")
    @Pattern(regexp = "^[0-9\\sA-Z]+$", message = "Wrong characters in passport id")
    private String passportId;

    @NotBlank(message = "Patient's address mustn't be blank or null")
    @Size(min = 10, max = 255, message = "Patient's address length must be no less than 10 and no more than 255 characters")
    private String address;

    @NotBlank(message = "Patient's phone number must be set")
    @Size(min = 10, message = "Phone number length must be no less than 10 digits")
    @Pattern(regexp = "^[0-9\\s\\-()]+$", message = "Wrong characters in phone number")
    private String phoneNumber;

    @Pattern(regexp = "(\\w+\\.)*\\w+@(\\w+\\.)+[a-zA-z]{2,}|[ \t]+", message = "Wrong patient's email")
    private String email;

    @NotBlank(message = "Insurance Policy Code required")
    private String insurancePolicyCode;

    @NotBlank(message = "Patient's insurance company mustn't be blank or null")
    @Size(min = 5, max = 50, message = "Patient's insurance company length must be no less than 5 and no more than 50 characters")
    private String insuranceCompany;

    @NotNull(message = "Patient's consent to personal data processing must be set")
    private boolean consentToPersonalDataProcessing;

    @NotNull(message = "Role must be set")
    private Roles role;

    private AuthenticationDataDTO authenticationDataPatient;

    private EmployeeDTO attendingDoctorId;

    private MedicalRecordDTO medicalRecord;

    private List<Prescription> prescriptions;

    private List<TreatmentEvent> treatmentEvents;
}
