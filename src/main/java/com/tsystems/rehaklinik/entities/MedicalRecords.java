package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "medical_records", schema = "rehaklinik")
public class MedicalRecords implements Serializable{
    private int medicalRecordId;
    private String hospitalStayStatus;
    private int hospitalWard;
    private ClinicalDiagnoses clinicalDiagnosis;
    private List<Patients> patients;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_record_id", nullable = false, length = 11)
    public int getMedicalRecordId() {
        return medicalRecordId;
    }

    @Column(name = "hospital_stay_status", nullable = false, length = 15)
    public String getHospitalStayStatus() {
        return hospitalStayStatus;
    }

    @Column(name = "hospital_ward", nullable = false)
    public int getHospitalWard() {
        return hospitalWard;
    }

    @ManyToOne
    @JoinColumn(name = "clinical_diagnosis_id", referencedColumnName = "clinical_diagnosis_id")
    public ClinicalDiagnoses getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    @OneToMany(mappedBy = "medicalRecord")
    public List<Patients> getPatients() {
        return patients;
    }

    public void setMedicalRecordId(int medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public void setHospitalStayStatus(String hospitalStayStatus) {
        this.hospitalStayStatus = hospitalStayStatus;
    }

    public void setHospitalWard(int hospitalWard) {
        this.hospitalWard = hospitalWard;
    }

    public void setClinicalDiagnosis(ClinicalDiagnoses clinicalDiagnosesByClinicalDiagnosisId) {
        this.clinicalDiagnosis = clinicalDiagnosesByClinicalDiagnosisId;
    }

    public void setPatients(List<Patients> patientsByMedicalRecordId) {
        this.patients = patientsByMedicalRecordId;
    }
}
