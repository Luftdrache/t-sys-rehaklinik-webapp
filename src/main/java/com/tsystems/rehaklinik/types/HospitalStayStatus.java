package com.tsystems.rehaklinik.types;

public enum HospitalStayStatus {
    NEW("New"),
    BEING_TREATED("Being Treated"),
    DISCHARGED("Discharged");

    private String hospitalStayStatus;

    HospitalStayStatus(String hospitalStayStatus) {
        this.hospitalStayStatus = hospitalStayStatus;
    }
}
