package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.PatientReceptionViewDTO;
import com.tsystems.rehaklinik.entities.Patient;

import java.util.List;

public interface ReceptionService {

    Patient addNewPatient(Patient patient);

    String deletePatientById(int id);

    List<PatientReceptionViewDTO> showAllPatients();

    Patient getPatientById(int id);

    List<Patient> findPatientBySurname(String surname);

    Patient editPatient(Patient editedPatient);





//    PatientDTO findPatientByIdDTO(int patientId);
}
