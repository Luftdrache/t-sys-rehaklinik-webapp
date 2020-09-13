package com.tsystems.rehaklinik.dao.interfaces;

import com.tsystems.rehaklinik.entities.AuthenticationData;


/**
 * DAO for {@link AuthenticationData} objects
 *
 * @author Julia Dalskaya
 */
public interface AuthDAO {

    /**
     * Searches for a user by username. Used for authentication.
     *
     * @param username username
     * @return AuthenticationData object
     */
    AuthenticationData findByUsername(String username);

}
