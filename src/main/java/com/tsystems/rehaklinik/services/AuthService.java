package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.entities.AuthenticationData;

/**
 * Authorisation service.
 *
 * @author Julia Dalskaya
 */
public interface AuthService {
    AuthenticationData findUserByUsername(String username);
}
