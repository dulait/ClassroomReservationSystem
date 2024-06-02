import { Routes } from '@angular/router';
import { RegisterComponent } from './core/components/auth/register/register.component';
import { LoginComponent } from './core/components/auth/login/login.component';
import { HomeComponent } from './core/components/home/home.component';
import { PageNotFoundComponent } from './core/components/page-not-found/page-not-found.component';
import { ReservationsComponent } from './core/components/reservations/reservations.component';
import { VerifyTempPasswordComponent } from './core/components/auth/verify-temp-password/verify-temp-password.component';
import { SetNewPasswordComponent } from './core/components/auth/set-new-password/set-new-password.component';

export const routes: Routes =
    [
        { path: 'register', component: RegisterComponent, title: 'Register' },
        { path: 'verify-temp-password', component: VerifyTempPasswordComponent, title: 'Register' },
        { path: 'set-new-password', component: SetNewPasswordComponent, title: 'Register' },
        { path: 'login', component: LoginComponent, title: 'Login' },
        { path: 'reservations', component: ReservationsComponent, title: 'My Reservations' },
        { path: '', component: HomeComponent, title: 'Home' },
        { path: '**', component: PageNotFoundComponent, title: 'Error 404' },
    ];
