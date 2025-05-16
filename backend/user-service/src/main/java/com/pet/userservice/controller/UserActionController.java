package com.pet.userservice.controller;

import com.pet.userservice.kafka.publisher.UserActivityEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserActionController {

    private final UserActivityEventPublisher activityPublisher;

    @PostMapping("/watched")
    public ResponseEntity<Void> markWatched(
            @RequestParam String userId,
            @RequestParam String movieId
    ) {
        activityPublisher.publish(userId, movieId, "watched");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/liked")
    public ResponseEntity<Void> markLiked(
            @RequestParam String userId,
            @RequestParam String movieId
    ) {
        activityPublisher.publish(userId, movieId, "liked");
        return ResponseEntity.ok().build();
    }
}
