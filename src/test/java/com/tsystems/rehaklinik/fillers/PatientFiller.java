package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.dto.AuthenticationDataDTO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.types.Roles;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class PatientFiller {

    private static final int ID = 2;
    private static final String FIRST_NAME = "Peter";
    private static final String SURNAME = "Fletcher";
    private static final LocalDate BIRTHDAY = LocalDate.of(1974, 11, 12);
    private static final String PHONE_NUMBER = "(020) 1234-1726";
    private static final String ADDRESS = "294 King Street London SE47 3FO";
    private static final String EMAIL = "peterfletcher@gmail.com";
    private static final String PASSPORT_ID = "1234879345";
    private static final String INSURANCE_POLICY_CODE = "ABC45081371PN";
    private static final String INSURANCE_COMPANY = "Viva Medicare";
    private static final boolean CONSENT_TO_PERSONAL_DATA_PROCESSING = true;
    private static final Roles ROLE = Roles.PATIENT;

    public static Patient getPatient() {
        Patient patient = new Patient();
        patient.setPatientId(ID);
        patient.setFirstName(FIRST_NAME);
        patient.setSurname(SURNAME);
        patient.setDateOfBirth(BIRTHDAY);
        patient.setPhoneNumber(PHONE_NUMBER);
        patient.setAddress(ADDRESS);
        patient.setEmail(EMAIL);
        patient.setPassportId(PASSPORT_ID);
        patient.setInsurancePolicyCode(INSURANCE_POLICY_CODE);
        patient.setInsuranceCompany(INSURANCE_COMPANY);
        patient.setConsentToPersonalDataProcessing(CONSENT_TO_PERSONAL_DATA_PROCESSING);
        patient.setRole(ROLE);
        Employee attendingDoctor = EmployeeFiller.getEmployee();
        patient.setAttendingDoctorId(attendingDoctor);
        AuthenticationData authenticationDataPatient = new AuthenticationData();
        authenticationDataPatient.setAuthenticationDataId(2);
        authenticationDataPatient.setUsername("peterfletcher");
        authenticationDataPatient.setPassword("peterfletcher");
        patient.setAuthenticationDataPatient(authenticationDataPatient);
        patient.setMedicalRecord(MedicalRecordFiller.getMedicalRecord());
        return patient;
    }

    public static PatientDTO getPatientDTO() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientId(ID);
        patientDTO.setFirstName(FIRST_NAME);
        patientDTO.setSurname(SURNAME);
        patientDTO.setDateOfBirth(BIRTHDAY);
        patientDTO.setPhoneNumber(PHONE_NUMBER);
        patientDTO.setAddress(ADDRESS);
        patientDTO.setEmail(EMAIL);
        patientDTO.setPassportId(PASSPORT_ID);
        patientDTO.setInsurancePolicyCode(INSURANCE_POLICY_CODE);
        patientDTO.setInsuranceCompany(INSURANCE_COMPANY);
        patientDTO.setConsentToPersonalDataProcessing(CONSENT_TO_PERSONAL_DATA_PROCESSING);
        patientDTO.setRole(ROLE);
        EmployeeDTO attendingDoctor = EmployeeFiller.getEmployeeDTO();
        patientDTO.setAttendingDoctorId(attendingDoctor);
        AuthenticationDataDTO authenticationDataPatient = new AuthenticationDataDTO();
        authenticationDataPatient.setAuthenticationDataId(2);
        authenticationDataPatient.setUsername("peterfletcher");
        authenticationDataPatient.setPassword("peterfletcher");
        patientDTO.setAuthenticationDataPatient(authenticationDataPatient);
        patientDTO.setMedicalRecord(MedicalRecordFiller.getMedicalRecordDTO());
        return patientDTO;
    }
}
