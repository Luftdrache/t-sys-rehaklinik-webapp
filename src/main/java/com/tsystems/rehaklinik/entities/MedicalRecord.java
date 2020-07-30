package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.HospitalStayStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<ClinicalDiagnose> clinicalDiagnosis;

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

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "medicalRecordId=" + medicalRecordId +
                ", patient=" + patient +
                ", clinicalDiagnosis=" + clinicalDiagnosis +
                ", hospitalStayStatus=" + hospitalStayStatus +
                ", hospitalDepartment='" + hospitalDepartment + '\'' +
                ", hospitalWard=" + hospitalWard +
                '}';
    }
}
