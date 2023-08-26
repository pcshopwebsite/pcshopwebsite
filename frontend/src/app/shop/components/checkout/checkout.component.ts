import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CdkStepperModule } from '@angular/cdk/stepper';
import { LayoutComponent } from './layout/layout.component';
import { ShippingInformationComponent } from './shipping-information/shipping-information.component';
import { ConfirmOrderComponent } from './confirm-order/confirm-order.component';
import { PaymentMethodComponent } from './payment-method/payment-method.component';

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [
    CommonModule,
    CdkStepperModule,
    LayoutComponent,
    ShippingInformationComponent,
    ConfirmOrderComponent,
  PaymentMethodComponent],
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent {
}
