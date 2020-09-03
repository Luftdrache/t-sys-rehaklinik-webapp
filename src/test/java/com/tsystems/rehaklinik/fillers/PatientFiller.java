package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.Patient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientFiller extends PatientCommon {
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
}
