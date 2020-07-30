package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.entities.MedicalRecord;

import java.util.List;

public interface DoctorService {
    //    List<PatientDTO> patients(int doctorId);
    List<PatientDTO> patients();

    MedicalRecord getMedicalRecord(int patientId);

    MedicalRecord getMedicalRecordById(int medRecId);

    MedicalRecord updateMedicalRecord(MedicalRecord editedMedicalRecord);

    MedicalRecord setHospitalisation(MedicalRecord medicalRecord);
}
