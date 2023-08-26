import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { CartService } from '../../services/cart.service';
import { Observable, Subject, map, tap } from 'rxjs';
import { CartItemDto } from '../../models/cart-item-dto';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { RouterModule } from '@angular/router';
import { MatRadioModule } from '@angular/material/radio'
import { FormsModule } from '@angular/forms';
import { loadScript } from '@paypal/paypal-js';
import { MatCheckboxModule } from '@angular/material/checkbox';

export type PaymentMethod = {name: string, logos: string[]};

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    MatIconModule,
    MatButtonModule,
    MatTooltipModule,
    RouterModule,
    MatRadioModule,
    FormsModule,
    MatCheckboxModule
  ]
})
export class ShoppingCartComponent implements OnInit {
  cartItems$: Observable<CartItemDto[] | undefined> = new Subject<CartItemDto[] | undefined>();
  paymentMethod: PaymentMethod = {
    name: 'Paypal',
    logos: [
      '/assets/images/payment-methods-icons/paypal.png'
    ]
  };
  paymentMethods: PaymentMethod[] = [
    {
      name: 'Credit cards',
      logos: [
        '/assets/images/payment-method-icons/visa.png',
        '/assets/images/payment-method-icons/jcb.png'
      ]
    },
    {
      name: 'Paypal',
      logos: [
        '/assets/images/payment-method-icons/paypal (1).png'
      ]
    },
    {
      name: 'Momo',
      logos: [
        '/assets/images/payment-method-icons/momo.png'
      ]
    }
  ]
  subTotal: number = 0.00;
  public constructor(
    private cartService: CartService
  ) { }
  ngOnInit(): void {
    this.cartService.findAll().subscribe(() => {
      this.cartItems$ = this.cartService.findAllCartItemsOfCart(this.cartService.cart.id).pipe(
        tap((items: CartItemDto[] | undefined) => {
          if (items) {
            this.subTotal = items.map((item: CartItemDto) => item.subTotal).reduce((prev: number, curr: number) => prev + curr)
          }
        })
      )
    });
  }
}
