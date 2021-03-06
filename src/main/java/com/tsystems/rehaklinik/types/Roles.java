package com.tsystems.rehaklinik.types;

public enum Roles {
    ADMIN("Admin"),
    DOCTOR("Doctor"),
    NURSE("Nurse"),
    PATIENT("Patient"),
    RECEPTIONIST("Receptionist");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
