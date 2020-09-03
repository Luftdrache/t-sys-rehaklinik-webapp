package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.entities.AuthenticationData;

public class AuthenticationDataFiller {
    private static final int ID = 1;
    private static final String USERNAME = "albertyoung";
    private static final String PASSWORD = "albertyoung";

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
