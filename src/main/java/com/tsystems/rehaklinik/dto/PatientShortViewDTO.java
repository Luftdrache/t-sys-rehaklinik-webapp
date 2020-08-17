package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.types.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

/**
 * DTO for {@link Patient} objects. It is a shortened representation of an object.
 *
 * @author Julia Dalskaya
 */
@Getter
@Setter
public class PatientShortViewDTO {
    private int patientId;
    private String name;
    private Gender gender;
    private int age;
    private String phone;
    private String insuranceCompany;
    private String insurancePolicyCode;
    private String attendingDoctor;

    public PatientShortViewDTO(Patient patient) {
        this.patientId = patient.getPatientId();
        this.name = patient.getSurname() + ", " + patient.getFirstName() + " " + patient.getMiddleName();
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
