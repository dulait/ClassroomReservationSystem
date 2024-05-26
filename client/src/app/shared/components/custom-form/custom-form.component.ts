import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MaterialModule } from '../../modules/material.module';
import { SharedModule } from '../../modules/shared.module';

export interface FormField {
  type: 'text' | 'date' | 'select' | 'password';
  label: string;
  name: string;
  required?: boolean;
  options?: { value: number, label: string }[];
}

export interface FormButton {
  text: string;
  action: (formData: any) => void;
  color?: 'primary' | 'accent' | 'warn';
}

@Component({
  selector: 'app-custom-form',
  standalone: true,
  imports:
    [
      MaterialModule,
      SharedModule
    ],
  templateUrl: './custom-form.component.html',
  styleUrl: './custom-form.component.scss'
})
export class CustomFormComponent {
  @Input() fields: FormField[] = [];
  @Input() buttons: FormButton[] = [];
  @Input() initialFormData: any;

  @Output() formSubmit = new EventEmitter<any>();
  @Output() formCancel = new EventEmitter<void>();

  form!: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({});
    this.fields.forEach(field => {
      const control = this.fb.control(
        this.initialFormData[field.name] || '',
        field.required ? Validators.required : []
      );
      this.form.addControl(field.name, control);
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.formSubmit.emit(this.form.value);
    }
  }

  onCancel() {
    this.formCancel.emit();
  }

  getControl(name: string) {
    return this.form.get(name);
  }

}
