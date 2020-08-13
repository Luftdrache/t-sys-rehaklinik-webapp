package com.tsystems.rehaklinik.dto;


import com.tsystems.rehaklinik.entities.MedicalRecord;
import com.tsystems.rehaklinik.types.HospitalStayStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Set;


/**
 * DTO for {@link MedicalRecord} objects
 *
 * @author Julia Dalskaya
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDTO implements Serializable {

    private int medicalRecordId;

    private PatientDTO patient;

    private Set<ClinicalDiagnosisDTO> clinicalDiagnosis;

    @NotNull(message = "Hospital stay status must be set")
    private HospitalStayStatus hospitalStayStatus;

    private String hospitalDepartment;

    @PositiveOrZero(message = "Ward number must be positive or zero")
    private int hospitalWard;

}
