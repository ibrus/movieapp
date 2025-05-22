import { NgModule }         from '@angular/core';
import { BrowserModule }    from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule }     from '@angular/common';

import { AppComponent }           from './app.component';
import { HomePageComponent }      from './home-page/home-page.component';
import { MovieCarouselComponent } from './movie-carousel/movie-carousel.component';

@NgModule({
  declarations: [],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    AppComponent,
    HomePageComponent,
    MovieCarouselComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
