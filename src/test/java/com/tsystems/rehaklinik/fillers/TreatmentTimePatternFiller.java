package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.dto.TreatmentTimePatternDTO;
import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentTimePattern;

import java.time.LocalTime;

public class TreatmentTimePatternFiller {
    private static final int ID = 1;
    private static final boolean IS_DAY = true;
    private static final LocalTime PRECISION_TIME = LocalTime.of(7, 0, 0);

    public static TreatmentTimePattern getPTreatmentTimePattern() {
        TreatmentTimePattern treatmentTimePattern = new TreatmentTimePattern();
        treatmentTimePattern.setTreatmentTimePatternId(ID);

        treatmentTimePattern.setPrecisionTime(PRECISION_TIME);

        treatmentTimePattern.setMonday(IS_DAY);
        treatmentTimePattern.setTuesday(IS_DAY);
        treatmentTimePattern.setWednesday(IS_DAY);
        treatmentTimePattern.setThursday(IS_DAY);
        treatmentTimePattern.setFriday(IS_DAY);
        treatmentTimePattern.setSaturday(IS_DAY);
        treatmentTimePattern.setSunday(IS_DAY);

        return treatmentTimePattern;
    }

    public static TreatmentTimePatternDTO getPTreatmentTimePatternDTO() {
        TreatmentTimePatternDTO treatmentTimePatternDTO = new TreatmentTimePatternDTO();
        treatmentTimePatternDTO.setTreatmentTimePatternId(ID);
        return treatmentTimePatternDTO;
    }
}
