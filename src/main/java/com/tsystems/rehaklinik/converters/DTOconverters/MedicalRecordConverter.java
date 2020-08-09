package com.tsystems.rehaklinik.converters.DTOconverters;

import com.tsystems.rehaklinik.dto.MedicalRecordDTO;
import com.tsystems.rehaklinik.entities.MedicalRecord;

public class MedicalRecordConverter {

  public static MedicalRecordDTO toDTO(MedicalRecord medicalRecord) {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setMedicalRecordId(medicalRecord.getMedicalRecordId());
        medicalRecordDTO.setHospitalStayStatus(medicalRecord.getHospitalStayStatus());
        medicalRecordDTO.setHospitalDepartment(medicalRecord.getHospitalDepartment());
        medicalRecordDTO.setHospitalWard(medicalRecord.getHospitalWard());
        return medicalRecordDTO;
    }
}
