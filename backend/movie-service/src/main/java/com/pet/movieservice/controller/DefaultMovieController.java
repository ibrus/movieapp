package com.pet.movieservice.controller;

import com.pet.movieservice.dto.DefaultMovieListsDto;
import com.pet.movieservice.dto.MoviePage;
import com.pet.movieservice.service.TmdbDefaultListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies/default")
public class DefaultMovieController {
    private final TmdbDefaultListService svc;

    public DefaultMovieController(TmdbDefaultListService svc) {
        this.svc = svc;
    }

    @GetMapping
    public DefaultMovieListsDto getDefaults(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "day") String trendingWindow
    ) {
        MoviePage pop  = svc.getPopular(page);
        MoviePage top  = svc.getTopRated(page);
        MoviePage trnd = svc.getTrending(trendingWindow, page);
        return new DefaultMovieListsDto(pop, top, trnd);
    }
}

