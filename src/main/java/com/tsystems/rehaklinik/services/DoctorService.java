package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.*;


import java.util.List;

public interface DoctorService {

    List<PatientShortViewDTO> patients();

    MedicalRecordDTO getMedicalRecord(int patientId);

    MedicalRecordDTO setHospitalisation(MedicalRecordDTO medicalRecord);

    MedicalRecordDTO setNewDiagnosis(ClinicalDiagnosisDTO clinicalDiagnose, int medRecordId);

    boolean deleteClinicalDiagnosisById(int clinicalDiagnoseId);

    ClinicalDiagnosisDTO getClinicalDiagnosisDTO(int clinicalDiagnoseId);

    ClinicalDiagnosisDTO editClinicalDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO);

    PrescriptionDTO addPrescription(PrescriptionDTO prescriptionDTO);

    boolean deletePrescription(int prescriptionId);

    List<PrescriptionShortViewDTO> findAllPatientsPrescription(int patientId);

    PrescriptionTreatmentPatternDTO findPrescriptionById(int prescriptionId);

    PrescriptionShortViewDTO editPrescription(PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO);

    boolean cancelPrescription(int prescriptionId);

    List<PatientShortViewDTO> findPatientBySurname(String surname);

    List<TreatmentEventDTO> findTreatmentEventsByPatientId(int id);

    List<TreatmentEventDTO> findTreatmentEventByName(String tEventName);

}
