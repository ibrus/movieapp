package com.pet.movieservice.service;

import com.pet.movieservice.dto.MoviePage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TmdbDefaultListService {
    private final RestTemplate restTemplate;
    private final String apiKey;

    public TmdbDefaultListService(RestTemplate restTemplate,
                                  @Value("${tmdb.apiKey}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey     = apiKey;
    }

    public MoviePage getPopular(int page) {
        String url = String.format(
                "https://api.themoviedb.org/3/movie/popular?api_key=%s&page=%d",
                apiKey, page
        );
        return restTemplate.getForObject(url, MoviePage.class);
    }

    public MoviePage getTopRated(int page) {
        String url = String.format(
                "https://api.themoviedb.org/3/movie/top_rated?api_key=%s&page=%d",
                apiKey, page
        );
        return restTemplate.getForObject(url, MoviePage.class);
    }

    public MoviePage getTrending(String timeWindow, int page) {
        String url = String.format(
                "https://api.themoviedb.org/3/trending/movie/%s?api_key=%s&page=%d",
                timeWindow, apiKey, page
        );
        return restTemplate.getForObject(url, MoviePage.class);
    }
}
