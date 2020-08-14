package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.types.TreatmentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
public class PrescriptionDetailsDTO {
    private int prescriptionId;
    private int medicineProcedureId;
    private String medicineProcedureName;
    private String treatmentType;
    private String dose;
    private String administeringMedicationMethod;
    private String startTreatment;
    private String endTreatment;
    private int intervalInHours;
    private String beforeMeals;
    private String atMeals;
    private String afterMeals;
    private String sunday;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private LocalTime precisionTime;

    private static final String LONG_DASH = "&mdash;";
    private static final LocalTime DEFAULT_TIME = LocalTime.of(07, 00);

    public PrescriptionDetailsDTO(PrescriptionTreatmentPatternDTO prescription) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("en"));
        String doseInfo = prescription.getDose();
        String method = prescription.getAdministeringMedicationMethod();

        this.prescriptionId = prescription.getPrescriptionId();
        this.medicineProcedureId = prescription.getMedicineProcedureId();
        this.medicineProcedureName = prescription.getMedicineProcedureName();
        this.treatmentType = prescription.getTreatmentType().toString();
        this.dose = doseInfo == null || doseInfo.isEmpty() ? LONG_DASH : doseInfo;
        this.administeringMedicationMethod = method == null || method.isEmpty() ? LONG_DASH : method;

        if (prescription.getStartTreatment() != null) {
            this.startTreatment = formatter.format(prescription.getStartTreatment());
        }
        if (prescription.getEndTreatment() != null) {
            this.endTreatment = formatter.format(prescription.getEndTreatment());
        }
        if (prescription.getPrecisionTime() != null) {
            this.precisionTime = prescription.getPrecisionTime();
        } else {
            this.precisionTime = DEFAULT_TIME;
        }
        this.intervalInHours = prescription.getIntervalInHours();

        if (prescription.isMonday()) {
            this.monday = "Monday";
        }
        if (prescription.isTuesday()) {
            this.tuesday = "Tuesday";
        }
        if (prescription.isWednesday()) {
            this.wednesday = "Wednesday";
        }
        if (prescription.isThursday()) {
            this.thursday = "Thursday";
        }
        if (prescription.isFriday()) {
            this.friday = "Friday";
        }
        if (prescription.isSaturday()) {
            this.saturday = "Saturday";
        }
        if (prescription.isSunday()) {
            this.sunday = "Sunday";
        }

        if (prescription.isBeforeMeals()) {
            this.beforeMeals = "Before Meals";
        }
        if (prescription.isAtMeals()) {
            this.atMeals= "At Meals";
        }
        if (prescription.isAfterMeals()) {
            this.afterMeals = "After Meals";
        }
    }
}
