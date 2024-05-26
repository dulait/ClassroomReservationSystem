import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor
    (
      private router: Router,
      private activatedRoute: ActivatedRoute
    ) { }

  redirectToHome() {
    this.router.navigate(['']);
  }

  redirectToReservations() {
    this.router.navigate(['reservations']);
  }

  isNotFoundPage(): boolean {
    return this.activatedRoute.snapshot.routeConfig?.path === '**';
  }

  isAuthPage(): boolean {
    return this.router.url.startsWith('/login') || this.router.url.startsWith('/register')
      || this.router.url.startsWith('/zaboravljena-lozinka') || this.router.url.startsWith('/promena-lozinke');
  }
}
