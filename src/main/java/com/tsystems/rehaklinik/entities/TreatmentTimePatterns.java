package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "treatment_time_patterns", schema = "rehaklinik")
public class TreatmentTimePatterns implements Serializable {
    private int treatmentTimePatternId;
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
    private List<Prescriptions> prescriptions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_time_pattern_id", nullable = false, length = 11)
    public int getTreatmentTimePatternId() {
        return treatmentTimePatternId;
    }

    @Column(name = "count_per_day", nullable = true, length = 2)
    public int getCountPerDay() {
        return countPerDay;
    }

    @Column(name = "before_meals", nullable = true, columnDefinition = "bool default false")
    public boolean getBeforeMeals() {
        return beforeMeals;
    }

    @Column(name = "at_meals", nullable = true, columnDefinition = "bool default false")
    public boolean getAtMeals() {
        return atMeals;
    }

    @Column(name = "after_meals", nullable = true, columnDefinition = "bool default false")
    public boolean getAfterMeals() {
        return afterMeals;
    }

    @Column(name = "Sunday", nullable = true, columnDefinition = "bool default false")
    public boolean getSunday() {
        return sunday;
    }

    @Column(name = "Monday", nullable = true, columnDefinition = "bool default false")
    public boolean getMonday() {
        return monday;
    }

    @Column(name = "Tuesday", nullable = true, columnDefinition = "bool default false")
    public boolean getTuesday() {
        return tuesday;
    }

    @Column(name = "Wednesday", nullable = true, columnDefinition = "bool default false")
    public boolean getWednesday() {
        return wednesday;
    }

    @Column(name = "Thursday", nullable = true, columnDefinition = "bool default false")
    public boolean getThursday() {
        return thursday;
    }

    @Column(name = "Friday", nullable = true, columnDefinition = "bool default false")
    public boolean getFriday() {
        return friday;
    }

    @Column(name = "Saturday", nullable = true, columnDefinition = "bool default false")
    public boolean getSaturday() {
        return saturday;
    }

    @OneToMany(mappedBy = "treatmentTimePattern")
    public List<Prescriptions> getPrescriptions() {
        return prescriptions;
    }

    public void setTreatmentTimePatternId(int treatmentTimePatternId) {
        this.treatmentTimePatternId = treatmentTimePatternId;
    }

    public void setCountPerDay(int countPerDay) {
        this.countPerDay = countPerDay;
    }

    public void setBeforeMeals(boolean beforeMeals) {
        this.beforeMeals = beforeMeals;
    }

    public void setAtMeals(boolean atMeals) {
        this.atMeals = atMeals;
    }

    public void setAfterMeals(boolean afterMeals) {
        this.afterMeals = afterMeals;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public void setPrescriptions(List<Prescriptions> prescriptionsByTreatmentTimePatternId) {
        this.prescriptions = prescriptionsByTreatmentTimePatternId;
    }
}
