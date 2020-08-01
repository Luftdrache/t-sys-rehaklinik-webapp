package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.ClinicalDiagnose;
import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.types.HospitalStayStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDTO implements Serializable {

    private int medicalRecordId;

    private Patient patient;

    private Set<ClinicalDiagnosisDTO> clinicalDiagnosis;

    @NotNull(message = "Hospital stay status must be set")
    private HospitalStayStatus hospitalStayStatus;

    private String hospitalDepartment;

    @PositiveOrZero(message = "Ward number must be positive or zero")
    private int hospitalWard;

}
