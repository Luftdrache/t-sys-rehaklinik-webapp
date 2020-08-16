package com.tsystems.rehaklinik.dto;


import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.types.TreatmentType;
import com.tsystems.rehaklinik.entities.TreatmentTimePattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * DTO for {@link Prescription} and {@link TreatmentTimePattern} together.
 *
 * @author Julia Dalskaya
 */
@Getter
@Setter
@NoArgsConstructor
public class PrescriptionTreatmentPatternDTO {

    private int prescriptionId;

    private int medicineProcedureId;

    @NotNull(message = "Medicine or procedure name must be set")
    @NotBlank(message = "Medicine or procedure name mustn't be blank")
    private String medicineProcedureName;

    @NotNull(message = "Treatment type must be set")
    private TreatmentType treatmentType;

    private String dose;

    private String administeringMedicationMethod;

    @NotNull(message = "Start treatment date must be set")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTreatment;

    @NotNull(message = "End treatment date must be set")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTreatment;

    private int treatmentTimePatternId;

    @PositiveOrZero(message = "Interval In Hours must be null or positive")
    private int intervalInHours;

    private boolean beforeMeals;
    private boolean atMeals;
    private boolean afterMeals;

    private boolean sunday;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;

    private LocalTime precisionTime;

    private int patientId;

    public PrescriptionTreatmentPatternDTO(Prescription prescription) {
        this.prescriptionId = prescription.getPrescriptionId();
        this.medicineProcedureId = prescription.getMedicineAndProcedure().getMedicineProcedureId();
        this.medicineProcedureName = prescription.getMedicineAndProcedure().getMedicineProcedureName();
        this.treatmentType = prescription.getMedicineAndProcedure().getTreatmentType();
        this.dose = prescription.getDose();
        this.administeringMedicationMethod = prescription.getAdministeringMedicationMethod();
        this.startTreatment = prescription.getStartTreatment();
        this.endTreatment = prescription.getEndTreatment();
        this.treatmentTimePatternId =  prescription.getTreatmentTimePattern().getTreatmentTimePatternId();
        this.intervalInHours = prescription.getTreatmentTimePattern().getIntervalInHours();
        this.beforeMeals = prescription.getTreatmentTimePattern().isBeforeMeals();
        this.atMeals = prescription.getTreatmentTimePattern().isAtMeals();
        this.afterMeals = prescription.getTreatmentTimePattern().isAfterMeals();
        this.sunday = prescription.getTreatmentTimePattern().isSunday();
        this.monday =prescription.getTreatmentTimePattern().isMonday() ;
        this.tuesday = prescription.getTreatmentTimePattern().isTuesday();
        this.wednesday = prescription.getTreatmentTimePattern().isWednesday();
        this.thursday = prescription.getTreatmentTimePattern().isThursday();
        this.friday =prescription.getTreatmentTimePattern().isFriday();
        this.saturday = prescription.getTreatmentTimePattern().isSaturday();
        this.precisionTime = prescription.getTreatmentTimePattern().getPrecisionTime();
        this.patientId = prescription.getPatient().getPatientId();
    }
}
