package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface TreatmentEventGenerationService {

   /**
    * Generates treatment event(-s) (with status = PLANNED) immediately after a new prescription added to database
    *
    * @param prescription new prescription
    * @return List with all generated treatment events
    */
   List<TreatmentEvent> generateTreatmentEvents(Prescription prescription);
}
