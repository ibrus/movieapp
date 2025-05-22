import { Component } from '@angular/core';
import { HomePageComponent } from './home-page/home-page.component';

@Component({
  selector: 'app-root',
  template: '<app-home-page></app-home-page>',
  imports: [HomePageComponent],
  standalone: true
})
export class AppComponent { }
