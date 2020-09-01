package com.tsystems.rehaklinik.jms;


/**
 * Sends message to queue
 *
 * @author Julia Dalskaya
 */
public interface MessageSender {

    /**
     * Sends message to queue
     *
     * @param message message to send
     */
    void send(final String message);

}
