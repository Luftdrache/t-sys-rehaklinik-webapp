package com.tsystems.rehaklinik.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class MessageSenderImpl implements MessageSender {

    private Logger logger = LoggerFactory.getLogger(MessageSenderImpl.class);

    JmsTemplate jmsTemplate;


    @Override
    public void send(String message) {
        logger.info("MedHelper_LOGS: In MessageSender: sending message");
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage jmsMessage =
                        session.createTextMessage(message);
                logger.info("MedHelper_LOGS: In MessageSender: Sending: {}", jmsMessage.getText());
                return jmsMessage;
            }
        });
    }

    @Autowired
    public MessageSenderImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
