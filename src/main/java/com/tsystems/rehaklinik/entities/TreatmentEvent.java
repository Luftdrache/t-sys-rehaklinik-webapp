package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.converters.LocalTimeAttributeConverter;
import com.tsystems.rehaklinik.types.EventStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "treatment_events", schema = "rehaklinik")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_event_id", nullable = false, length = 11)
    private int treatmentEventId;

    @NotNull (message = "Treatment event date must be set")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "treatment_event_date", nullable = false)
    private LocalDate treatmentEventDate;

    @NotNull(message = "Treatment event time must be set")
    @Convert(converter = LocalTimeAttributeConverter.class)
    @Column(name = "treatment_event_time", nullable = false, columnDefinition = "TIME")
    private LocalTime treatmentEventTime;

    @Column(name = "cancel_reason", length = 100)
    @Size(min = 5, max = 100, message = "Cancel reason length must be no less than 5 and no more than 100 characters")
    private String cancelReason;

    @NotNull(message = "Patient must be set (for treatment event)")
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    private Patient patient;

    @NotNull(message = "Prescription must be set (for treatment event)")
    @ManyToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "prescription_id", nullable = false)
    private Prescription prescription;

    @NotNull(message = "Treatment event status must be set")
    @Enumerated(EnumType.STRING)
    @Column(name = "treatment_event_status", columnDefinition ="ENUM ('PLANNED', 'COMPLETED', 'CANCELLED', 'OVERDUE')",
            nullable = false, length = 100)
    private EventStatus treatmentEventStatus;
}
