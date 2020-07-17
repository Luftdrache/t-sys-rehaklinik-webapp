package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "patients", schema = "rehaklinik")
public class Patients implements Serializable {
    private int patientId;
    private String firstName;
    private String secondName;
    private String surname;
    private LocalDate dateOfBirth;
    private String gender;
    private String login;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;
    private int passportId;
    private String insurancePolicyCode;
    private String insuranceCompany;
    private boolean consentToPersonalDataProcessing;
    private Employees attendingDoctorId;
    private MedicalRecords medicalRecord;
    private List<Prescriptions> prescriptions;
    private List<TreatmentEvents> treatmentEvents;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", nullable = false, length = 11)
    public int getPatientId() {
        return patientId;
    }

    @Column(name = "first_name", nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "second_name", nullable = true, length = 50)
    public String getSecondName() {
        return secondName;
    }

    @Column(name = "surname", nullable = false, length = 50)
    public String getSurname() {
        return surname;
    }

//    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth", nullable = false)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Column(name = "gender", nullable = false, length = 6)
    public String getGender() {
        return gender;
    }

    @Column(name = "login", nullable = false, unique = true, length = 35)
    public String getLogin() {
        return login;
    }

    @Column(name = "password", nullable = false, unique = true, length = 35)
    public String getPassword() {
        return password;
    }

    @Column(name = "phone_number", nullable = true, length = 25)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Column(name = "email", nullable = true, length = 80)
    public String getEmail() {
        return email;
    }

    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    @Column(name = "passport_id", nullable = false, unique = true)
    public int getPassportId() {
        return passportId;
    }

    @Column(name = "insurance_policy_code", nullable = false, length = 50)
    public String getInsurancePolicyCode() {
        return insurancePolicyCode;
    }

    @Column(name = "insurance_company", nullable = false, length = 50)
    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    @Column(name = "сonsent_to_personal_data_processing", nullable = false)
    public boolean getСonsentToPersonalDataProcessing() {
        return сonsentToPersonalDataProcessing;
    }

    @ManyToOne
    @JoinColumn(name = "attending_doctor_id", referencedColumnName = "employee_id", nullable = false)
    public Employees getAttendingDoctorId() {
        return attendingDoctorId;
    }


    @ManyToOne
    @JoinColumn(name = "medical_record_id", referencedColumnName = "medical_record_id")
    public MedicalRecords getMedicalRecord() {
        return medicalRecord;
    }


    @OneToMany(mappedBy = "patient")
    public List<Prescriptions> getPrescriptions() {
        return prescriptions;
    }


    @OneToMany(mappedBy = "patient")
    public List<TreatmentEvents> getTreatmentEvents() {
        return treatmentEvents;
    }


    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    public void setInsurancePolicyCode(String insurancePolicyCode) {
        this.insurancePolicyCode = insurancePolicyCode;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public void setСonsentToPersonalDataProcessing(boolean сonsentToPersonalDataProcessing) {
        this.сonsentToPersonalDataProcessing = сonsentToPersonalDataProcessing;
    }

    public void setAttendingDoctorId(Employees employeesByAttendingDoctorId) {
        this.attendingDoctorId = employeesByAttendingDoctorId;
    }

    public void setMedicalRecord(MedicalRecords medicalRecordsByMedicalRecordId) {
        this.medicalRecord = medicalRecordsByMedicalRecordId;
    }

    public void setPrescriptions(List<Prescriptions> prescriptionsByPatientId) {
        this.prescriptions = prescriptionsByPatientId;
    }

    public void setTreatmentEvents(List<TreatmentEvents> treatmentEventsByPatientId) {
        this.treatmentEvents = treatmentEventsByPatientId;
    }


    @Override
    public String toString() {
        return "Patients{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", passportId=" + passportId +
                ", insurancePolicyCode='" + insurancePolicyCode + '\'' +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                ", сonsentToPersonalDataProcessing=" + сonsentToPersonalDataProcessing +
                ", attendingDoctorId=" + attendingDoctorId +
                ", medicalRecord=" + medicalRecord +
                ", prescriptions=" + prescriptions +
                ", treatmentEvents=" + treatmentEvents +
                '}';
    }
}
