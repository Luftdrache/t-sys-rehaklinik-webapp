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

    ClinicalDiagnosisDTO getClinicalDiagnosis(int clinicalDiagnoseId);

    MedicalRecordDTO setNewDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO, int medRecordId);

    ClinicalDiagnosisDTO editClinicalDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO);

    boolean deleteClinicalDiagnosisById(int clinicalDiagnosisId);

    PrescriptionDetailsDTO getPrescriptionDetails(int prescriptionId);

    PrescriptionDTO addPrescription(PrescriptionDTO prescriptionDTO);

    PrescriptionShortViewDTO editPrescription(PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO);

    boolean deletePrescription(int prescriptionId);

    boolean cancelPrescription(int prescriptionId);

    boolean deleteTreatmentEvent(int tEventId);

    boolean cancelTreatmentEvent(int tEventId);

    List<PatientShortViewDTO> findPatients();

    List<PatientShortViewDTO> findAllPatients();

    List<PrescriptionShortViewDTO> findAllPatientsPrescription(int patientId);

    PrescriptionTreatmentPatternDTO findPrescriptionById(int prescriptionId);

    List<PatientShortViewDTO> findPatientBySurname(String surname);

    List<TreatmentEventDTO> findTreatmentEventsByPatientId(int id);

    List<TreatmentEventDTO> findTreatmentEventByName(String tEventName);

    List<PrescriptionShortViewDTO> checkOtherPrescriptionsOnSameDateAndTime(PrescriptionDTO prescriptionDTO);

}
