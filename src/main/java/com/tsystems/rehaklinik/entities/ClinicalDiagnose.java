package com.tsystems.rehaklinik.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "clinical_diagnoses", schema = "rehaklinik")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalDiagnose implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinical_diagnosis_id", nullable = false, length = 11)
    private int clinicalDiagnosisId;

    @NotNull(message = "Name of the disease must be set")
    @NotBlank(message = "Name of the disease mustn't be blank")
    @Column(name = "main_disease", nullable = false, length = 50)
    private String mainDisease;

    @NotNull(message = "ICD-10 code must be set")
    @Size(min = 3, max = 8, message = "ICD-10 code must have minimum 3 and maximum 8 symbols")
    //For test. Wrong: B274.77","U31","567.". Right:B22.Z154, D17
    @Pattern(regexp ="[A-TV-Z][0-9][0-9AB]\\.?[0-9A-TV-Z]{0,4}", message = "Wrong ICD-10 code")
    @Column(name = "ICD_10_code", nullable = false, length = 5)
    private String icd10Code;

    @Column(name = "accompanying_pathology", length = 50)
    private String accompanyingPathology;

    @Lob
    @Column(name = "full_diagnosis_description")
    private String fullDiagnosisDescription;

    @OneToMany(mappedBy = "clinicalDiagnosis")
    private List<MedicalRecord> medicalRecords;

}
