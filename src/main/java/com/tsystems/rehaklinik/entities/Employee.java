package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "employees", schema = "rehaklinik")
public class Employee {
    private int id;
    private String first_name;
    private String second_name;
    private String surname;
    private String login;
    private String password;
    private Date date_of_birth;
    private String phone_number;
    private String address;
    private String position;
    private String qualification_category_id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    public int getId() {
        return id;
    }

    @Column(name = "first_name", nullable = false, length = 50)
    public String getFirst_name() {
        return first_name;
    }

    @Column(name = "second_name", length = 50)
    public String getSecond_name() {
        return second_name;
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

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDate_of_birth() {
        return date_of_birth;
    }

    @Column(name = "phone_number", length = 25)
    public String getPhone_number() {
        return phone_number;
    }

    @Column(name = "address", length = 60)
    public String getAddress() {
        return address;
    }

    @Column(name = "position_id", nullable = false, length = 11)
    public String getPosition() {
        return position;
    }

    @Column(name = "qualification_category_id", length = 11)
    public String getQualification_category_id() {
        return qualification_category_id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
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

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setQualification_category_id(String qualification_category_id) {
        this.qualification_category_id = qualification_category_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", surname='" + surname + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                ", position='" + position + '\'' +
                ", qualification_category_id='" + qualification_category_id + '\'' +
                '}';
    }
}
