import { Component } from '@angular/core';
import { CustomFormComponent, FormButton, FormField } from '../../../shared/components/custom-form/custom-form.component';

@Component({
  selector: 'app-register',
  standalone: true,
  imports:
    [
      CustomFormComponent
    ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  fields: FormField[] = [
    { type: 'text', label: 'Email', name: 'email', required: true }
  ];

  buttons: FormButton[] = [
    { text: 'Cancel', action: this.cancel.bind(this), color: 'warn' },
    { text: 'Register', action: this.login.bind(this), color: 'primary' }
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
