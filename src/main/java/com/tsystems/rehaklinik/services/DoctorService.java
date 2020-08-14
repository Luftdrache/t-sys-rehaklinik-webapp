package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.*;


import java.util.List;

/**
 * Doctor service.
 *
 * @author Julia Dalskaya
 */
public interface DoctorService {

    MedicalRecordDTO getMedicalRecord(int patientId);

    MedicalRecordDTO setHospitalisation(MedicalRecordDTO medicalRecord);

    MedicalRecordDTO setNewDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO, int medRecordId);

    boolean deleteClinicalDiagnosisById(int clinicalDiagnosisId);

    ClinicalDiagnosisDTO getClinicalDiagnosis(int clinicalDiagnoseId);

    ClinicalDiagnosisDTO editClinicalDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO);

    PrescriptionDTO addPrescription(PrescriptionDTO prescriptionDTO);

    PrescriptionShortViewDTO editPrescription(PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO);

    boolean deletePrescription(int prescriptionId);

    boolean cancelPrescription(int prescriptionId);

    boolean deleteTreatmentEvent(int tEventId);

    PrescriptionDetailsDTO getPrescriptionDetails(int prescriptionId);

    List<PatientShortViewDTO> findPatients();

    List<PrescriptionShortViewDTO> findAllPatientsPrescription(int patientId);

    PrescriptionTreatmentPatternDTO findPrescriptionById(int prescriptionId);

    List<PatientShortViewDTO> findPatientBySurname(String surname);

    List<TreatmentEventDTO> findTreatmentEventsByPatientId(int id);

    List<TreatmentEventDTO> findTreatmentEventByName(String tEventName);

    boolean cancelTreatmentEvent(int tEventId);

}
