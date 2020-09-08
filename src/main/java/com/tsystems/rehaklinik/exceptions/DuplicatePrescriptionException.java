package com.tsystems.rehaklinik.exceptions;


/**
 * An exception, occurs in an effort to add duplicate prescription for the same patient
 *
 * @author Julia Dalskaya
 */
public class DuplicatePrescriptionException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param name medicine or procedure name
     */
    public DuplicatePrescriptionException(String name) {
        super(name);
    }
}
