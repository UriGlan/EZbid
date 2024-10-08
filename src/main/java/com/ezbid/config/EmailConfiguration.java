package com.ezbid.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {
    @Value("${spring.mail.username}")
    private String emailUserName;
    @Value("${spring.mail.password}")
    private String emailPassword;


    // This method configures the email sender
    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSender mailSender = new JavaMailSenderImpl();
        ((JavaMailSenderImpl) mailSender).setHost("smtp.gmail.com");
        ((JavaMailSenderImpl) mailSender).setPort(587);
        ((JavaMailSenderImpl) mailSender).setUsername(emailUserName);
        ((JavaMailSenderImpl) mailSender).setPassword(emailPassword);

        Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;

    }
}
