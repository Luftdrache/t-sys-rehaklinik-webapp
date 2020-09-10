package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.TreatmentEvent;
import java.util.List;


/**
 * DAO for {@link TreatmentEvent} objects
 *
 * @author Julia Dalskaya
 */
public interface TreatmentEventDAO {

    /**
     * Creates new treatment event
     *
     * @param treatmentEvent treatment event to save
     * @return created treatment event
     */
    TreatmentEvent createTreatmentEvent(TreatmentEvent treatmentEvent);


    /**
     * Cancels selected treatment event
     *
     * @param treatmentEvent treatment event to cancel
     * @return canceled treatment event
     */
    TreatmentEvent cancelTreatmentEvent(TreatmentEvent treatmentEvent);


    /**
     * Searches for all treatment events existed in database
     *
     * @return found treatment event list
     */
    List<TreatmentEvent> findAllTreatmentEvents();


    /**
     * Searches for a planned treatment events
     *
     * @return found treatment event list
     */
    List<TreatmentEvent> findAllPlannedTreatmentEvents(); //for nurse

    /**
     * Deletes treatment event
     *
     * @param treatmentEvent treatment event to delete
     * @return operation result: true or false
     */
    boolean deleteTreatmentEvents(TreatmentEvent treatmentEvent);

    /**
     * Deletes all patient's planned treatment events when they are no longer necessary
     *
     * @param treatmentEvents treatment events list
     * @return operation result: true or false
     */
    boolean deletePrescriptionPlannedTreatmentEvents(List<TreatmentEvent> treatmentEvents);

    /**
     * Assigns 'COMPLETED' status to selected treatment event
     *
     * @param treatmentEvent treatment event
     * @return modified treatment event
     */
    TreatmentEvent setCompleted (TreatmentEvent treatmentEvent);


    /**
     * Searches for a treatment event by id
     *
     * @param treatmentEventId treatment event id
     * @return found treatment event
     */
    TreatmentEvent findTreatmentEventById(int treatmentEventId);


    /**
     * Searches for all completed treatment events
     *
     * @return all completed treatment events list
     */
    List<TreatmentEvent> findAllCompletedTreatmentEvents();


    /**
     * Searches for all urgent treatment events
     *
     * @return  all urgent treatment events list
     */
    List<TreatmentEvent> findUrgentTreatmentEvents();


    /**
     * Searches for all today treatment events
     *
     * @return all today treatment events list
     */
    List<TreatmentEvent> findTodayTreatmentEvents();


    /**
     * Searches for all treatment events of the specified patient
     *
     * @param surname patient's surname
     * @return  all patient's treatment events list
     */
    List<TreatmentEvent> findTreatmentEventsByPatientsSurname(String surname);


    /**
     * Searches for all overdue treatment events
     *
     * @return overdue treatment events list
     */
    List<TreatmentEvent> findOverdueTreatmentEvents();


    /**
     * Searches for all planned treatment events by selected prescription id
     *
     * @return planned treatment events list
     */
    List<TreatmentEvent> findPlannedTreatmentEventsByPrescriptionId(int prescriptionId);


    /**
     * Searches for all treatment events by selected prescription id
     *
     * @return planned treatment events list
     */
    List<TreatmentEvent> findAllTreatmentEventsByPrescriptionId(int prescriptionId);


    /**
     * Searches for all planned treatment events by selected patient id
     *
     * @param id patient id
     * @return found selected patient treatment events
     */
    List<TreatmentEvent> findTreatmentEventByPatientId(int id);


    /**
     * Searches for all planned treatment events by specified name
     *
     * @param tEventName treatment event name
     * @return treatment events found by specified name
     */
    List<TreatmentEvent> findTreatmentEventByName(String tEventName);
}

