package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees", schema = "rehaklinik")
public class Employees implements Serializable{
    private int employeeId;
    private String firstName;
    private String secondName;
    private String surname;
    private String login;
    private String password;
    private LocalDate dateOfBirth;
    private int passportId;
    private String address;
    private String phoneNumber;
    private String email;
    private int cabinetOrWardNumber;
    private Positions positions;
    private QualificationCategories qualificationCategory;
    private WorkingSchedules workingSchedule;
    private Roles role;
    private List<Patients> patients;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, length = 11)
    public int getEmployeeId() {
        return employeeId;
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

    @Column(name = "login", nullable = false, unique = true, length = 35)
    public String getLogin() {
        return login;
    }

    @Column(name = "password", nullable = false, unique = true, length = 35)
    public String getPassword() {
        return password;
    }

//    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth", nullable = false)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Column(name = "passport_id", nullable = false, unique = true)
    public int getPassportId() {
        return passportId;
    }

    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    @Column(name = "phone_number", nullable = false, length = 25)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Column(name = "email", nullable = true, length = 80)
    public String getEmail() {
        return email;
    }

    @Column(name = "cabinet_or_ward_number", nullable = true, length = 3)
    public int getCabinetOrWardNumber() {
        return cabinetOrWardNumber;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "position_id", nullable = false)
    public Positions getPositions() {
        return positions;
    }

    @ManyToOne
    @JoinColumn(name = "qualification_category_id", referencedColumnName = "qualification_category_id")
    public QualificationCategories getQualificationCategory() {
        return qualificationCategory;
    }

    @ManyToOne
    @JoinColumn(name = "working_schedule_id", referencedColumnName = "working_schedule_id")
    public WorkingSchedules getWorkingSchedule() {
        return workingSchedule;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    public Roles getRole() {
        return role;
    }

    @OneToMany(mappedBy = "attendingDoctorId")
    public List<Patients> getPatients() {
        return patients;
    }


    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setCabinetOrWardNumber(int cabinetOrWardNumber) {
        this.cabinetOrWardNumber = cabinetOrWardNumber;
    }


    public void setPositions(Positions positionsByPositionId) {
        this.positions = positionsByPositionId;
    }

    public void setQualificationCategory(QualificationCategories qualificationCategoriesByQualificationCategoryId) {
        this.qualificationCategory = qualificationCategoriesByQualificationCategoryId;
    }

    public void setWorkingSchedule(WorkingSchedules workingSchedulesByWorkingScheduleId) {
        this.workingSchedule = workingSchedulesByWorkingScheduleId;
    }

    public void setRole(Roles rolesByRoleId) {
        this.role = rolesByRoleId;
    }

    public void setPatients(List<Patients> patientsByEmployeeId) {
        this.patients = patientsByEmployeeId;
    }


    @Override
    public String toString() {
        return "Employees{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passportId=" + passportId +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", cabinetOrWardNumber=" + cabinetOrWardNumber +
                ", positions=" + positions +
                ", qualificationCategory=" + qualificationCategory +
                ", workingSchedule=" + workingSchedule +
                ", role=" + role +
                ", patients=" + patients +
                '}';
    }
}
