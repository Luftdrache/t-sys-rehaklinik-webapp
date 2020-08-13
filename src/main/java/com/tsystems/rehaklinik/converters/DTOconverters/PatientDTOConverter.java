package com.tsystems.rehaklinik.converters.DTOconverters;

import com.tsystems.rehaklinik.dto.AuthenticationDataDTO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.dto.MedicalRecordDTO;
import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.entities.MedicalRecord;
import com.tsystems.rehaklinik.entities.Patient;


public class PatientDTOConverter {

    public static PatientDTO toDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientId(patient.getPatientId());
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setMiddleName(patient.getMiddleName());
        patientDTO.setSurname(patient.getSurname());
        patientDTO.setGender(patient.getGender());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDTO.setPassportId(patient.getPassportId());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setPhoneNumber(patient.getPhoneNumber());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setInsurancePolicyCode(patient.getInsurancePolicyCode());
        patientDTO.setInsuranceCompany(patient.getInsuranceCompany());
        patientDTO.setConsentToPersonalDataProcessing(patient.isConsentToPersonalDataProcessing());
        patientDTO.setRole(patient.getRole());

        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setMedicalRecordId(patient.getMedicalRecord().getMedicalRecordId());
        medicalRecordDTO.setHospitalStayStatus(patient.getMedicalRecord().getHospitalStayStatus());
        patientDTO.setMedicalRecord(medicalRecordDTO);

        AuthenticationDataDTO authenticationDataDTO = new AuthenticationDataDTO();
        authenticationDataDTO.setAuthenticationDataId(patient.getAuthenticationDataPatient().getAuthenticationDataId());
        authenticationDataDTO.setPassword(patient.getAuthenticationDataPatient().getPassword());
        authenticationDataDTO.setUsername(patient.getAuthenticationDataPatient().getUsername());
        patientDTO.setAuthenticationDataPatient(authenticationDataDTO);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        if (patient.getAttendingDoctorId() != null) {
            employeeDTO = EmployeeDTOConverter.toDTO(patient.getAttendingDoctorId());
        }

        patientDTO.setAttendingDoctorId(employeeDTO);
        return patientDTO;
    }


    public static Patient fromDTO(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setPatientId(patientDTO.getPatientId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setMiddleName(patientDTO.getMiddleName());
        patient.setSurname(patientDTO.getSurname());
        patient.setGender(patientDTO.getGender());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setPassportId(patientDTO.getPassportId());
        patient.setAddress(patientDTO.getAddress());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setEmail(patientDTO.getEmail());
        patient.setInsurancePolicyCode(patientDTO.getInsurancePolicyCode());
        patient.setInsuranceCompany(patientDTO.getInsuranceCompany());
        patient.setConsentToPersonalDataProcessing(patientDTO.isConsentToPersonalDataProcessing());
        patient.setRole(patientDTO.getRole());

        AuthenticationData authenticationData = new AuthenticationData();
        authenticationData.setAuthenticationDataId(patientDTO.getAuthenticationDataPatient().getAuthenticationDataId());
        authenticationData.setPassword(patientDTO.getAuthenticationDataPatient().getPassword());
        authenticationData.setUsername(patientDTO.getAuthenticationDataPatient().getUsername());
        patient.setAuthenticationDataPatient(authenticationData);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setMedicalRecordId(patientDTO.getMedicalRecord().getMedicalRecordId());
        medicalRecord.setHospitalStayStatus(patientDTO.getMedicalRecord().getHospitalStayStatus());
        patient.setMedicalRecord(medicalRecord);
        patient.setAttendingDoctorId(null);
        return patient;
    }
}
