package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.ClinicalDiagnosisDTO;
import com.tsystems.rehaklinik.dto.MedicalRecordDTO;
import com.tsystems.rehaklinik.dto.PatientShortViewDTO;
import com.tsystems.rehaklinik.entities.ClinicalDiagnose;
import com.tsystems.rehaklinik.entities.MedicalRecord;
import com.tsystems.rehaklinik.entities.Prescription;

import java.util.List;

public interface DoctorService {
    //    List<PatientDTO> patients(int doctorId);
    List<PatientShortViewDTO> patients();

    MedicalRecord getMedicalRecord(int patientId);

    MedicalRecord getMedicalRecordById(int medRecId);

    MedicalRecord updateMedicalRecord(MedicalRecord editedMedicalRecord);

    MedicalRecord setHospitalisation(MedicalRecord medicalRecord);

    MedicalRecordDTO setNewDiagnosis(ClinicalDiagnosisDTO clinicalDiagnose, int medRecordId);

    Prescription addPrescription(Prescription prescription);
}
