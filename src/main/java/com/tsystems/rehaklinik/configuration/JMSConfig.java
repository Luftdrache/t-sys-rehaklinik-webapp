package com.tsystems.rehaklinik.configuration;


import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.tsystems.rehaklinik")
public class JMSConfig {

    public static final String CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
    public static final String JMS_CONNECTION_FACTORY_JNDI = "jms/RemoteConnectionFactory";
    public static final String WILDFLY_REMOTING_URL = "http-remoting://127.0.0.1:8080";
    public static final String JMS_USERNAME = "luftdrache";
    public static final String JMS_PASSWORD = "luftdrache";
    public static final String JMS_QUEUE_JNDI = "treatmentEventQueue";
//    public static final String JMS_TOPIC_QUEUE_JNDI = "treatmentEventTopicQueue";


    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY);
        properties.put(Context.PROVIDER_URL, WILDFLY_REMOTING_URL);
        properties.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
        properties.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);

        InitialContext initialContext = new InitialContext(properties);

        ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup(JMS_CONNECTION_FACTORY_JNDI);
        UserCredentialsConnectionFactoryAdapter connectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
        connectionFactoryAdapter.setUsername(JMS_USERNAME);
        connectionFactoryAdapter.setPassword(JMS_PASSWORD);
        connectionFactoryAdapter.setTargetConnectionFactory(connectionFactory);
        return connectionFactoryAdapter;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(JMS_QUEUE_JNDI);
        return template;
    }
}
//https://docs.jboss.org/author/display/WFLY/Application%20Client%20Migration.html
//http://www.mastertheboss.com/jboss-server/jboss-jms/jboss-jms-configuration
//https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.0/html/configuring_messaging/configure_destinations_artemis