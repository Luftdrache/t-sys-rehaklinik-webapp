package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.types.Roles;

public interface AuthDAO {

    AuthenticationData findByUsername(String username);

}
