package com.pet.userservice.kafka.publisher;

import com.pet.common.event.UserRegisteredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserRegisteredEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(UserRegisteredEventPublisher.class);

    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;

    public UserRegisteredEventPublisher(
            @Qualifier("userRegisteredKafkaTemplate") KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(UserRegisteredEvent event) {
        kafkaTemplate.send("user-registered", event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("✅ Kafka UserRegisteredEvent sent successfully: {}", result.getRecordMetadata());
                    } else {
                        log.error("❌ Kafka UserRegisteredEvent failed to send", ex);
                    }
                });
    }
}
