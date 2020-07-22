package com.tsystems.rehaklinik.types;

public enum HospitalStayStatus {
    NEW("New"),
    BEING_TREATED("Being Treated"),
    DISCHARGED("Discharged");

    private String hospitalStayStatus;

    HospitalStayStatus(String hospitalStayStatus) {
        this.hospitalStayStatus = hospitalStayStatus;
    }

    public String getHospitalStayStatus() {
        return hospitalStayStatus;
    }

    public void setHospitalStayStatus(String hospitalStayStatus) {
        this.hospitalStayStatus = hospitalStayStatus;
    }

    @Override
    public String toString() {
        return hospitalStayStatus;
    }
}
