import { Component } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { RouterModule } from "@angular/router";
import { CartService } from "./services/cart.service";

@Component({
    selector: 'app-shop-master-layout',
    templateUrl: './shop-master-layout.component.html',
    styleUrls: ['./shop-master-layout.component.scss'],
    standalone: true,
    imports: [
        MatIconModule, 
        MatButtonModule, 
        MatInputModule,
        MatFormFieldModule,
        RouterModule
    ]
})
export class ShopMasterLayoutComponent {
    constructor(private cartService: CartService) { }
    ngOnInit() {
        this.cartService.findAll().subscribe();
    }
}