package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.dto.PatientShortViewDTO;
import com.tsystems.rehaklinik.entities.Patient;

import java.util.List;

public interface ReceptionService {

    Patient addNewPatient(Patient patient);

    String deletePatientById(int id);

    List<PatientShortViewDTO> showAllPatients();

    Patient getPatientById(int id);

    List<Patient> findPatientBySurname(String surname);

    Patient editPatient(Patient editedPatient);

    List<EmployeeShortViewDTO> getAllDoctors();


//    PatientDTO findPatientByIdDTO(int patientId);
}
