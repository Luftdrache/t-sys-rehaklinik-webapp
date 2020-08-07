package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dao.AuthDAO;
import com.tsystems.rehaklinik.entities.AuthenticationData;

public interface AuthService {
    AuthenticationData findUserByUsername(String username);
}
