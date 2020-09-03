package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.dto.AuthenticationDataDTO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.dto.PatientDTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientDTOFiller extends PatientCommon {

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
        EmployeeDTO attendingDoctor = EmployeeDTOFiller.getEmployeeDTO();
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
