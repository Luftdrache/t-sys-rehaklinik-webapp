package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.entities.Patient;

import java.util.List;

public interface ReceptionService {

    Patient addNewPatient(Patient patient);

    String deletePatientById(int id);

    List<PatientDTO> showAllPatients();

    Patient getPatientById(int id);

    List<Patient> findPatientBySurname(String surname);

    Patient editPatient(Patient editedPatient);

    List<EmployeeDTO> getAllDoctors();


//    PatientDTO findPatientByIdDTO(int patientId);
}
