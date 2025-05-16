package com.pet.movieservice.repository;

import com.pet.movieservice.model.UserActivity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserActivityRepository extends MongoRepository<UserActivity, String> {
    List<UserActivity> findByUserIdOrderByTimestampDesc(String userId);
}
