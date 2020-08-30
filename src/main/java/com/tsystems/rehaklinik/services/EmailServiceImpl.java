package com.tsystems.rehaklinik.services;


import com.itextpdf.text.Document;
import com.tsystems.rehaklinik.dto.MedicalRecordDTO;
import com.tsystems.rehaklinik.dto.TreatmentEventDTO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender emailSender;
    private final DoctorService doctorService;

    private final static String SENDER = "REHAKLINIK";
    private final static String EMAIL_TITLE = "Rehaklinik info: get your treatment details";

    @Override
    public boolean sendEmail() {
        logger.info("MedHelper_LOGS: In EmailServiceImpl - sending an email");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Patient patient = ((AuthenticationData) (authentication.getPrincipal())).getPatient();
        String email = patient.getEmail();
        String patientName = patient.getFirstName();
        int patientId = patient.getPatientId();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SENDER);
        message.setTo(email);
        message.setSubject(EMAIL_TITLE);
        message.setText("Dear, " + patientName + " \n Here is your treatment details:");
        //formPDF(patientId);
        emailSender.send(message);
        return true;
    }

    //IN PROGRESS
    private Document formPDF(int patientId) {
        Document pdf = new Document();
        MedicalRecordDTO medicalRecordDTO = doctorService.getMedicalRecord(patientId);
        List<TreatmentEventDTO> treatmentEventDTOS = doctorService.findTreatmentEventsByPatientId(patientId);
        return pdf;
    }


    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, DoctorService doctorService) {
        this.emailSender = emailSender;
        this.doctorService = doctorService;
    }

}
