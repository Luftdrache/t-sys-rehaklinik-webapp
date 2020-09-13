package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dao.interfaces.AuthDAO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.fillers.AuthenticationDataFiller;
import com.tsystems.rehaklinik.services.implementations.AuthServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private Logger mockLogger;

    @Mock
    private AuthDAO authDAO;

    @InjectMocks
    private AuthServiceImpl authService;


    @Test
    @DisplayName("Successful finding authentication data")
    void findUserByUsername_should_return_user_credentials() {
        mockLogger.info("Logging");
        AuthenticationData authenticationData = AuthenticationDataFiller.getAuthenticationData();
        given(authDAO.findByUsername("albertyoung")).willReturn(authenticationData);
        AuthenticationData foundUser = authService.findUserByUsername("albertyoung");
        assertNotNull(foundUser);
    }

    @Test
    @DisplayName("The test returns correct data")
    void findUserByUsername_should_return_requested_user() {
        AuthenticationData testAuthenticationData = AuthenticationDataFiller.getAuthenticationData();
        given(authDAO.findByUsername("albertyoung")).willReturn(testAuthenticationData);
        AuthenticationData found = authService.findUserByUsername("albertyoung");
        assertEquals(testAuthenticationData, found);
    }

    @Test
    @DisplayName("Successful null returning if no such user found")
    void findUserByUsername_should_return_null() {
        mockLogger.info("Logging");
        AuthenticationData foundUser = authService.findUserByUsername("wrong_username");
        assertNull(foundUser);
    }
}