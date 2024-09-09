import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { ComputerDto } from '../models/computer-dto';
import { Observable, map, tap } from 'rxjs';
import { AppConfigService } from 'src/app/core/services/app-config.service';
import { CartDto } from '../models/cart-dto';
import { CartItemDto } from '../models/cart-item-dto';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private readonly cartUrl = `${AppConfigService.settings.api.baseUrl}/${AppConfigService.settings.api.cartUrl}`;
  public cartItems = signal<CartItemDto[]>([]);
  
  constructor(private http: HttpClient) { }

  findAll() : Observable<CartDto[]> {
    return this.http.get<CartDto[]>(this.cartUrl).pipe(
      tap((carts: CartDto[]) => this.cartItems.set(carts[0]?.cartItems)),
    );
  }
  findAllCartItemsOfCart(cartId: string): Observable<CartItemDto[] | undefined> {
    const url = `${this.cartUrl}/${cartId}`;
    return this.http.get<CartDto>(url).pipe(
      map((cart: CartDto) => cart.cartItems),
      map((cartItems: CartItemDto[]) => {
        return cartItems.map((cartItem: CartItemDto) => {
          // cartItem.subTotal = cartItem.computer.price * cartItem.quantity;
          return cartItem;
        })
      })
    );
  }
  addToCart(_t38: ComputerDto): Observable<CartDto> {
    const addToCartRequest = {
      // cartId: this.cart.id,
      computerId: _t38.id,
      quantity: 1,
      action: 'ADD'
    }
    return this.http.put<any>(this.cartUrl, addToCartRequest);
  }
  removeFromCart(_t38: ComputerDto): Observable<CartDto> {
    const removeFromCartRequest = {
      // cartId: this.cart.id,
      computerId: _t38.id,
      quantity: 1,
      action: 'REMOVE'
    }
    return this.http.put<any>(this.cartUrl, removeFromCartRequest);
  }
}
