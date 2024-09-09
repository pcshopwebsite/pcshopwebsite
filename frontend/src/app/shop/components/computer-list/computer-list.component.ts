import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComputerService } from '../../services/computer.service';
import { ComputerDto } from 'src/app/shop/models/computer-dto';
import { Observable } from 'rxjs';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { CartService } from '../../services/cart.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-computer-list',
  standalone: true,
  imports: [
    CommonModule, 
    MatIconModule, 
    MatButtonModule,
    RouterModule
  ],
  templateUrl: './computer-list.component.html',
  styleUrls: ['./computer-list.component.scss']
})
export class ComputerListComponent implements OnInit {
  
  computers$: Observable<ComputerDto[]> | undefined;
  
  constructor(
    private computerService: ComputerService,
    private cartService: CartService,
    private router: Router
    ) { }
    
    ngOnInit(): void {
      this.computers$ = this.computerService.findAll();
    }
    addToCart(_t38: ComputerDto) {
      this.cartService.addToCart(_t38).subscribe();
    }
    buyComputerNow(computer: ComputerDto) {
      this.cartService.addToCart(computer).subscribe(() => {
        this.router.navigate(['/shop/cart']);
      });
    }
}
