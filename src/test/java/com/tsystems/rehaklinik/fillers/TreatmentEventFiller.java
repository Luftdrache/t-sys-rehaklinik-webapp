package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.dto.MedicalRecordDTO;
import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.types.EventStatus;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
public class TreatmentEventFiller {
    private static final int ID = 1;
    private static final LocalDate DATE = LocalDate.of(2020, 8, 30);
    private static final LocalTime TIME = LocalTime.of(12, 00, 00);
    private static final EventStatus STATUS = EventStatus.PLANNED;

    public static TreatmentEvent getTreatmentEvent() {
        TreatmentEvent treatmentEvent = new TreatmentEvent();
        treatmentEvent.setTreatmentEventId(ID);
        treatmentEvent.setTreatmentEventDate(DATE);
        treatmentEvent.setTreatmentEventTime(TIME);
        treatmentEvent.setTreatmentEventStatus(STATUS);
        treatmentEvent.setPatient(PatientFiller.getPatient());
        treatmentEvent.setPrescription(PrescriptionFiller.getPrescription());
        return treatmentEvent;
    }


}
