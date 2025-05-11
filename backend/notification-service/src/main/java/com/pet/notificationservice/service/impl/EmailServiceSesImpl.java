package com.pet.notificationservice.service.impl;

import com.pet.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
@RequiredArgsConstructor
public class EmailServiceSesImpl implements EmailService {

    private final SesClient sesClient;
    @Value("${notification.email.sender}")
    private String senderEmail;

    @Override
    public void sendWelcomeEmail(String email) {
        String subject = "Welcome to MovieApp!";
        String body = "Hi there,\n\nThanks for registering at MovieApp!";

        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .destination(Destination.builder()
                        .toAddresses(email)
                        .build())
                .message(Message.builder()
                        .subject(Content.builder().data(subject).charset("UTF-8").build())
                        .body(Body.builder()
                                .text(Content.builder().data(body).charset("UTF-8").build())
                                .build())
                        .build())
                .source(senderEmail)
                .build();

        try {
            sesClient.sendEmail(emailRequest);
            System.out.println("Sent welcome email to " + email);
        } catch (SesException e) {
            System.err.println("Failed to send email: " + e.awsErrorDetails().errorMessage());
        }
    }
}
