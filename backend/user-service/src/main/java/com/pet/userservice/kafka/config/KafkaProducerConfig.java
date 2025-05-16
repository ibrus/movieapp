package com.pet.userservice.kafka.config;

import com.pet.common.event.UserActivityEvent;
import com.pet.common.event.UserRegisteredEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private Map<String, Object> baseConfigs() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return config;
    }

    @Bean
    public ProducerFactory<String, UserRegisteredEvent> userRegisteredProducerFactory() {
        return new DefaultKafkaProducerFactory<>(baseConfigs());
    }

    @Bean
    public KafkaTemplate<String, UserRegisteredEvent> userRegisteredKafkaTemplate() {
        return new KafkaTemplate<>(userRegisteredProducerFactory());
    }

    @Bean
    public ProducerFactory<String, UserActivityEvent> userActivityProducerFactory() {
        return new DefaultKafkaProducerFactory<>(baseConfigs());
    }

    @Bean
    public KafkaTemplate<String, UserActivityEvent> userActivityKafkaTemplate() {
        return new KafkaTemplate<>(userActivityProducerFactory());
    }
}
