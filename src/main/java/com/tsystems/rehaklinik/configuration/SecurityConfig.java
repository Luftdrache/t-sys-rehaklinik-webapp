package com.tsystems.rehaklinik.configuration;

import com.tsystems.rehaklinik.security.CustomAuthProviderImpl;
import com.tsystems.rehaklinik.security.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@ComponentScan(basePackages = "com.tsystems.rehaklinik")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomAuthProviderImpl authProvider;

    private static final String LOGIN_PAGE = "/auth/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", LOGIN_PAGE,
                        "/resources/css/**", "/resources/images/**", "/rehaklinik/api/**").permitAll()
                .antMatchers("/", LOGIN_PAGE).anonymous()
                .antMatchers("/doctor/**").hasRole("DOCTOR")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/reception/**").hasRole("RECEPTIONIST")
                .antMatchers("/nurse/**").hasRole("NURSE")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(LOGIN_PAGE)
                .loginProcessingUrl("/login/process") //login form should POST data to this URL for processing.Here SS checks login and pass
                .permitAll()
                .successHandler(customAuthenticationSuccessHandler())
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/auth/403-error-page");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Autowired
    public SecurityConfig(CustomAuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }
}
