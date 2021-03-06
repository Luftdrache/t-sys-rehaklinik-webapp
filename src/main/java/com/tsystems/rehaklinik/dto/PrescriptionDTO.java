package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.types.PrescriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


/**
 * DTO for {@link Prescription} objects
 *
 * @author Julia Dalskaya
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDTO {
    private int prescriptionId;

    @NotNull(message = "Medicine or procedure must be set (for the prescription)")
    private MedicineAndProcedureDTO medicineAndProcedure;

    private String dose;

    private String administeringMedicationMethod;

    @NotNull(message = "Start treatment date must be set")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTreatment;

    @NotNull(message = "End treatment date must be set")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTreatment;

    private PrescriptionStatus prescriptionStatus;

    @NotNull (message = "Patient must be set (for the prescription)")
    private PatientDTO patient;

    @NotNull (message = "Treatment Time Pattern must be set (for the prescription)")
    private TreatmentTimePatternDTO treatmentTimePattern;

    private List<TreatmentEvent> treatmentEvents;
}
