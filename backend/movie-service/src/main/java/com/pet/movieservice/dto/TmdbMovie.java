package com.pet.movieservice.dto;

import lombok.Data;

@Data
public class TmdbMovie {
    private String title;
    private String overview;
    private String release_date;
    private String poster_path;
}
