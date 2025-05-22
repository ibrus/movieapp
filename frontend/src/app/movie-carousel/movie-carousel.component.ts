import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Movie } from '../models/movie-page.model';

@Component({
  selector: 'movie-carousel',
  templateUrl: './movie-carousel.component.html',
  styleUrls: ['./movie-carousel.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class MovieCarouselComponent {
  @Input() movies: Movie[] = [];
}
