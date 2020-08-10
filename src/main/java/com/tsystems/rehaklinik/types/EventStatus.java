package com.tsystems.rehaklinik.types;

public enum EventStatus {
    PLANNED("Planned"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled"),
    OUT_OF_TIME("Out of Time");

    private String eventStatus;

    EventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    @Override
    public String toString() {
        return eventStatus;
    }
}
