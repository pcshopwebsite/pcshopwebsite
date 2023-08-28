import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { loadScript } from '@paypal/paypal-js';

@Component({
  selector: 'app-payment-method',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './payment-method.component.html',
  styleUrls: ['./payment-method.component.scss']
})
export class PaymentMethodComponent {

  constructor() {}

  ngAfterViewInit(): void {
    const selector = '#paypal-standard-checkout';
    this.waitForElementToLoad(selector).then(s => {
      this.renderPaypalButtons(selector);
    })
  }

  private async waitForElementToLoad(selector: string) {
    while (document.querySelector(selector) === null) {
      await new Promise(resolve => requestAnimationFrame(resolve));
    }
    return document.querySelector(selector);
  }

  private renderPaypalButtons(selector: string) {
    loadScript({
      clientId: 'AWIRuvWKTL5r45MM-L-sRSsAWcQ9_vBliFM8foisS5EIOnACWw8B7Azepq7-wnBDpbRIykOwmEYMGQG_'
    }).then((paypal) => {
      const paypalButtons = paypal?.Buttons;
      if (paypalButtons) {
        paypalButtons()
          .render(selector)
          .catch((error) => {
            console.log('Failed to render the Paypal Buttons', error);
          });
      } else {
        console.log('The paypal button isn\'t defined');
      }
    }).catch((error) => {
      console.log('Failed to load the PayPal JS SDK script', error);
    });
  }
}
