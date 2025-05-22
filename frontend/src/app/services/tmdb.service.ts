import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DefaultMovieListsDto } from '../models/default-movie-lists.dto';

@Injectable({ providedIn: 'root' })
export class TmdbService {
    constructor(private http: HttpClient) {}

    getDefaultLists(
        page: number = 1,
        trendingWindow: 'day' | 'week' = 'day'
    ): Observable<DefaultMovieListsDto> {
        return this.http.get<DefaultMovieListsDto>(
            `/api/movies/default?page=${page}&trendingWindow=${trendingWindow}`
        );
    }
}
