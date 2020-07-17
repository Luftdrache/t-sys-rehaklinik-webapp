package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "clinical_diagnoses", schema = "rehaklinik")
public class ClinicalDiagnoses implements Serializable {
    private int clinicalDiagnosisId;
    private String mainDisease;
    private String icd10Code;
    private String accompanyingPathology;
    private String fullDiagnosisDescription;
    private List<MedicalRecords> medicalRecords;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinical_diagnosis_id", nullable = false, length = 11)
    public int getClinicalDiagnosisId() {
        return clinicalDiagnosisId;
    }

    @Column(name = "main_disease", nullable = false, length = 50)
    public String getMainDisease() {
        return mainDisease;
    }

    @Column(name = "ICD_10_code", nullable = false, length = 5)
    public String getIcd10Code() {
        return icd10Code;
    }

    @Column(name = "accompanying_pathology", nullable = true, length = 50)
    public String getAccompanyingPathology() {
        return accompanyingPathology;
    }

    @Lob
    @Column(name = "full_diagnosis_description", nullable = true)
    public String getFullDiagnosisDescription() {
        return fullDiagnosisDescription;
    }

    @OneToMany(mappedBy = "clinicalDiagnosis")
    public List<MedicalRecords> getMedicalRecords() {
        return medicalRecords;
    }

    public void setClinicalDiagnosisId(int clinicalDiagnosisId) {
        this.clinicalDiagnosisId = clinicalDiagnosisId;
    }

    public void setMainDisease(String mainDisease) {
        this.mainDisease = mainDisease;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public void setAccompanyingPathology(String accompanyingPathology) {
        this.accompanyingPathology = accompanyingPathology;
    }

    public void setFullDiagnosisDescription(String fullDiagnosisDescription) {
        this.fullDiagnosisDescription = fullDiagnosisDescription;
    }

    public void setMedicalRecords(List<MedicalRecords> medicalRecordsByClinicalDiagnosisId) {
        this.medicalRecords = medicalRecordsByClinicalDiagnosisId;
    }

}
