import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatStepperModule } from '@angular/material/stepper';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';

type PaymentMethod = "cash" | "momo" | "credit_card" | "paypal";

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [
    CommonModule, 
    MatStepperModule, 
    FormsModule, 
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatRadioModule
  ],
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent {
  shippingForm!: FormGroup
  paymentMethodForm!: FormGroup

  constructor(private formBuilder: FormBuilder) {}
  ngOnInit() {
    this.createForm();
  }

  createForm(): void {
    this.shippingForm = this.formBuilder.group({
      receiverName: ['', Validators.required],
      receiverPhone: ['', Validators.required],
      shippingAddress: ['', Validators.required],
      shippingCity: ['', Validators.required],
      shippingPostalCode: ['', Validators.required],
      additionalNote: ['']
    })
    this.paymentMethodForm = this.formBuilder.group({
      paymentMethod: ['cash', Validators.required]
    })
  }
}
