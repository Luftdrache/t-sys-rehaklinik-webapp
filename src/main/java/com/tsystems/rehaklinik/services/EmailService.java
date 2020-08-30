package com.tsystems.rehaklinik.services;

/**
 * Sending Email Service
 *
 * @author Julia Dalskaya
 */
public interface EmailService {
    /**
     * Sends email to a patient
     *
     * @return boolean of operation's result
     */
    boolean sendEmail();
}
