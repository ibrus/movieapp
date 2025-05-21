package com.pet.movieservice.client;

import com.pet.movieservice.dto.MovieDto;
import com.pet.movieservice.dto.TmdbMovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TmdbClient {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String TMDB_API_BASE = "https://api.themoviedb.org/3";

    public List<MovieDto> getPopularMovies() {
        String url = TMDB_API_BASE + "/movie/popular?api_key=" + apiKey;
        TmdbMovieResponse response = restTemplate.getForObject(url, TmdbMovieResponse.class);
        return mapToDtoList(response);
    }

    public List<MovieDto> getTopRatedMovies() {
        String url = TMDB_API_BASE + "/movie/top_rated?api_key=" + apiKey;
        TmdbMovieResponse response = restTemplate.getForObject(url, TmdbMovieResponse.class);
        return mapToDtoList(response);
    }

    public List<MovieDto> getUpcomingMovies() {
        String url = TMDB_API_BASE + "/movie/upcoming?api_key=" + apiKey;
        TmdbMovieResponse response = restTemplate.getForObject(url, TmdbMovieResponse.class);
        return mapToDtoList(response);
    }

    private List<MovieDto> mapToDtoList(TmdbMovieResponse response) {
        if (response == null || response.getResults() == null) return List.of();
        return response.getResults().stream()
                .map(m -> new MovieDto(m.getTitle(), m.getOverview(), m.getRelease_date(), m.getPoster_path()))
                .collect(Collectors.toList());
    }
}
