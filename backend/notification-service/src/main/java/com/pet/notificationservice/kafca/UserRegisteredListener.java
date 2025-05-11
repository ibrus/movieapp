package com.pet.notificationservice.kafca;
import com.pet.common.event.UserRegisteredEvent;
import com.pet.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisteredListener {

    private static final Logger log = LoggerFactory.getLogger(UserRegisteredListener.class);
    private final EmailService emailService;

    @KafkaListener(topics = "user-registered", groupId = "notification-group")
    public void listen(UserRegisteredEvent event) {
        log.info("Received user registration event");
        emailService.sendWelcomeEmail(event.getUsername());
    }
}
