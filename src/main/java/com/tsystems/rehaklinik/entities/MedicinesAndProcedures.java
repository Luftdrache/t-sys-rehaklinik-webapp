package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "medicines_and_procedures", schema = "rehaklinik")
public class MedicinesAndProcedures implements Serializable {
    private int medicineProcedureId;
    private String medicineProcedureName;
    private String treatmentType;
    private List<Prescriptions> prescriptions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_procedure_id", nullable = false, length = 11)
    public int getMedicineProcedureId() {
        return medicineProcedureId;
    }

    @Column(name = "medicine_procedure_name", nullable = false, unique = true, length = 100)
    public String getMedicineProcedureName() {
        return medicineProcedureName;
    }

    @Column(name = "treatment_type", nullable = false, length = 9)
    public String getTreatmentType() {
        return treatmentType;
    }

    @OneToMany(mappedBy = "medicineAndProcedure")
    public List<Prescriptions> getPrescriptions() {
        return prescriptions;
    }

    public void setMedicineProcedureId(int medicineProcedureId) {
        this.medicineProcedureId = medicineProcedureId;
    }

    public void setMedicineProcedureName(String medicineProcedureName) {
        this.medicineProcedureName = medicineProcedureName;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public void setPrescriptions(List<Prescriptions> prescriptionsByMedicineProcedureId) {
        this.prescriptions = prescriptionsByMedicineProcedureId;
    }
}
