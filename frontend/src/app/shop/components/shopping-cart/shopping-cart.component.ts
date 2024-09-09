import { Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { CartService } from '../../services/cart.service';
import { Observable, Subject, map, tap } from 'rxjs';
import { CartItemDto } from '../../models/cart-item-dto';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { Router, RouterModule } from '@angular/router';
import { MatRadioModule } from '@angular/material/radio'
import { FormsModule } from '@angular/forms';
import { MatCheckboxChange, MatCheckboxModule } from '@angular/material/checkbox';
import { OrderService } from '../../services/order.service';

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
  subTotal: number = 0.00;
  public constructor(
    private cartService: CartService,
    private orderService: OrderService,
    private router: Router
    ) { }
    ngOnInit(): void {
      // this.cartService.findAll().subscribe(() => {
      //   this.cartItems$ = this.cartService.findAllCartItemsOfCart(this.cartService.cart.id).pipe(
      //     tap((items: CartItemDto[] | undefined) => {
      //       if (items && items.length > 0) {
      //         this.subTotal = items.map((item: CartItemDto) => item.subTotal).reduce((prev: number, curr: number) => prev + curr)
      //       }
      //     })
      //     )
      //   });
      }
      proceedCheckout() {
        
        this.router.navigate(['/shop/checkout']);
      }
      selectionChange(e: MatCheckboxChange, item: CartItemDto) {
        const cbSelected = e.checked;
        if (cbSelected) {
          this.orderService.order.update(order => {
            order.orderItems?.push({
              product: item.computer,
              quantity: item.quantity,
              // price: item.computer.price
            })
            return order;
          })
        } else {
          this.orderService.order.update(order => {
            order.orderItems?.splice(order.orderItems.indexOf(
              {
                product: item.computer,
                quantity: item.quantity,
                // price: item.computer.price
              }
              ), 1);
              return order;
          })
        }
      }
      headerCheckChange($event: MatCheckboxChange) {
        if ($event.checked) {
          
        }
      }
    }
      