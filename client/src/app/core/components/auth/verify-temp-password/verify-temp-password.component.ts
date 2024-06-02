import { Component } from '@angular/core';
import { SharedModule } from '../../../../shared/modules/shared.module';
import { MaterialModule } from '../../../../shared/modules/material.module';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-verify-temp-password',
  standalone: true,
  imports:
    [
      SharedModule,
      MaterialModule
    ],
  templateUrl: './verify-temp-password.component.html',
  styleUrl: './verify-temp-password.component.scss'
})
export class VerifyTempPasswordComponent {
  email: string = "";
  tempPassword: string = "";

  constructor
    (
      private authService: AuthService,
      private router: Router
    ) { }

  verifyTempPassword() {
    this.authService.verifyTempPassword(this.email, this.tempPassword).subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigate(['/set-new-password']);
      },
      error: console.log
    });

  }

}
