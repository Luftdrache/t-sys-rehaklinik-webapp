package com.tsystems.rehaklinik.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Is used to send message to queue on app launch
 *
 * @author Julia Dalskaya
 */
@Component
public class CustomContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(CustomContextRefreshedEventListener.class);

    MessageSender messageSender;

    /**
     * Sends message to queue
     *
     * @param contextRefreshedEvent ContextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("MedHelper_LOGS: In CustomContextRefreshedEventListener: sending message in app launch");
        messageSender.send("Rehaklinik web-app: app launched");
    }

    @Autowired
    public CustomContextRefreshedEventListener(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
}
