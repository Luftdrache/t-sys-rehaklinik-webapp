package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.TreatmentEventDTO;

import java.util.List;

/**
 * Nurse service.
 *
 * @author Julia Dalskaya
 */
public interface NurseService {

    List<TreatmentEventDTO> findAllTreatmentEvents();

    List<TreatmentEventDTO> findAllPlannedTreatmentEvents();

    List<TreatmentEventDTO> findAllCompletedTreatmentEvents();

    TreatmentEventDTO findTreatmentEventById(int treatmentEventId);

    boolean cancelTreatmentEvent(int treatmentEventId, String cancelReason);

    boolean setTreatmentEventToCompleted(int treatmentEventId);

    List<TreatmentEventDTO> getUrgentTreatmentEvents();

    List<TreatmentEventDTO> getTodayTreatmentEvents();

    List<TreatmentEventDTO> findTreatmentEventsByPatientsSurname(String surname);

    List<TreatmentEventDTO> findOverdueTreatmentEvents();
}
