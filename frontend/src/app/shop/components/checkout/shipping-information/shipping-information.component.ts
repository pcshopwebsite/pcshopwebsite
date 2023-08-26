import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-shipping-information',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './shipping-information.component.html',
  styleUrls: ['./shipping-information.component.scss']
})
export class ShippingInformationComponent {
  shippingForm: FormGroup = this.createForm();

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit() {}

  createForm() {
    return this.formBuilder.group({
      name: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      address: ['', Validators.required],
      city: ['', Validators.required],
      postalCode: ['', Validators.required],
      note: ['']
    })
  }

  submitForm() {
    if (this.shippingForm?.valid) {
      console.log(this.shippingForm.value)
    }
  }
}
