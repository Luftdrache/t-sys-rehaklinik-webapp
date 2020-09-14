package com.tsystems.rehaklinik.services.interfaces;

import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.dto.PatientShortViewDTO;

import java.util.List;

/**
 * Reception service.
 *
 * @author Julia Dalskaya
 */
public interface ReceptionService {

    /**
     * Business logic for adding a new patient
     *
     * @param patient PatientDTO
     * @return PatientDTO
     */
    PatientDTO addNewPatient(PatientDTO patient);

    /**
     * Business logic for deleting a patient
     *
     * @param id patient id
     * @return message
     */
    String deletePatientById(int id);

    /**
     * Business logic for finding all patients
     *
     * @return List of PatientShortViewDTO'
     */
    List<PatientShortViewDTO> showAllPatients();

    /**
     * Business logic for getting a patient by id
     *
     * @param id patient id
     * @return PatientDTO
     */
    PatientDTO getPatientById(int id);

    /**
     * Business logic for finding patient('s) by surname
     *
     * @param surname patient surname
     * @return List of PatientShortViewDTO'
     */
    List<PatientShortViewDTO> findPatientBySurname(String surname);

    /**
     * Business logic for editing a patient
     *
     * @param editedPatient PatientDTO
     * @return PatientDTO
     */
    PatientDTO editPatient(PatientDTO editedPatient);

    /**
     * Business logic for getting all doctors
     *
     * @return list of doctors
     */
    List<EmployeeShortViewDTO> getAllDoctors();

    /**
     * Business logic for setting an attending doctor
     *
     * @param doctorId  doctor id
     * @param patientId patient id
     * @return PatientDTO
     */
    PatientDTO setAttendingDoctor(int doctorId, int patientId);

}
