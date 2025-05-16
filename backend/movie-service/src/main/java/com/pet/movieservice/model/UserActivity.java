package com.pet.movieservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "user_activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActivity {
    @Id
    private String id;

    private String userId;
    private String movieId;
    private String action;
    private Instant timestamp;
}
