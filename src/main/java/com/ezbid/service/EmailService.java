package com.ezbid.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// This class is a service that handles email sending
@Service
public class EmailService {
    private final JavaMailSender emailSender;

    // This constructor initializes the email sender
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    // This method sends a verification email
    public void sendVerificationEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        emailSender.send(message);
    }

    // This method sends a password reset email
    public void sendPasswordResetEmail(String email, String token) throws MessagingException {
        String subject = "Password Reset";
        String text = "<h1>Password Reset</h1>"
                + "<p>The reset token is</p>" + token
                + "<p>Please insert it in the reset password form</p>";

        sendVerificationEmail(email, subject, text);

    }
}
