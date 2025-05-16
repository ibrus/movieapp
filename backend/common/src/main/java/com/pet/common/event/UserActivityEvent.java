package com.pet.common.event;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserActivityEvent {
    private String userId;
    private String movieId;
    private String action; // watched, liked, disliked
    private Instant timestamp;
}
