package com.tsystems.rehaklinik.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;


/**
 * Treatment time pattern entity
 */
@Entity
@Table(name = "treatment_time_patterns", schema = "rehaklinik")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentTimePattern implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_time_pattern_id", nullable = false, length = 11)
    private int treatmentTimePatternId;

    @Column(name = "Sunday")
    private boolean sunday;
    @Column(name = "Monday")
    private boolean monday;
    @Column(name = "Tuesday")
    private boolean tuesday;
    @Column(name = "Wednesday")
    private boolean wednesday;
    @Column(name = "Thursday")
    private boolean thursday;
    @Column(name = "Friday")
    private boolean friday;
    @Column(name = "Saturday")
    private boolean saturday;

    @Column(name = "precision_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime precisionTime;

    @Column(name = "interval_in_hours", length = 2)
    private int intervalInHours;

    @Column(name = "before_meals")
    private boolean beforeMeals;
    @Column(name = "at_meals")
    private boolean atMeals;
    @Column(name = "after_meals")
    private boolean afterMeals;

    @OneToMany(mappedBy = "treatmentTimePattern", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;
}
