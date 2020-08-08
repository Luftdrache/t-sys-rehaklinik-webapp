package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.dto.PatientShortViewDTO;

import java.util.List;

public interface ReceptionService {

    PatientDTO addNewPatient(PatientDTO patient);

    String deletePatientById(int id);

    List<PatientShortViewDTO> showAllPatients();

    PatientDTO getPatientById(int id);

    List<PatientShortViewDTO> findPatientBySurname(String surname);

    PatientDTO editPatient(PatientDTO editedPatient);

    List<EmployeeShortViewDTO> getAllDoctors();

    PatientDTO setAttendingDoctor(int doctorId, int patientId);

}
