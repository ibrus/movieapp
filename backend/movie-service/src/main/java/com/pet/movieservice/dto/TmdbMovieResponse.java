package com.pet.movieservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class TmdbMovieResponse {
    private List<TmdbMovie> results;
}
