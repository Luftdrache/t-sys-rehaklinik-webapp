package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentTimePattern;

public class TreatmentTimePatternFiller {
    private static final int ID = 1;

    public static TreatmentTimePattern getPTreatmentTimePattern() {
        TreatmentTimePattern treatmentTimePattern = new TreatmentTimePattern();
        treatmentTimePattern.setTreatmentTimePatternId(ID);
        return treatmentTimePattern;
    }
}
