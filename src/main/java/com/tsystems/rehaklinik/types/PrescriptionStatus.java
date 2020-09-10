package com.tsystems.rehaklinik.types;

public enum PrescriptionStatus {
    TBD("To be done"),
    CANCELLED("Cancelled"),
    DONE("Done");

    private String prescriptionStatus;

    PrescriptionStatus(String prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

    public String getPrescriptionStatus() {
        return prescriptionStatus;
    }

    public void setPrescriptionStatus(String prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

    @Override
    public String toString() {
        return prescriptionStatus;
    }

}
