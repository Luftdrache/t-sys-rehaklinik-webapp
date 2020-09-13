package com.tsystems.rehaklinik.services.interfaces;

import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentEvent;

import java.util.List;

/**
 * Treatment event generation service.
 *
 * @author Julia Dalskaya
 */
public interface TreatmentEventGenerationService {

   /**
    * Generates treatment event(-s) (with status = PLANNED) immediately after a new prescription added to database
    *
    * @param prescription new prescription
    * @return List with all generated treatment events
    */
   List<TreatmentEvent> generateTreatmentEvents(Prescription prescription);
}
