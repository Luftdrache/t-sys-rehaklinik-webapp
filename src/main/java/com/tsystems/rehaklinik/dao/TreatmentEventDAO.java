package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.TreatmentEvent;

import java.util.List;

public interface TreatmentEventDAO {

    TreatmentEvent createTreatmentEvent(TreatmentEvent treatmentEvent);

    TreatmentEvent cancelTreatmentEvent(TreatmentEvent treatmentEvent);

    List<TreatmentEvent> findAllTreatmentEvents();

    List<TreatmentEvent> findAllPlannedTreatmentEvents(); //for nurse

    boolean deleteAllPatientTreatmentEvents(int patientId);

    TreatmentEvent findTreatmentEventById(int treatmentEventId);

    List<TreatmentEvent> findAllCompletedTreatmentEvents();

    TreatmentEvent setCompleted (TreatmentEvent treatmentEvent);

    List<TreatmentEvent> findUrgentTreatmentEvents();

    List<TreatmentEvent> findTodayTreatmentEvents();

    List<TreatmentEvent> findTreatmentEventsByPatientsSurname(String surname);


}

