package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dto.TreatmentEventDTO;
import com.tsystems.rehaklinik.entities.TreatmentEvent;

import java.util.List;

public interface NurseService {

    List<TreatmentEventDTO> findAllTreatmentEvents();

    List<TreatmentEventDTO> findAllPlannedTreatmentEvents();

    List<TreatmentEventDTO> findAllCompletedTreatmentEvents();

    TreatmentEventDTO findTreatmentEventById(int treatmentEventId);

    boolean cancelTreatmentEvent(int treatmentEventId, String cancelReason);

    List<TreatmentEventDTO> getUrgentTreatmentEvents();
}
