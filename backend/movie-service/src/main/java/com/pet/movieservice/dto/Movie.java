package com.pet.movieservice.dto;

import lombok.Data;

@Data
public class Movie {
    private int id;
    private String title;
    private String poster_path;
}