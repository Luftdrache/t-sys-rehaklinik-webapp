package com.tsystems.rehaklinik.exceptions;


/**
 * An exception, occurs in an effort to get or change information by id that is not in the database
 *
 * @author Julia Dalskaya
 */
public class WrongIdException extends RuntimeException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public WrongIdException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param id wrong id
     */
    public WrongIdException(int id) {
        super("Attempt to perform an operation by a nonexistent id = " + id);
    }
}
