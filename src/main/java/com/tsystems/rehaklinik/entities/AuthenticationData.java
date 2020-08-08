package com.tsystems.rehaklinik.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authentication_data", schema = "rehaklinik",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"},
                        name = "UNQ_USERNAME")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authentication_data_id", nullable = false, length = 11)
    private int authenticationDataId;

    @Column(name = "username", nullable = false, length = 35)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "authenticationDataEmployee")
    private Employee employee;

    @OneToOne(mappedBy = "authenticationDataPatient")
    private Patient patient;
}
