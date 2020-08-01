package com.tsystems.rehaklinik.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "working_schedules", schema = "rehaklinik")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkingSchedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "working_schedule_id", nullable = false, length = 11)
    private int workingScheduleId;

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

    @NotNull(message = "Employee's working start time must be set")
    @Column(name = "working_start_time", nullable = false)
    private LocalTime workingStartTime;
    @NotNull(message = "Employee's working end time must be set")
    @Column(name = "working_end_time", nullable = false)
    private LocalTime workingEndTime;


    @OneToMany(mappedBy = "workingSchedule")
    private List<Employee> employees;


}
