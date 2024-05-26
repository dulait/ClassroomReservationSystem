import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './core/components/header/header.component';
import { RouteService } from './core/services/route.service';
import { SharedModule } from './shared/modules/shared.module';

@Component({
  selector: 'app-root',
  standalone: true,
  imports:
    [
      SharedModule,
      HeaderComponent,
      RouterOutlet
    ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Classroom Reservation System';

  constructor(private routeService: RouteService) { }

  isNotFoundPage(): boolean {
    return this.routeService.isNotFoundPage();
  }

  isAuthPage(): boolean {
    return this.routeService.isAuthPage();
  }
}
