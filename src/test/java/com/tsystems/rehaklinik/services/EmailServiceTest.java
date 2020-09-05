package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.fillers.AuthenticationDataFiller;
import com.tsystems.rehaklinik.fillers.PatientFiller;
import com.tsystems.rehaklinik.util.PDFGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private Logger mockLogger;
    @Mock
    private JavaMailSender emailSender;
    @Mock
    private PDFGenerator pdfGenerator;
    @Mock
    private Authentication authentication;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private MimeMessage mimeMessage;
    @Mock
    private InputStreamSource attachment;


    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    void sendEmail_should_send_email_with_PDF_attachment() {
        mockLogger.info("Logging");
        given(securityContext.getAuthentication()).willReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        AuthenticationData authenticationData = AuthenticationDataFiller.getAuthenticationData();
        authenticationData.setPatient(PatientFiller.getPatient());
        given(authentication.getPrincipal()).willReturn(authenticationData);
        given(emailSender.createMimeMessage()).willReturn(mimeMessage);
        given(pdfGenerator.generatePDF(2, "Peter Fletcher")).willReturn(attachment);
        assertNotNull(attachment);
        assertTrue(emailService.sendEmail());
    }
}