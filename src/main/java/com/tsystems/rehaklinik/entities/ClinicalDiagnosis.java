package com.tsystems.rehaklinik.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clinical diagnosis entity
 */
@Entity
@Table(name = "clinical_diagnoses", schema = "rehaklinik",
        uniqueConstraints = @UniqueConstraint(columnNames = {"main_disease", "ICD_10_code", "medical_record_id"},
                name = "UNQ_MEDICINE_PROCEDURE_NAME"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalDiagnosis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinical_diagnosis_id", nullable = false, length = 11)
    private int clinicalDiagnosisId;

    @Column(name = "main_disease", nullable = false)
    private String mainDisease;

    @Column(name = "ICD_10_code", nullable = false, length = 5)
    private String icd10Code;

    @Column(name = "accompanying_pathology")
    private String accompanyingPathology;

    @Lob
    @Column(name = "full_diagnosis_description")
    private String fullDiagnosisDescription;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "medical_record_id", referencedColumnName = "medical_record_id")
    private MedicalRecord medicalRecord;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClinicalDiagnosis that = (ClinicalDiagnosis) o;
        return icd10Code.equals(that.icd10Code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icd10Code);
    }
}
