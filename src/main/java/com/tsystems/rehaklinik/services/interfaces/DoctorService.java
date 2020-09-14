package com.tsystems.rehaklinik.services.interfaces;

import com.tsystems.rehaklinik.dto.*;


import java.util.List;

/**
 * Doctor service.
 *
 * @author Julia Dalskaya
 */
public interface DoctorService {

    /**
     * Business logic for getting a medical record
     *
     * @param patientId patirnt id
     * @return MedicalRecordDTO
     */
    MedicalRecordDTO getMedicalRecord(int patientId);

    /**
     * Business logic for setting hospitalisation
     *
     * @param medicalRecord medical record
     * @return MedicalRecordDTO
     */
    MedicalRecordDTO setHospitalisation(MedicalRecordDTO medicalRecord);

    /**
     * Business logic for getting a clinical diagnosis
     *
     * @param clinicalDiagnoseId clinical diagnosis id
     * @return ClinicalDiagnosisDTO
     */
    ClinicalDiagnosisDTO getClinicalDiagnosis(int clinicalDiagnoseId);

    /**
     * Business logic for setting a clinical diagnosis
     *
     * @param clinicalDiagnosisDTO ClinicalDiagnosisDTO
     * @param medRecordId          medical record id
     * @return MedicalRecordDTO
     */
    MedicalRecordDTO setNewDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO, int medRecordId);

    /**
     * Business logic for editing a clinical diagnosis
     *
     * @param clinicalDiagnosisDTO ClinicalDiagnosisDTO
     * @return ClinicalDiagnosisDTO
     */
    ClinicalDiagnosisDTO editClinicalDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO);

    /**
     * Business logic for deleting a clinical diagnosis
     *
     * @param clinicalDiagnosisId clinical diagnosis id
     * @return boolean operation result
     */
    boolean deleteClinicalDiagnosisById(int clinicalDiagnosisId);

    /**
     * Business logic for getting prescription details
     *
     * @param prescriptionId prescription id
     * @return PrescriptionDetailsDTO
     */
    PrescriptionDetailsDTO getPrescriptionDetails(int prescriptionId);

    /**
     * Business logic for adding a new prescription
     *
     * @param prescriptionDTO PrescriptionDTO
     * @return PrescriptionDTO
     */
    PrescriptionDTO addPrescription(PrescriptionDTO prescriptionDTO);

    /**
     * Business logic for editing a prescription
     *
     * @param prescriptionTreatmentPatternDTO PrescriptionTreatmentPatternDTO
     * @return PrescriptionShortViewDTO
     */
    PrescriptionShortViewDTO editPrescription(PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO);

    /**
     * Business logic for deleting a prescription
     *
     * @param prescriptionId prescription id
     * @return boolean operation result
     */
    boolean deletePrescription(int prescriptionId);

    /**
     * Business logic for cancelling a prescription
     *
     * @param prescriptionId prescription id
     * @return boolean operation result
     */
    boolean cancelPrescription(int prescriptionId);

    /**
     * Business logic for deleting a treatment event
     *
     * @param tEventId a treatment event id
     * @return boolean operation result
     */
    boolean deleteTreatmentEvent(int tEventId);

    /**
     * Business logic for cancelling a treatment event
     *
     * @param tEventId a treatment event id
     * @return boolean operation result
     */
    boolean cancelTreatmentEvent(int tEventId);

    /**
     * Business logic for finding all doctor's patients
     *
     * @return List of PatientShortViewDTO'
     */
    List<PatientShortViewDTO> findPatients();

    /**
     * Business logic for finding all patients
     *
     * @return List of PatientShortViewDTO'
     */
    List<PatientShortViewDTO> findAllPatients();

    /**
     * Business logic for finding all patient's prescription
     *
     * @param patientId patient id
     * @return List of PrescriptionShortViewDTO
     */
    List<PrescriptionShortViewDTO> findAllPatientsPrescription(int patientId);

    /**
     * Business logic for finding a prescription by id
     *
     * @param prescriptionId prescription id
     * @return PrescriptionTreatmentPatternDTO
     */
    PrescriptionTreatmentPatternDTO findPrescriptionById(int prescriptionId);

    /**
     * Business logic for finding a patient by surname
     *
     * @param surname patient's surname
     * @return List of PatientShortViewDTO'
     */
    List<PatientShortViewDTO> findPatientBySurname(String surname);

    /**
     * Business logic for finding treatment events by patient id
     *
     * @param id patient id
     * @return List of TreatmentEventDTO'
     */
    List<TreatmentEventDTO> findTreatmentEventsByPatientId(int id);

    /**
     * Business logic for finding a treatment event by name
     *
     * @param tEventName name of a treatment event
     * @return List of TreatmentEventDTO'
     */
    List<TreatmentEventDTO> findTreatmentEventByName(String tEventName);

    /**
     * Business logic for checking are there already other prescriptions on same date and time in database
     *
     * @param prescriptionDTO PrescriptionDTO
     * @return List of  PrescriptionShortViewDTO'
     */
    List<PrescriptionShortViewDTO> checkOtherPrescriptionsOnSameDateAndTime(PrescriptionDTO prescriptionDTO);

}
