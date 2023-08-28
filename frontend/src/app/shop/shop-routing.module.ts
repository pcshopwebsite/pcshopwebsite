import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ShopMasterLayoutComponent } from "./shop-master-layout.component";
import { ComputerListComponent } from "./components/computer-list/computer-list.component";
import { ShoppingCartComponent } from "./components/shopping-cart/shopping-cart.component";
import { CheckoutComponent } from "./components/checkout/checkout.component";

const routes: Routes = [
    {
        path: '',
        component: ShopMasterLayoutComponent,
        children: [
            {
                path: '',
                redirectTo: 'computers',
                pathMatch: 'full'
            },
            {
                path: 'computers',
                component: ComputerListComponent
            },
            {
                path: 'cart',
                component: ShoppingCartComponent
            },
            {
                path: 'checkout',
                component: CheckoutComponent
            }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class ShopRoutingModule {
}