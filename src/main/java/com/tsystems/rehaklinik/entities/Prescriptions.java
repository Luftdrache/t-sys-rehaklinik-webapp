package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "prescriptions", schema = "rehaklinik")
public class Prescriptions implements Serializable {
    private int prescriptionId;
    private String dose;
    private String administeringMedicationMethod;
    private LocalTime startTreatment;
    private LocalTime endTreatment;
    private Patients patient;
    private MedicinesAndProcedures medicineAndProcedure;
    private TreatmentTimePatterns treatmentTimePattern;
    private List<TreatmentEvents> treatmentEvents;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id", nullable = false, length = 11)
    public int getPrescriptionId() {
        return prescriptionId;
    }


    @Column(name = "dose", nullable = true, length = 20)
    public String getDose() {
        return dose;
    }

    @Column(name = "administering_medication_method", nullable = true, length = 30)
    public String getAdministeringMedicationMethod() {
        return administeringMedicationMethod;
    }

//    @Temporal(TemporalType.TIME)
    @Column(name = "start_treatment", nullable = false)
    public LocalTime getStartTreatment() {
        return startTreatment;
    }

//    @Temporal(TemporalType.TIME)
    @Column(name = "end_treatment", nullable = false)
    public LocalTime getEndTreatment() {
        return endTreatment;
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    public Patients getPatient() {
        return patient;
    }

    @ManyToOne
    @JoinColumn(name = "medicine_procedure_id", referencedColumnName = "medicine_procedure_id", nullable = false)
    public MedicinesAndProcedures getMedicineAndProcedure() {
        return medicineAndProcedure;
    }

    @ManyToOne
    @JoinColumn(name = "treatment_time_pattern_id", referencedColumnName = "treatment_time_pattern_id", nullable = false)
    public TreatmentTimePatterns getTreatmentTimePattern() {
        return treatmentTimePattern;
    }

    @OneToMany(mappedBy = "prescription")
    public List<TreatmentEvents> getTreatmentEvents() {
        return treatmentEvents;
    }


    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public void setAdministeringMedicationMethod(String administeringMedicationMethod) {
        this.administeringMedicationMethod = administeringMedicationMethod;
    }

    public void setStartTreatment(LocalTime startTreatment) {
        this.startTreatment = startTreatment;
    }

    public void setEndTreatment(LocalTime endTreatment) {
        this.endTreatment = endTreatment;
    }


    public void setPatient(Patients patientsByPatientId) {
        this.patient = patientsByPatientId;
    }

    public void setMedicineAndProcedure(MedicinesAndProcedures medicinesAndProceduresByMedicineProcedureId) {
        this.medicineAndProcedure = medicinesAndProceduresByMedicineProcedureId;
    }

    public void setTreatmentTimePattern(TreatmentTimePatterns treatmentTimePatternsByTreatmentTimePatternId) {
        this.treatmentTimePattern = treatmentTimePatternsByTreatmentTimePatternId;
    }

    public void setTreatmentEvents(List<TreatmentEvents> treatmentEventsByPrescriptionId) {
        this.treatmentEvents = treatmentEventsByPrescriptionId;
    }
}
