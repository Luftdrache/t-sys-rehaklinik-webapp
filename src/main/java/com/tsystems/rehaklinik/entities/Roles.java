package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles", schema = "rehaklinik")
public class Roles implements Serializable {
    private int roleId;
    private String role;
    private List<Employees> employees;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, length = 11)
    public int getRoleId() {
        return roleId;
    }

    @Column(name = "role", nullable = false, unique = true, length = 25)
    public String getRole() {
        return role;
    }

    @OneToMany(mappedBy = "role")
    public List<Employees> getEmployees() {
        return employees;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmployees(List<Employees> employeesByRoleId) {
        this.employees = employeesByRoleId;
    }
}
