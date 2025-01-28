package com.belen.phishing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.default.host}")
    private String defaultHost;

    @Value("${spring.mail.default.port}")
    private int defaultPort;

    @Value("${spring.mail.default.username}")
    private String defaultUsername;

    @Value("${spring.mail.default.password}")
    private String defaultPassword;

    @Value("${spring.mail.netflix.host}")
    private String netflixHost;

    @Value("${spring.mail.netflix.port}")
    private int netflixPort;

    @Value("${spring.mail.netflix.username}")
    private String netflixUsername;

    @Value("${spring.mail.netflix.password}")
    private String netflixPassword;

    @Value("${spring.mail.linkedin.host}")
    private String linkedinHost;

    @Value("${spring.mail.linkedin.port}")
    private int linkedinPort;

    @Value("${spring.mail.linkedin.username}")
    private String linkedinUsername;

    @Value("${spring.mail.linkedin.password}")
    private String linkedinPassword;

    @Bean
    public JavaMailSender defaultMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(defaultHost);
        mailSender.setPort(defaultPort);
        mailSender.setUsername(defaultUsername);
        mailSender.setPassword(defaultPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    @Bean
    public JavaMailSender netflixMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(netflixHost);
        mailSender.setPort(netflixPort);
        mailSender.setUsername(netflixUsername);
        mailSender.setPassword(netflixPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    @Bean
    public JavaMailSender linkedinMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(linkedinHost);
        mailSender.setPort(linkedinPort);
        mailSender.setUsername(linkedinUsername);
        mailSender.setPassword(linkedinPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}