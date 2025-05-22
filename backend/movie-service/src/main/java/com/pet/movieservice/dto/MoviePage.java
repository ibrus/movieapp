package com.pet.movieservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class MoviePage {
    private int page;
    private List<Movie> results;
    private int total_pages;
    private int total_results;
}
