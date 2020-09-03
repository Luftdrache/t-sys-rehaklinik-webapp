package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.types.Roles;

import java.time.LocalDate;

public class PatientCommon {
    protected static final int ID = 2;
    protected static final String FIRST_NAME = "Peter";
    protected static final String SURNAME = "Fletcher";
    protected static final LocalDate BIRTHDAY = LocalDate.of(1974, 11, 12);
    protected static final String PHONE_NUMBER = "(020) 1234-1726";
    protected static final String ADDRESS = "294 King Street London SE47 3FO";
    protected static final String EMAIL = "peterfletcher@gmail.com";
    protected static final String PASSPORT_ID = "1234879345";
    protected static final String INSURANCE_POLICY_CODE = "ABC45081371PN";
    protected static final String INSURANCE_COMPANY = "Viva Medicare";
    protected static final boolean CONSENT_TO_PERSONAL_DATA_PROCESSING = true;
    protected static final Roles ROLE = Roles.PATIENT;
}
