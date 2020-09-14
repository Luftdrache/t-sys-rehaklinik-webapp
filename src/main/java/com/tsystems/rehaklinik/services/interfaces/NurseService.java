package com.tsystems.rehaklinik.services.interfaces;

import com.tsystems.rehaklinik.dto.TreatmentEventDTO;

import java.util.List;

/**
 * Nurse service.
 *
 * @author Julia Dalskaya
 */
public interface NurseService {

    /**
     * Business logic for finding all treatment events
     *
     * @return List of TreatmentEventDTO'
     */
    List<TreatmentEventDTO> findAllTreatmentEvents();

    /**
     * Business logic for finding all planned treatment events
     *
     * @return List of TreatmentEventDTO'
     */
    List<TreatmentEventDTO> findAllPlannedTreatmentEvents();

    /**
     * Business logic for finding all completed treatment events
     *
     * @return List of TreatmentEventDTO'
     */
    List<TreatmentEventDTO> findAllCompletedTreatmentEvents();

    /**
     * Business logic for finding a treatment event by id
     *
     * @param treatmentEventId treatment event id
     * @return TreatmentEventDTO
     */
    TreatmentEventDTO findTreatmentEventById(int treatmentEventId);

    /**
     * Business logic for cancelling a treatment event
     *
     * @param treatmentEventId treatment event id
     * @param cancelReason     cancel reason
     * @return boolean operation result
     */
    boolean cancelTreatmentEvent(int treatmentEventId, String cancelReason);

    /**
     * Business logic for set a treatment event as completed
     *
     * @param treatmentEventId treatment event id
     * @return boolean operation result
     */
    boolean setTreatmentEventToCompleted(int treatmentEventId);

    /**
     * Business logic for getting urgent treatment events
     *
     * @return List of TreatmentEventDTO'
     */
    List<TreatmentEventDTO> getUrgentTreatmentEvents();

    /**
     * Business logic for getting today treatment events
     *
     * @return List of TreatmentEventDTO'
     */
    List<TreatmentEventDTO> getTodayTreatmentEvents();

    /**
     * Business logic for finding treatment events by patient's surname
     *
     * @param surname patient's surname
     * @return List of TreatmentEventDTO'
     */
    List<TreatmentEventDTO> findTreatmentEventsByPatientsSurname(String surname);

    /**
     * Business logic for
     *
     * @return List of TreatmentEventDTO'
     */
    List<TreatmentEventDTO> findOverdueTreatmentEvents();
}
