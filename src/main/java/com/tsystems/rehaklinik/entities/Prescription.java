package com.tsystems.rehaklinik.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


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

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "medicine_procedure_id", referencedColumnName = "medicine_procedure_id", nullable = false)
    private MedicineAndProcedure medicineAndProcedure;

    @Column(name = "dose", length = 20)
    private String dose;

    @Column(name = "administering_medication_method", length = 30)
    private String administeringMedicationMethod;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_treatment", nullable = false)
    private LocalDate startTreatment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_treatment", nullable = false)
    private LocalDate endTreatment;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "treatment_time_pattern_id", referencedColumnName = "treatment_time_pattern_id", nullable = false)
    private TreatmentTimePattern treatmentTimePattern;

    @OneToMany(mappedBy = "prescription")
    private List<TreatmentEvent> treatmentEvents;
}
