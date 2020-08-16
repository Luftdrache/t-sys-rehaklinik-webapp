package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.PrescriptionStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


/**
 * Prescription entity
 */
@Entity
@Table(name = "prescriptions", schema = "rehaklinik")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prescription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id", nullable = false, length = 11)
    private int prescriptionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine_procedure_id", referencedColumnName = "medicine_procedure_id", nullable = false)
    private MedicineAndProcedure medicineAndProcedure;

    @Column(name = "dose", length = 20)
    private String dose;

    @Column(name = "administering_medication_method", length = 30)
    private String administeringMedicationMethod;

    @Column(name = "start_treatment", nullable = false)
    private LocalDate startTreatment;

    @Column(name = "end_treatment", nullable = false)
    private LocalDate endTreatment;

    @Enumerated(EnumType.STRING)
    @Column(name = "prescription_status", columnDefinition = "ENUM('TBD', 'CANCELLED')", nullable = false)
    private PrescriptionStatus prescriptionStatus;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "treatment_time_pattern_id", referencedColumnName = "treatment_time_pattern_id", nullable = false)
    private TreatmentTimePattern treatmentTimePattern;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<TreatmentEvent> treatmentEvents;
}
