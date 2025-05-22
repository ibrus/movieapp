import { Component } from '@angular/core';
import { HomePageComponent } from './home-page/home-page.component';

@Component({
  selector: 'app-root',
  template: '<app-home-page></app-home-page>',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [HomePageComponent]
})
export class AppComponent { }
