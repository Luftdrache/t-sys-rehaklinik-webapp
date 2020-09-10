package com.tsystems.rehaklinik.converters.DTOconverters;

import com.tsystems.rehaklinik.dto.ClinicalDiagnosisDTO;
import com.tsystems.rehaklinik.entities.ClinicalDiagnosis;
import com.tsystems.rehaklinik.entities.MedicalRecord;


public class ClinicalDiagnoseDTOConverter {
    public static ClinicalDiagnosisDTO toDTO(ClinicalDiagnosis clinicalDiagnose) {
        ClinicalDiagnosisDTO clinicalDiagnosisDTO = new ClinicalDiagnosisDTO();
        clinicalDiagnosisDTO.setClinicalDiagnosisId(clinicalDiagnose.getClinicalDiagnosisId());
        clinicalDiagnosisDTO.setMainDisease(clinicalDiagnose.getMainDisease());
        clinicalDiagnosisDTO.setIcd10Code(clinicalDiagnose.getIcd10Code());
        clinicalDiagnosisDTO.setAccompanyingPathology(clinicalDiagnose.getAccompanyingPathology());
        clinicalDiagnosisDTO.setFullDiagnosisDescription(clinicalDiagnose.getFullDiagnosisDescription());
        clinicalDiagnosisDTO.setMedicalRecord(clinicalDiagnose.getMedicalRecord());
        return clinicalDiagnosisDTO;
    }

    public static ClinicalDiagnosis fromDTO(ClinicalDiagnosisDTO clinicalDiagnosisDTO) {
        ClinicalDiagnosis clinicalDiagnose = new ClinicalDiagnosis();
        clinicalDiagnose.setClinicalDiagnosisId(clinicalDiagnosisDTO.getClinicalDiagnosisId());
        clinicalDiagnose.setMainDisease(clinicalDiagnosisDTO.getMainDisease());
        clinicalDiagnose.setIcd10Code(clinicalDiagnosisDTO.getIcd10Code());
        clinicalDiagnose.setAccompanyingPathology(clinicalDiagnosisDTO.getAccompanyingPathology());
        clinicalDiagnose.setFullDiagnosisDescription(clinicalDiagnosisDTO.getFullDiagnosisDescription());

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setMedicalRecordId(clinicalDiagnosisDTO.getMedicalRecord().getMedicalRecordId());
        medicalRecord.setClinicalDiagnosis(clinicalDiagnosisDTO.getMedicalRecord().getClinicalDiagnosis());
        medicalRecord.setHospitalStayStatus(clinicalDiagnosisDTO.getMedicalRecord().getHospitalStayStatus());
        medicalRecord.setHospitalDepartment(clinicalDiagnosisDTO.getMedicalRecord().getHospitalDepartment());
        medicalRecord.setHospitalWard(clinicalDiagnosisDTO.getMedicalRecord().getHospitalWard());

        clinicalDiagnose.setMedicalRecord(medicalRecord);
        return clinicalDiagnose;
    }
}
