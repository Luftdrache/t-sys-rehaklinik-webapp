package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.Prescription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentTimePatternDTO {
    private int treatmentTimePatternId;

    @PositiveOrZero (message = "Count per day must be null or positive")
    private int countPerDay;

    private boolean beforeMeals;
    private boolean atMeals;
    private boolean afterMeals;

    private boolean sunday;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;

    private LocalTime precisionTime;

    private List<PrescriptionDTO> prescriptions;
}