import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { CartService } from '../../services/cart.service';
import { Observable, Subject, map } from 'rxjs';
import { CartItemDto } from '../../models/cart-item-dto';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    MatIconModule
  ]
})
export class ShoppingCartComponent implements OnInit {
  cartItems$: Observable<CartItemDto[] | undefined> = new Subject<CartItemDto[] | undefined>();
  public constructor(
    private cartService: CartService
  ) { }
  ngOnInit(): void {
    this.cartItems$ = this.cartService.findAllCartItemsOfCart(this.cartService.cart.id);
  }

}
