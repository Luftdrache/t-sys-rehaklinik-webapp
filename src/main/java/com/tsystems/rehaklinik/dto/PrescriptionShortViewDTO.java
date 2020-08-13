package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.types.PrescriptionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.Locale;


/**
 * DTO for {@link Prescription} objects. It is a shortened representation of an object.
 *
 * @author Julia Dalskaya
 */
@Getter
@Setter
@NoArgsConstructor
public class PrescriptionShortViewDTO {
    private int prescriptionId;
    private String name;
    private String treatmentType;
    private String dose;
    private String administeringMedicationMethod;
    private String startTreatment;
    private String endTreatment;
    private PrescriptionStatus prescriptionStatus;


    public PrescriptionShortViewDTO(Prescription prescription) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("en"));

        this.prescriptionId = prescription.getPrescriptionId();
        this.name = prescription.getMedicineAndProcedure().getMedicineProcedureName();
        this.treatmentType = prescription.getMedicineAndProcedure().getTreatmentType().toString();
        this.dose = prescription.getDose();
        this.administeringMedicationMethod = prescription.getAdministeringMedicationMethod();
        this.startTreatment = formatter.format(prescription.getStartTreatment());
        this.endTreatment = formatter.format(prescription.getEndTreatment());
        this.prescriptionStatus = prescription.getPrescriptionStatus();
    }
}
