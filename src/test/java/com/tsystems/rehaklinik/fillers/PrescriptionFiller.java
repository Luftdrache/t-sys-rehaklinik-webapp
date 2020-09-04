package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentEvent;

import java.time.LocalDate;

public class PrescriptionFiller {
    private static final int ID = 1;
    private static final LocalDate START = LocalDate.of(2020, 11, 20);
    private static final LocalDate END = LocalDate.of(2020, 12, 1);
    private static final String DOSE = "50 mg";
    private static final String METHOD = "";


    public static Prescription getPrescription() {
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(ID);
        prescription.setMedicineAndProcedure(MedicineAndProcedureFiller.getMedicineAndProcedure());
        prescription.setDose(DOSE);
        prescription.setAdministeringMedicationMethod(METHOD);
        prescription.setPatient(PatientFiller.getPatient());
        prescription.setStartTreatment(START);
        prescription.setEndTreatment(END);
        prescription.setTreatmentTimePattern(TreatmentTimePatternFiller.getPTreatmentTimePattern());
        return prescription;
    }
}
