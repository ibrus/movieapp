package com.pet.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultMovieListsDto {
    private MoviePage popular;
    private MoviePage topRated;
    private MoviePage trending;
}
