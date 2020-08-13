package com.tsystems.rehaklinik.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


/**
 * Custom AuthenticationSuccessHandler. Redirects users to different pages depending on user's role.
 *
 * @author Julia Dalskaya
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_DOCTOR")) {
            httpServletResponse.sendRedirect("/doctor/start-page");
        } else if (roles.contains("ROLE_NURSE")) {
            httpServletResponse.sendRedirect("/nurse/start-page");
        } else if (roles.contains("ROLE_RECEPTIONIST")) {
            httpServletResponse.sendRedirect("/reception/start-page");
        } else if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/start-page");
        }
    }
}
