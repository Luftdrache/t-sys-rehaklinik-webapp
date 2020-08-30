package com.tsystems.rehaklinik.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.tsystems.rehaklinik")
@PropertySource("classpath:email.properties")
public class EmailConfig {

    private final Environment environment;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(environment.getRequiredProperty("mail.username"));
        mailSender.setPassword(environment.getRequiredProperty("mail.password"));
        mailSender.setJavaMailProperties(mailProperties());
        return mailSender;
    }


    @Bean
    public Properties mailProperties() {
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.host", environment.getRequiredProperty("mail.host"));
        mailProperties.put("mail.smtp.port", environment.getRequiredProperty("mail.port"));
        mailProperties.put("mail.transport.protocol", environment.getRequiredProperty("mail.protocol"));
        mailProperties.put("mail.smtp.auth", environment.getRequiredProperty("mail.auth"));
        mailProperties.put("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.starttls.enable"));
        mailProperties.put("mail.debug", environment.getRequiredProperty("mail.debug"));
        return mailProperties;
    }


    @Autowired
    public EmailConfig(Environment environment) {
        this.environment = environment;
    }
}
