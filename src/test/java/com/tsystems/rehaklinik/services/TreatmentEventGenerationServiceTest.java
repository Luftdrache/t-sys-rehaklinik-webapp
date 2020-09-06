package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.fillers.PrescriptionFiller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TreatmentEventGenerationServiceTest {

    @InjectMocks
    private TreatmentEventGenerationServiceImpl treatmentEventGenerationService;


    @Test
    void generateTreatmentEvents_should_return_generated_treatment_events() {
        Prescription prescription = PrescriptionFiller.getPrescription();
        assertFalse(treatmentEventGenerationService.generateTreatmentEvents(prescription).isEmpty());
    }
}