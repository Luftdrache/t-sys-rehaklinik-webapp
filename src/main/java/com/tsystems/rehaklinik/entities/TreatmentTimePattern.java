package com.tsystems.rehaklinik.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "treatment_time_patterns", schema = "rehaklinik")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentTimePattern implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_time_pattern_id", nullable = false, length = 11)
    private int treatmentTimePatternId;

    @Column(name = "count_per_day", length = 2)
    private int countPerDay;


    @Column(name = "before_meals")
    private boolean beforeMeals;
    @Column(name = "at_meals")
    private boolean atMeals;
    @Column(name = "after_meals")
    private boolean afterMeals;

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


    @OneToMany(mappedBy = "treatmentTimePattern")
    private List<Prescription> prescriptions;

}
