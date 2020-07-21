package com.tsystems.rehaklinik.types;

public enum TreatmentType {
    MEDICINE("Medicine"),
    PROCEDURE("Procedure");

    private String treatmentType;

    TreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }
}
