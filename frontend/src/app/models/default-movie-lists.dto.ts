import { MoviePage } from './movie-page.model';

export interface DefaultMovieListsDto {
    popular:  MoviePage;
    topRated: MoviePage;
    trending: MoviePage;
}
