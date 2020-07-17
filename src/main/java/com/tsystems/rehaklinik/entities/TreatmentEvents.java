package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "treatment_events", schema = "rehaklinik")
public class TreatmentEvents implements Serializable {
    private int treatmentEventId;
    private LocalDate treatmentEventDate;
    private LocalTime treatmentEventTime;
    private String cancelReason;
    private Patients patient;
    private Prescriptions prescription;
    private TreatmentEventStatuses treatmentEventStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_event_id", nullable = false, length = 11)
    public int getTreatmentEventId() {
        return treatmentEventId;
    }

    @Column(name = "treatment_event_date", nullable = false)
    public LocalDate getTreatmentEventDate() {
        return treatmentEventDate;
    }

    @Column(name = "treatment_event_time", nullable = false)
    public LocalTime getTreatmentEventTime() {
        return treatmentEventTime;
    }

    @Column(name = "cancel_reason", nullable = true, length = 100)
    public String getCancelReason() {
        return cancelReason;
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    public Patients getPatient() {
        return patient;
    }

    @ManyToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "prescription_id", nullable = false)
    public Prescriptions getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescriptions prescriptionsByPrescriptionId) {
        this.prescription = prescriptionsByPrescriptionId;
    }

    @ManyToOne
    @JoinColumn(name = "treatment_event_status_id", referencedColumnName = "treatment_event_status_id", nullable = false)
    public TreatmentEventStatuses getTreatmentEventStatus() {
        return treatmentEventStatus;
    }

    public void setTreatmentEventId(int treatmentEventId) {
        this.treatmentEventId = treatmentEventId;
    }

    public void setTreatmentEventDate(LocalDate treatmentEventDate) {
        this.treatmentEventDate = treatmentEventDate;
    }

    public void setTreatmentEventTime(LocalTime treatmentEventTime) { this.treatmentEventTime = treatmentEventTime; }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public void setPatient(Patients patientsByPatientId) {
        this.patient = patientsByPatientId;
    }

    public void setTreatmentEventStatus(TreatmentEventStatuses treatmentEventStatusesByTreatmentEventStatusId) {
        this.treatmentEventStatus = treatmentEventStatusesByTreatmentEventStatusId;
    }
}
