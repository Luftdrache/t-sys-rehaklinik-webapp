package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.MedicalRecord;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalDiagnosisDTO implements Serializable {
    private int clinicalDiagnosisId;

    @NotNull(message = "Name of the disease must be set")
    @NotBlank(message = "Name of the disease mustn't be blank")
    private String mainDisease;

    @NotNull(message = "ICD-10 code must be set")
    @Size(min = 3, max = 8, message = "ICD-10 code must have minimum 3 and maximum 8 symbols")
    @Pattern(regexp ="[A-TV-Z][0-9][0-9AB]\\.?[0-9A-TV-Z]{0,4}", message = "Wrong ICD-10 code") //For test. Wrong: B274.77","U31","567.". Right:B22.Z154, D17
    private String icd10Code;

    private String accompanyingPathology;

    private String fullDiagnosisDescription;

    private MedicalRecord medicalRecord;
}
