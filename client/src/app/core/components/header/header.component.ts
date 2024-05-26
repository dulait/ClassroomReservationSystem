import { Component } from '@angular/core';
import { MaterialModule } from '../../../shared/modules/material.module';
import { RouteService } from '../../services/route.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports:
    [
      MaterialModule,
    ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  constructor
    (
      private routeService: RouteService
    ) { }

  redirectToHome() {
    this.routeService.redirectToHome();
  }

  redirectToReservations() {
    this.routeService.redirectToReservations();
  }

}
