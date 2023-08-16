import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
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
  private _cart: CartDto = {} as CartDto;
  constructor(private http: HttpClient) { }

  get cart(): CartDto {
    return this._cart;
  }

  set cart(cart: CartDto) {
    this._cart = cart;
  }
  
  findAll() : Observable<CartDto[]> {
    return this.http.get<CartDto[]>(this.cartUrl).pipe(
      tap((carts: CartDto[]) => this._cart = carts[0])
    );
  }
  findAllCartItemsOfCart(cartId: string): Observable<CartItemDto[] | undefined> {
    const url = `${this.cartUrl}/${cartId}`;
    return this.http.get<CartDto>(url).pipe(
      map((cart: CartDto) => cart.cartItems)
    );
  }
  addToCart(_t38: ComputerDto): Observable<CartDto> {
    const addToCartRequest = {
      cartId: this.cart.id,
      computerId: _t38.id,
      quantity: 1,
      action: 'ADD'
    }
    return this.http.put<any>(this.cartUrl, addToCartRequest);
  }
}
