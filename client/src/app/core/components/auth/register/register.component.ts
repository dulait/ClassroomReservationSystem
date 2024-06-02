import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { SharedModule } from '../../../../shared/modules/shared.module';
import { MaterialModule } from '../../../../shared/modules/material.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports:
    [
      SharedModule,
      MaterialModule
    ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  email: string = "";

  constructor
    (
      private authService: AuthService,
      private router: Router
    ) { }

  register() {
    this.authService.register(this.email).subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigate(['/verify-temp-password']);
      },
      error: console.log
    })

  }

}
