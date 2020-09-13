package com.tsystems.rehaklinik.dao.interfaces;


import com.tsystems.rehaklinik.entities.Patient;

import java.util.List;


/**
 * DAO for {@link Patient} objects
 *
 * @author Julia Dalskaya
 */
public interface PatientDAO {

    /**
     * Creates new patient
     *
     * @param patient new patient to save in database
     * @return created patient
     */
    Patient createPatient(Patient patient);


    /**
     * Deletes patient by patient's id
     *
     * @param patientId patient's id
     * @return boolean result of deleting operation
     */
    boolean deletePatient(int patientId);


    /**
     * Updates patient's data
     *
     * @param patient edited patient to update
     * @return updated patient
     */
    Patient updatePatient(Patient patient);


    /**
     * Searches for a patient by patient's id
     *
     * @param patientId patient's id
     * @return found patient
     */
    Patient findPatientById(int patientId);

    /**
     * Searches patient('s) by id
     *
     * @param patientSurname patient's surname
     * @return found patient list
     */
    List<Patient> findPatientBySurname(String patientSurname);


    /**
     * Searches for all patients whose information is stored in the database
     *
     * @return found patient list
     */
    List<Patient> findAll();


    /**
     * Searches for patients being treated by a specific doctor
     *
     * @param doctorId
     * @return
     */
    List<Patient> findPatientByDoctorId(int doctorId);
}
