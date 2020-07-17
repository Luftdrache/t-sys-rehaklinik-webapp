package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "treatment_event_statuses", schema = "rehaklinik")
public class TreatmentEventStatuses implements Serializable {
    private int treatmentEventStatusId;
    private String treatmentEventStatus;
    private List<TreatmentEvents> treatmentEvents;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_event_status_id", nullable = false, length = 11)
    public int getTreatmentEventStatusId() {
        return treatmentEventStatusId;
    }

    @Column(name = "treatment_event_status", nullable = false, unique = true, length = 100)
    public String getTreatmentEventStatus() {
        return treatmentEventStatus;
    }


    @OneToMany(mappedBy = "treatmentEventStatus")
    public List<TreatmentEvents> getTreatmentEvents() {
        return treatmentEvents;
    }

    public void setTreatmentEventStatusId(int treatmentEventStatusId) {
        this.treatmentEventStatusId = treatmentEventStatusId;
    }

    public void setTreatmentEventStatus(String treatmentEventStatus) {
        this.treatmentEventStatus = treatmentEventStatus;
    }

    public void setTreatmentEvents(List<TreatmentEvents> treatmentEventsByTreatmentEventStatusId) {
        this.treatmentEvents = treatmentEventsByTreatmentEventStatusId;
    }
}
