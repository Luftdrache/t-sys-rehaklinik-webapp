package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.Patient;

import java.util.List;

public interface PatientDAO {

    Patient createPatient(Patient patient);

    String deletePatient(int patientId);

    Patient updatePatient(Patient patient);

    Patient findPatientById(int patientId);

    List<Employee> findPatientBySurname(String patientSurname);

    List<Patient> findAll();

}
