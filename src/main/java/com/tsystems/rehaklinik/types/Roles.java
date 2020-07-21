package com.tsystems.rehaklinik.types;

public enum Roles {
    ADMIN("Administrator"),
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
}
