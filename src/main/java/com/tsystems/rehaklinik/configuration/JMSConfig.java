package com.tsystems.rehaklinik.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.tsystems.rehaklinik")
@PropertySource("classpath:jms.properties")
public class JMSConfig {

    private final Environment environment;

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, environment.getRequiredProperty("jms.context.factory"));
        properties.put(Context.PROVIDER_URL, environment.getRequiredProperty("jms.provider.url"));
        properties.put(Context.SECURITY_PRINCIPAL, environment.getRequiredProperty("jms.username"));
        properties.put(Context.SECURITY_CREDENTIALS, environment.getRequiredProperty("jms.password"));
        InitialContext initialContext = new InitialContext(properties);

        ConnectionFactory connectionFactory =
                (ConnectionFactory) initialContext.lookup(environment.getRequiredProperty("jms.connection.factory.jndi"));
        UserCredentialsConnectionFactoryAdapter connectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
        connectionFactoryAdapter.setUsername(environment.getRequiredProperty("jms.username"));
        connectionFactoryAdapter.setPassword(environment.getRequiredProperty("jms.password"));
        connectionFactoryAdapter.setTargetConnectionFactory(connectionFactory);
        return connectionFactoryAdapter;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(environment.getRequiredProperty("jms.queue.jndi"));
        return template;
    }

    @Autowired
    public JMSConfig(Environment environment) {
        this.environment = environment;
    }
}