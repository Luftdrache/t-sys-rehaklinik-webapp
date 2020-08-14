package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.types.EventStatus;
import com.tsystems.rehaklinik.types.TreatmentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


/**
 * DTO for {@link TreatmentEvent} objects
 *
 * @author Julia Dalskaya
 */
@Getter
@Setter
@NoArgsConstructor
public class TreatmentEventDTO {
    private int treatmentEventId;
    private int patientId;
    private String patient;
    private String hospitalDepartment;
    private String hospitalWard;
    private String treatmentEventDate;
    private LocalTime treatmentEventTime;
    private String medicineProcedureName;
    private TreatmentType treatmentType;
    private String dose;
    private String administeringMedicationMethod;
    private String cancelReason;
    private EventStatus treatmentEventStatus;
    private String treatmentPeriodStartDate;
    private String treatmentPeriodEndDate;


    private static final String LONG_DASH = "&mdash;";
    private static final String WARD_NUMBER = "0";

    public TreatmentEventDTO(TreatmentEvent treatmentEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("en"));

        this.treatmentEventId = treatmentEvent.getTreatmentEventId();
        this.patientId = treatmentEvent.getPatient().getPatientId();
        this.patient = treatmentEvent.getPatient().getSurname() + ", "
                + treatmentEvent.getPatient().getFirstName() + " "
                + treatmentEvent.getPatient().getMiddleName();
        this.hospitalDepartment = treatmentEvent.getPrescription().getPatient().getMedicalRecord().getHospitalDepartment();
        if (this.hospitalDepartment == null || this.hospitalDepartment.equals("")) this.hospitalDepartment = LONG_DASH;
        this.hospitalWard = Integer.toString(treatmentEvent.getPrescription().getPatient().getMedicalRecord().getHospitalWard());
        if (this.hospitalWard.equals("") || this.hospitalWard.equals(WARD_NUMBER)) this.hospitalWard = LONG_DASH;
        this.treatmentEventDate = formatter.format(treatmentEvent.getTreatmentEventDate());
        this.treatmentEventTime = treatmentEvent.getTreatmentEventTime();
        this.medicineProcedureName = treatmentEvent.getPrescription().getMedicineAndProcedure().getMedicineProcedureName();
        this.treatmentType = treatmentEvent.getPrescription().getMedicineAndProcedure().getTreatmentType();
        this.dose = treatmentEvent.getPrescription().getDose();
        if (this.dose.equals("")) this.dose = LONG_DASH;
        this.administeringMedicationMethod = treatmentEvent.getPrescription().getAdministeringMedicationMethod();
        if (this.administeringMedicationMethod.equals("")) this.administeringMedicationMethod = LONG_DASH;
        this.cancelReason = treatmentEvent.getCancelReason();
        this.treatmentEventStatus = treatmentEvent.getTreatmentEventStatus();
        this.treatmentPeriodStartDate = formatter.format(treatmentEvent.getPrescription().getStartTreatment());
        this.treatmentPeriodEndDate = formatter.format(treatmentEvent.getPrescription().getEndTreatment());
    }
}
