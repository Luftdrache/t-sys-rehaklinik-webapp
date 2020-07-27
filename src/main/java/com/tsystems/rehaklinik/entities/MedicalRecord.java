package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.HospitalStayStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "medical_records", schema = "rehaklinik")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_record_id", nullable = false, length = 11)
    private int medicalRecordId;

    @OneToOne(mappedBy = "medicalRecord")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "clinical_diagnosis_id", referencedColumnName = "clinical_diagnosis_id") //Eager
    private ClinicalDiagnose clinicalDiagnosis;

    @NotNull(message = "Hospital stay status must be set")
    @Enumerated(EnumType.STRING)
    @Column(name = "hospital_stay_status", columnDefinition ="ENUM ('NEW', 'BEING_TREATED', 'DISCHARGED') default 'NEW'",
            nullable = false)
    private HospitalStayStatus hospitalStayStatus;

    @Column (name = "hospital_department")
    private String hospitalDepartment;

    @PositiveOrZero(message = "Ward number must be positive or zero")
    @Column(name = "hospital_ward")
    private int hospitalWard;
}
