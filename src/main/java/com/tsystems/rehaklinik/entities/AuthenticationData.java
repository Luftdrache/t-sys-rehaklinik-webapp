package com.tsystems.rehaklinik.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * Authentication data entity
 */
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

    @Size(min = 5, max = 35, message = "Login length must be no less than 5 and no more than 35 characters")
    @Column(name = "username", nullable = false, length = 35)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "authenticationDataEmployee")
    private Employee employee;

    @OneToOne(mappedBy = "authenticationDataPatient")
    private Patient patient;
}
