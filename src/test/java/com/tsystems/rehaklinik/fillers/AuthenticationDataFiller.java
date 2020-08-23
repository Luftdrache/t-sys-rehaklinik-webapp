package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.entities.AuthenticationData;

public class AuthenticationDataFiller {
    public static final int ID = 1;
    public static final String USERNAME = "robertyoung";
    public static final String PASSWORD = "robertyoung";

    public AuthenticationDataFiller() {
    }

    public static AuthenticationData getAuthenticationData() {
        AuthenticationData authenticationData = new AuthenticationData();
        authenticationData.setAuthenticationDataId(ID);
        authenticationData.setUsername(USERNAME);
        authenticationData.setPassword(PASSWORD);
        return authenticationData;
    }
}
