package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.HospitalStayStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * Medical record entity
 */
@Entity
@Table(name = "medical_records", schema = "rehaklinik")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_record_id", nullable = false, length = 11)
    private int medicalRecordId;

    @OneToOne(mappedBy = "medicalRecord")
    private Patient patient;

    @OneToMany (mappedBy = "medicalRecord", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ClinicalDiagnosis> clinicalDiagnosis;

    @Enumerated(EnumType.STRING)
    @Column(name = "hospital_stay_status", columnDefinition ="ENUM ('NEW', 'BEING_TREATED', 'DISCHARGED') default 'NEW'",
            nullable = false)
    private HospitalStayStatus hospitalStayStatus;

    @Column (name = "hospital_department")
    private String hospitalDepartment;

    @Column(name = "hospital_ward")
    private int hospitalWard;
}
