package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.MedicineAndProcedure;
import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.entities.TreatmentTimePattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDTO {
    private int prescriptionId;

    @NotNull(message = "Medicine or procedure must be set (for the prescription)")
    private MedicineAndProcedureDTO medicineAndProcedureDTO;

    private String dose;

    private String administeringMedicationMethod;

    @NotNull(message = "Start treatment date must be set")
    private LocalDate startTreatment;

    @Future(message = "End treatment date must be in future")
    @NotNull(message = "End treatment date must be set")
    private LocalDate endTreatment;

    @NotNull (message = "Patient must be set (for the prescription)")
    private PatientDTO patientDTO;

    @NotNull (message = "Treatment Time Pattern must be set (for the prescription)")
    private TreatmentTimePatternDTO treatmentTimePattern;

    private List<TreatmentEvent> treatmentEvents;
}