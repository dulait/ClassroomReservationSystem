import { Component } from '@angular/core';
import { ClassroomsComponent } from './classrooms/classrooms.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports:
    [
      ClassroomsComponent
    ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
