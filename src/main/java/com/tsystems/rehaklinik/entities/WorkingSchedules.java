package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "working_schedules", schema = "rehaklinik")
public class WorkingSchedules implements Serializable {
    private int workingScheduleId;
    private boolean sunday;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private LocalTime workingStartTime;
    private LocalTime workingEndTime;
    private List<Employees> employees;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "working_schedule_id", nullable = false, length = 11)
    public int getWorkingScheduleId() {
        return workingScheduleId;
    }

    @Column(name = "Sunday", nullable = true)
    public boolean getSunday() {
        return sunday;
    }

    @Column(name = "Monday", nullable = true)
    public boolean getMonday() {
        return monday;
    }

    @Column(name = "Tuesday", nullable = true)
    public boolean getTuesday() {
        return tuesday;
    }

    @Column(name = "Wednesday", nullable = true)
    public boolean getWednesday() {
        return wednesday;
    }

    @Column(name = "Thursday", nullable = true)
    public boolean getThursday() {
        return thursday;
    }

    @Column(name = "Friday", nullable = true)
    public boolean getFriday() {
        return friday;
    }

    @Column(name = "Saturday", nullable = true)
    public boolean getSaturday() {
        return saturday;
    }

    @Column(name = "working_start_time", nullable = false)
    public LocalTime getWorkingStartTime() {
        return workingStartTime;
    }

    @Column(name = "working_end_time", nullable = false)
    public LocalTime getWorkingEndTime() {
        return workingEndTime;
    }

    @OneToMany(mappedBy = "workingSchedule")
    public List<Employees> getEmployees() {
        return employees;
    }

    public void setWorkingScheduleId(int workingScheduleId) {
        this.workingScheduleId = workingScheduleId;
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

    public void setWorkingStartTime(LocalTime workingStartTime) {
        this.workingStartTime = workingStartTime;
    }

    public void setWorkingEndTime(LocalTime workingEndTime) {
        this.workingEndTime = workingEndTime;
    }

    public void setEmployees(List<Employees> employeesByWorkingScheduleId) {
        this.employees = employeesByWorkingScheduleId;
    }
}
