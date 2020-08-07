package com.tsystems.rehaklinik.security;

import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomAuthProviderImpl implements AuthenticationProvider {

    private static Logger logger = LoggerFactory.getLogger(CustomAuthProviderImpl.class);

    private AuthService authService;
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("MedHelper_LOGS: CustomAuthProviderImpl: in authenticate() method");
        String username = authentication.getName();
        logger.info("MedHelper_LOGS: CustomAuthProviderImpl: received username " + username);
        AuthenticationData user = authService.findUserByUsername(username);
        if (user == null) {
            logger.info("MedHelper_LOGS: CustomAuthProviderImpl: bad username");
            throw new UsernameNotFoundException("User not found");
        }

        String password = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            logger.info("MedHelper_LOGS: CustomAuthProviderImpl: bad credentials");
            throw new BadCredentialsException("Bad credentials");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        String role;
        if(user.getEmployee()!=null) {
            role = "ROLE_" + user.getEmployee().getRole().toString().toUpperCase();
        } else {
            role = "ROLE_" + user.getPatient().getRole().toString().toUpperCase();
        }
        authorities.add(new SimpleGrantedAuthority(role));

        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), authorities);
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }


    @Autowired
    public CustomAuthProviderImpl(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }
}
