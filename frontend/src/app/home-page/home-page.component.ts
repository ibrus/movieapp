import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TmdbService } from '../services/tmdb.service';
import { DefaultMovieListsDto } from '../models/default-movie-lists.dto';
import { MovieCarouselComponent } from '../movie-carousel/movie-carousel.component';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  standalone: true,
  imports: [CommonModule, MovieCarouselComponent]
})
export class HomePageComponent implements OnInit {
  defaultLists?: DefaultMovieListsDto;
  isLoading = true;
  errorMsg?: string;

  constructor(private tmdb: TmdbService) {}

  ngOnInit(): void {
    this.tmdb.getDefaultLists().subscribe({
      next: lists => {
        this.defaultLists = lists;
        this.isLoading = false;
      },
      error: err => {
        this.errorMsg = err.message || 'Failed to load';
        this.isLoading = false;
      }
    });
  }
}
