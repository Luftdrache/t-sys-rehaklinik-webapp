package com.tsystems.rehaklinik.services.implementations;


import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.services.interfaces.EmailService;
import com.tsystems.rehaklinik.util.PDFGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private JavaMailSender emailSender;
    private PDFGenerator pdfGenerator;

    private static final String SENDER = "REHAKLINIK";
    private static final String EMAIL_TITLE = "Rehaklinik info: get your treatment details";
    private static final String FILE_NAME = "Rehaklinik Treatment Details Report.pdf";

    @Override
    public boolean sendEmail() {
        logger.info("MedHelper_LOGS: In EmailServiceImpl - sending an email");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Patient patient = ((AuthenticationData) (authentication.getPrincipal())).getPatient();

        int patientId = patient.getPatientId();
        String patientName = patient.getFirstName() + " " + patient.getSurname();
        String email = patient.getEmail();

        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SENDER);
            helper.setTo(email);
            helper.setSubject(EMAIL_TITLE);
            helper.setText("Dear " + patientName +
                    ",\n\nHere is your treatment details in attachment." +
                    "\n\n\nWe wish you a speedy recovery!\nYours sincerely,\nRehaklinik");
            final InputStreamSource attachment = pdfGenerator.generatePDF(patientId, patientName);
            helper.addAttachment(FILE_NAME, attachment);
            emailSender.send(message);
        } catch (MessagingException exception) {
            logger.error("MedHelper_LOGS: ERROR in EmailServiceImpl -->", exception.fillInStackTrace());
        }
        return true;
    }


    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, PDFGenerator pdfGenerator) {
        this.emailSender = emailSender;
        this.pdfGenerator = pdfGenerator;
    }

}
