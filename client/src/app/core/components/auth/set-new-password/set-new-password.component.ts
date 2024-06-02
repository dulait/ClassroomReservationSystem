import { Component } from '@angular/core';
import { SharedModule } from '../../../../shared/modules/shared.module';
import { MaterialModule } from '../../../../shared/modules/material.module';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-set-new-password',
  standalone: true,
  imports:
    [
      SharedModule,
      MaterialModule
    ],
  templateUrl: './set-new-password.component.html',
  styleUrl: './set-new-password.component.scss'
})
export class SetNewPasswordComponent {

  email: string = "";
  password: string = "";

  constructor
    (
      private authService: AuthService,
      private router: Router
    ) { }

  setNewPassword() {
    this.authService.setNewPassword(this.email, this.password).subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigate(['/login']);
      },
      error: console.log
    })

  }

}
