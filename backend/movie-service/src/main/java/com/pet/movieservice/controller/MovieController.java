package com.pet.movieservice.controller;

import com.pet.movieservice.dto.MovieDto;
import com.pet.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/featured")
    public List<MovieDto> getFeaturedMovies() {
        return movieService.getFeaturedMovies();
    }

    @GetMapping("/category/{category}")
    public List<MovieDto> getMoviesByCategory(@PathVariable String category) {
        return movieService.getMoviesByCategory(category);
    }

    @GetMapping("/recommendations/{userId}")
    public List<MovieDto> getRecommendations(@PathVariable UUID userId) {
        return movieService.getRecommendations(userId);
    }
}
