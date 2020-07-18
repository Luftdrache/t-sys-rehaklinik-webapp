package com.tsystems.rehaklinik.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "positions", schema = "rehaklinik",
uniqueConstraints = @UniqueConstraint(columnNames = "position", name = "INQ_POSITION"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id", nullable = false, length = 11)
    private int positionId;

    @NotBlank(message = "Employee's position mustn't be blank or null")
    @Size(max=50, message = "Position length must be no more than 50 characters")
    @Column(name = "position", nullable = false, length = 50)
    private String position;

    @OneToMany(mappedBy = "position")
    private List<Employee> employees;



}
