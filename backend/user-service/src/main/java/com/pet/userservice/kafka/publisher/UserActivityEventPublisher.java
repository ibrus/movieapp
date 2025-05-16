package com.pet.userservice.kafka.publisher;

import com.pet.common.event.UserActivityEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserActivityEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(UserActivityEventPublisher.class);

    private final KafkaTemplate<String, UserActivityEvent> kafkaTemplate;

    public UserActivityEventPublisher(
            @Qualifier("userActivityKafkaTemplate") KafkaTemplate<String, UserActivityEvent> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String userId, String movieId, String action) {
        UserActivityEvent event = UserActivityEvent.builder()
                .userId(userId)
                .movieId(movieId)
                .action(action)
                .timestamp(Instant.now())
                .build();

        kafkaTemplate.send("user-activity", event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("✅ Kafka UserActivityEvent sent successfully: {}", result.getRecordMetadata());
                    } else {
                        log.error("❌ Kafka UserActivityEvent failed to send", ex);
                    }
                });
    }
}
