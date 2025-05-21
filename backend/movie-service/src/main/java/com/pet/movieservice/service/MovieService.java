package com.pet.movieservice.service;

import com.pet.movieservice.client.TmdbClient;
import com.pet.movieservice.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final TmdbClient tmdbClient;

    public List<MovieDto> getFeaturedMovies() {
        return tmdbClient.getPopularMovies();
    }

    public List<MovieDto> getMoviesByCategory(String category) {
        return switch (category.toLowerCase()) {
            case "top-rated" -> tmdbClient.getTopRatedMovies();
            case "upcoming" -> tmdbClient.getUpcomingMovies();
            default -> tmdbClient.getPopularMovies();
        };
    }

    public List<MovieDto> getRecommendations(UUID userId) {
        // Mocked for now
        return tmdbClient.getTopRatedMovies().stream().limit(5).toList();
    }
}