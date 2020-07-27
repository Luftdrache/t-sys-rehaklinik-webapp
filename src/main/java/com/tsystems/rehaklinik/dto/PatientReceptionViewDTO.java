package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.types.Gender;

import java.time.LocalDate;

public class PatientReceptionViewDTO {
    private int patientId;
    private String name;
    private String surname;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String insuranceCompany;
    private String insurancePolicyCode;
    private String attendingDoctor;

    public PatientReceptionViewDTO(Patient patient) {
        this.patientId = patient.getPatientId();
        this.name = patient.getFirstName() + " " + patient.getMiddleName();
        this.surname = patient.getSurname();
        this.gender = patient.getGender();
        this.dateOfBirth = patient.getDateOfBirth();
        this.insuranceCompany = patient.getInsuranceCompany();
        this.insurancePolicyCode = patient.getInsurancePolicyCode();
        if (patient.getAttendingDoctorId() != null) {
            this.attendingDoctor = patient.getAttendingDoctorId().getFirstName() + " "
                    + patient.getAttendingDoctorId().getMiddleName() + " "
                    + patient.getAttendingDoctorId().getSurname();
        } else {
            this.attendingDoctor = "- None - ";
        }
    }


    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsurancePolicyCode() {
        return insurancePolicyCode;
    }

    public void setInsurancePolicyCode(String insurancePolicyCode) {
        this.insurancePolicyCode = insurancePolicyCode;
    }

    public String getAttendingDoctor() {
        return attendingDoctor;
    }

    public void setAttendingDoctor(String attendingDoctor) {
        this.attendingDoctor = attendingDoctor;
    }

    @Override
    public String toString() {
        return "PatientHospitalReceptionViewDTO{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                ", insurancePolicyCode='" + insurancePolicyCode + '\'' +
                ", attendingDoctor='" + attendingDoctor + '\'' +
                '}';
    }
}
