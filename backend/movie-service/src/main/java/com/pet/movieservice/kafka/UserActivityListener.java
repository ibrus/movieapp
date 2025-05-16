package com.pet.movieservice.kafka;

import com.pet.common.event.UserActivityEvent;
import com.pet.movieservice.model.UserActivity;
import com.pet.movieservice.repository.UserActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserActivityListener {

    private final UserActivityRepository repository;

    @KafkaListener(topics = "user-activity", groupId = "movie-service")
    public void handleUserActivity(UserActivityEvent event) {
        UserActivity activity = UserActivity.builder()
                .userId(event.getUserId())
                .movieId(event.getMovieId())
                .action(event.getAction())
                .timestamp(event.getTimestamp())
                .build();

        repository.save(activity);
    }
}
