package com.tsystems.rehaklinik.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "prescriptions", schema = "rehaklinik")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id", nullable = false, length = 11)
    private int prescriptionId;

    @NotNull(message = "Medicine or procedure must be set (for the prescription)")
    @ManyToOne
    @JoinColumn(name = "medicine_procedure_id", referencedColumnName = "medicine_procedure_id", nullable = false)
    private MedicineAndProcedure medicineAndProcedure;

    @Column(name = "dose", length = 20)
    private String dose;

    @Column(name = "administering_medication_method", length = 30)
    private String administeringMedicationMethod;

    @NotNull(message = "Start treatment date and time must be set")
    @Column(name = "start_treatment", nullable = false)
    private LocalDateTime startTreatment;

    @NotNull(message = "End treatment date and time must be set")
    @Column(name = "end_treatment", nullable = false)
    private LocalDateTime endTreatment;

    @NotNull (message = "Patient must be set (for the prescription)")
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    private Patient patient;

    @NotNull (message = "Treatment Time Pattern must be set (for the prescription)")
    @ManyToOne
    @JoinColumn(name = "treatment_time_pattern_id", referencedColumnName = "treatment_time_pattern_id", nullable = false)
    private TreatmentTimePattern treatmentTimePattern;

    @OneToMany(mappedBy = "prescription")
    private List<TreatmentEvent> treatmentEvents;
}
