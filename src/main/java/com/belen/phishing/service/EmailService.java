package com.belen.phishing.service;

import com.belen.phishing.config.ConstantesUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender defaultMailSender;
    private final JavaMailSender netflixMailSender;
    private final JavaMailSender linkedinMailSender;

    @Autowired
    public EmailService(JavaMailSender defaultMailSender, JavaMailSender netflixMailSender, JavaMailSender linkedinMailSender) {
        this.defaultMailSender = defaultMailSender;
        this.netflixMailSender = netflixMailSender;
        this.linkedinMailSender = linkedinMailSender;
    }

    public void sendEmail(String to, String subject, String text, String senderType) {
        JavaMailSender mailSender = getMailSender(senderType);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("no-reply@belydu.com");
        mailSender.send(message);
    }

    public void sendEmail2(String to, String subject, String htmlContent, String senderType) throws MessagingException {
        JavaMailSender mailSender = getMailSender(senderType);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.setFrom("no-reply@belydu.com");

        mailSender.send(message);
    }

    private JavaMailSender getMailSender(String senderType) {
        switch (senderType.toLowerCase()) {
            case "netflix":
                return netflixMailSender;
            case "linkedin":
                return linkedinMailSender;
            default:
                return defaultMailSender;
        }
    }
}