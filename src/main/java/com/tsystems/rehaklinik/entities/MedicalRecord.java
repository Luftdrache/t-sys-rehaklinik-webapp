package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.HospitalStayStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

    @NotNull(message = "Hospital stay status must be set")
    @Enumerated(EnumType.STRING)
    @Column(name = "hospital_stay_status", nullable = false)
    private HospitalStayStatus hospitalStayStatus;

    @NotNull(message = "Hospital warn number must be set")
    @Positive(message = "Ward number must be positive")
    @Column(name = "hospital_ward", nullable = false)
    private int hospitalWard;

    @ManyToOne
    @JoinColumn(name = "clinical_diagnosis_id", referencedColumnName = "clinical_diagnosis_id")
    private ClinicalDiagnose clinicalDiagnosis;

    @OneToMany(mappedBy = "medicalRecord")
    private List<Patient> patients;

}
