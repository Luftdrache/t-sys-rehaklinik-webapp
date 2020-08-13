package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.entities.AuthenticationData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *DTO for {@link AuthenticationData} objects
 *
 * @author Julia Dalskaya
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDataDTO {
    private int authenticationDataId;

    @NotBlank(message = "Username must be set")
    @Size(min = 5, max = 35, message = "Login length must be no less than 5 and no more than 35 characters")
    private String username;

    @NotBlank(message = "Password must be set, not blank")
    @Size(min = 8, message = "Password length must be no less than 8 characters")
    private String password;

    private EmployeeDTO employee;

    private PatientDTO patient;
}
