package com.tsystems.rehaklinik.services.interfaces;

import com.tsystems.rehaklinik.entities.AuthenticationData;

/**
 * Authorisation service.
 *
 * @author Julia Dalskaya
 */
public interface AuthService {

    /**
     * Business logic for finding a user by username
     * @param username username
     * @return AuthenticationData
     */
    AuthenticationData findUserByUsername(String username);
}
