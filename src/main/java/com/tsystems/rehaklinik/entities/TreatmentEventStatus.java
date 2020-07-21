package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.EventStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "treatment_event_statuses", schema = "rehaklinik",
uniqueConstraints = @UniqueConstraint(columnNames = "treatment_event_status", name = "UNQ_TREATMENT_EVENT_STATUS"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentEventStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_event_status_id", nullable = false, length = 11)
    private int treatmentEventStatusId;

    @NotNull(message = "Treatment event status must be set")
    @Enumerated(EnumType.STRING)
    @Column(name = "treatment_event_status", columnDefinition ="ENUM (' PLANNED', 'COMPLETED', 'CANCELLED')",
            nullable = false, length = 100)
    private EventStatus treatmentEventStatus;

    @OneToMany(mappedBy = "treatmentEventStatus")
    private List<TreatmentEvent> treatmentEvents;

}


