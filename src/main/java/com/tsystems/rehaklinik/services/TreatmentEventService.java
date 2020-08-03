package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface TreatmentEventService {
   List<TreatmentEvent> generateTreatmentEvents(Prescription prescription);
}
