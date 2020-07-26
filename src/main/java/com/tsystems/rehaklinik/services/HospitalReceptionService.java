package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.Patient;

import java.util.List;

public interface HospitalReceptionService {

    Patient addNewPatient(Patient patient);

    String deletePatientById(int id);

    List<Patient> showAllPatients();

    Patient getPatientById(int id);

    List<Patient> findPatientBySurname(String surname);

    Patient editPatient(Patient editedPatient);





//    PatientDTO findPatientByIdDTO(int patientId);
}
