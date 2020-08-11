package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.TreatmentEvent;

import java.util.List;

public interface TreatmentEventDAO {

    /**
     * Creates new treatment event
     *
     * @param treatmentEvent treatment event to save
     * @return created treatment event
     */
    TreatmentEvent createTreatmentEvent(TreatmentEvent treatmentEvent);


    /**
     * Cansels selected treatment event
     *
     * @param treatmentEvent treatment event to cancel
     * @return canceled treatment event
     */
    TreatmentEvent cancelTreatmentEvent(TreatmentEvent treatmentEvent);


    /**
     * Finds all treatment events existed in database
     *
     * @return found treatment event list
     */
    List<TreatmentEvent> findAllTreatmentEvents();


    /**
     * Finds planned treatment events
     *
     * @return found treatment event list
     */
    List<TreatmentEvent> findAllPlannedTreatmentEvents(); //for nurse


    /**
     * Deletes all patient's planned treatment events when they are no longer necessary
     *
     * @param treatmentEvents treatment events list
     * @return operation result: true or false
     */
    boolean deletePrescriptionPlannedTreatmentEvents(List<TreatmentEvent> treatmentEvents);


    /**
     * Finds treatment event by id
     *
     * @param treatmentEventId treatment event id
     * @return found treatment event
     */
    TreatmentEvent findTreatmentEventById(int treatmentEventId);


    /**
     * Finds all completed treatment events
     *
     * @return all completed treatment events list
     */
    List<TreatmentEvent> findAllCompletedTreatmentEvents();


    /**
     * Assigns 'COMPLETED' status to selected treatment event
     *
     * @param treatmentEvent treatment event
     * @return modified treatment event
     */
    TreatmentEvent setCompleted (TreatmentEvent treatmentEvent);


    /**
     * Finds all urgent treatment events
     *
     * @return  all urgent treatment events list
     */
    List<TreatmentEvent> findUrgentTreatmentEvents();


    /**
     * Finds all today treatment events
     *
     * @return all today treatment events list
     */
    List<TreatmentEvent> findTodayTreatmentEvents();


    /**
     * Finds all treatment events of the specified patient
     *
     * @param surname patient's surname
     * @return  all patient's treatment events list
     */
    List<TreatmentEvent> findTreatmentEventsByPatientsSurname(String surname);


    /**
     * Finds all overdue treatment events
     *
     * @return overdue treatment events list
     */
    List<TreatmentEvent> findOverdueTreatmentEvents();


    /**
     * Finds all planned treatment events by selected prescription id
     *
     * @return planned treatment events list
     */
    List<TreatmentEvent> findPlannedTreatmentEventsByPrescriptionId(int PrescriptionId);


    /**
     * Finds all planned treatment events by selected patient id
     *
     * @param id patient id
     * @return found selected patient treatment events
     */
    List<TreatmentEvent> findTreatmentEventByPatientId(int id);


    /**
     * Finds all planned treatment events by specified name
     *
     * @param tEventName treatment event name
     * @return treatment events found by specified name
     */
    List<TreatmentEvent> findTreatmentEventByName(String tEventName);
}

