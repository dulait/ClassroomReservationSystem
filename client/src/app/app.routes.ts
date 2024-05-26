import { Routes } from '@angular/router';
import { RegisterComponent } from './core/components/register/register.component';
import { LoginComponent } from './core/components/login/login.component';
import { HomeComponent } from './core/components/home/home.component';
import { PageNotFoundComponent } from './core/components/page-not-found/page-not-found.component';
import { ReservationsComponent } from './core/components/reservations/reservations.component';

export const routes: Routes =
    [
        { path: 'register', component: RegisterComponent, title: 'Register' },
        { path: 'login', component: LoginComponent, title: 'Login' },
        { path: 'reservations', component: ReservationsComponent, title: 'My Reservations' },
        { path: '', component: HomeComponent, title: 'Home' },
        { path: '**', component: PageNotFoundComponent, title: 'Error 404' },
    ];
