import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';
import { SharedModule } from '../../../../shared/modules/shared.module';
import { MaterialModule } from '../../../../shared/modules/material.module';

@Component({
  selector: 'app-login',
  standalone: true,
  imports:
    [
      SharedModule,
      MaterialModule
    ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  email: string = "";
  password: string = "";

  constructor
    (
      private authService: AuthService,
      private router: Router
    ) { }

  login() {
    this.authService.login(this.email, this.password).subscribe({
      next: (res) => {
        console.log(res);
        localStorage.setItem('token', res);
        this.router.navigate(['']);
      },
      error: console.log
    })

  }

}
