package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.*;
import com.tsystems.rehaklinik.entities.MedicalRecord;


import java.util.List;

public interface DoctorService {
    //    List<PatientDTO> patients(int doctorId);
    List<PatientShortViewDTO> patients();

    MedicalRecord getMedicalRecord(int patientId);

    MedicalRecord updateMedicalRecord(MedicalRecord editedMedicalRecord);

    MedicalRecord setHospitalisation(MedicalRecord medicalRecord);

    MedicalRecordDTO setNewDiagnosis(ClinicalDiagnosisDTO clinicalDiagnose, int medRecordId);

    PrescriptionDTO addPrescription(PrescriptionDTO prescriptionDTO);

    boolean deletePrescription(int prescriptionId);

    List<PrescriptionShortViewDTO> findAllPatientsPrescription(int patientId);

    PrescriptionTreatmentPatternDTO findPrescriptionById(int prescriptionId);

    PrescriptionShortViewDTO editPrescription(PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO);
}
