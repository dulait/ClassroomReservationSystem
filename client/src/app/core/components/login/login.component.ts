import { Component } from '@angular/core';
import { CustomFormComponent, FormButton, FormField } from '../../../shared/components/custom-form/custom-form.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports:
    [
      CustomFormComponent
    ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  fields: FormField[] = [
    { type: 'text', label: 'Email', name: 'email', required: true },
    { type: 'password', label: 'Password', name: 'password', required: true },
  ];

  buttons: FormButton[] = [
    { text: 'Cancel', action: this.cancel.bind(this), color: 'warn' },
    { text: 'Login', action: this.login.bind(this), color: 'primary' }
  ];

  initialData = {
    email: '',
    password: ''
  };

  login(formData: any) {
    console.log('Login form submitted with data:', formData);
  }

  cancel() {
    console.log('Login form cancelled');
  }
}
