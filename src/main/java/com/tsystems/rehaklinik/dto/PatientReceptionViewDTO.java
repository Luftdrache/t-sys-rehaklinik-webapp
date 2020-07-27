package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.types.Gender;

import java.time.LocalDate;
import java.time.Period;

public class PatientReceptionViewDTO {
    private int patientId;
    private String name;
    private Gender gender;
    private int age;
    private String phone;
    private String insuranceCompany;
    private String insurancePolicyCode;
    private String attendingDoctor;

    public PatientReceptionViewDTO(Patient patient) {
        this.patientId = patient.getPatientId();
        this.name = patient.getFirstName() + " " + patient.getMiddleName() + " " + patient.getSurname();
        this.gender = patient.getGender();
        this.age = Period.between(patient.getDateOfBirth(), LocalDate.now()).getYears();
        this.phone = patient.getPhoneNumber();
        this.insuranceCompany = patient.getInsuranceCompany();
        this.insurancePolicyCode = patient.getInsurancePolicyCode();
        if (patient.getAttendingDoctorId() != null) {
            this.attendingDoctor = patient.getAttendingDoctorId().getFirstName() + " "
                    + patient.getAttendingDoctorId().getMiddleName() + " "
                    + patient.getAttendingDoctorId().getSurname();
        } else {
            this.attendingDoctor = "None";
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "PatientReceptionViewDTO{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                ", insurancePolicyCode='" + insurancePolicyCode + '\'' +
                ", attendingDoctor='" + attendingDoctor + '\'' +
                '}';
    }
}
