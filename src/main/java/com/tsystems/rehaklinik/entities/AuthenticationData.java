package com.tsystems.rehaklinik.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "authentication_data", schema = "rehaklinik",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"login"},
                        name = "UNQ_LOGIN"),
                @UniqueConstraint(columnNames = "password",
                        name = "UNQ_PASSWORD")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authentication_data_id", nullable = false, length = 11)
    private int authenticationDataId;

    @NotNull(message = "Login must be set")
    @Size(min = 5, max = 35, message = "Login length must be no less than 5 and no more than 35 characters")
    @Column(name = "login", nullable = false, length = 35)
    private String login;

    @NotBlank(message = "Password must be set, not blank")
    @Size(min = 5, max = 35, message = "Password length must be no less than 5 and no more than 35 characters")
    @Column(name = "password", nullable = false, length = 35)
    private String password;

    @OneToOne(mappedBy = "authenticationDataEmployee")
    private Employee employee;

    @OneToOne(mappedBy = "authenticationDataPatient")
    private Patient patient;
}
