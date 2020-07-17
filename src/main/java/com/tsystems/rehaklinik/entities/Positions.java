package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "positions", schema = "rehaklinik")
public class Positions implements Serializable {
    private int positionId;
    private String position;
    private List<Employees> employees;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id", nullable = false, length = 11)
    public int getPositionId() {
        return positionId;
    }

    @Column(name = "position", nullable = false, unique = true, length = 50)
    public String getPosition() {
        return position;
    }

    @OneToMany(mappedBy = "positions")
    public List<Employees> getEmployees() {
        return employees;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setEmployees(List<Employees> employeesByPositionId) {
        this.employees = employeesByPositionId;
    }
}
