package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.dto.ClinicalDiagnosisDTO;
import com.tsystems.rehaklinik.entities.ClinicalDiagnosis;
import com.tsystems.rehaklinik.entities.MedicalRecord;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClinicalDiagnosisFiller {
    private static final int ID = 1;
    private static final String MAIN_DISEASE = "Cardiomyopathy";
    private static final String ICD_10_CODE = "I43.1";

    public static ClinicalDiagnosis getClinicalDiagnosis() {
        ClinicalDiagnosis clinicalDiagnosis = new ClinicalDiagnosis();
        clinicalDiagnosis.setClinicalDiagnosisId(ID);
        clinicalDiagnosis.setMainDisease(MAIN_DISEASE);
        clinicalDiagnosis.setIcd10Code(ICD_10_CODE);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setMedicalRecordId(1);
        clinicalDiagnosis.setMedicalRecord(medicalRecord);
        return clinicalDiagnosis;
    }

    public static ClinicalDiagnosisDTO ClinicalDiagnosisDTO() {
        ClinicalDiagnosisDTO clinicalDiagnosisDTO = new ClinicalDiagnosisDTO();
        clinicalDiagnosisDTO.setClinicalDiagnosisId(ID);
        clinicalDiagnosisDTO.setMainDisease(MAIN_DISEASE);
        clinicalDiagnosisDTO.setIcd10Code(ICD_10_CODE);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setMedicalRecordId(1);
        clinicalDiagnosisDTO.setMedicalRecord(medicalRecord);
        return clinicalDiagnosisDTO;
    }
}
