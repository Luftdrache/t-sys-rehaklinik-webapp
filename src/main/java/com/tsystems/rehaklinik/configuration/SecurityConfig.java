package com.tsystems.rehaklinik.configuration;

import com.tsystems.rehaklinik.controllers.AdminController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = "com.tsystems.rehaklinik")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception { //разделение доступа к хендлерам, параметры
        http.authorizeRequests()
                .antMatchers("/login").anonymous()
//                .antMatchers("/admin/**").authenticated()
//                .antMatchers("/reception/**").authenticated()
//                .antMatchers("/doctor/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
//                .loginProcessingUrl()
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/auth/403-error-page");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
}
