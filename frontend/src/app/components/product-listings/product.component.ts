import { Component, OnInit } from "@angular/core";
import { ProductDto } from "src/app/models/product-dto";
import { ProductService } from "src/app/services/product.service";

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html'
})
export class ProductComponent implements OnInit {

    products: ProductDto[] = [];

    constructor(
        private productService: ProductService
    ) { }

    ngOnInit(): void {
        this.findAllProducts();
    }

    findAllProducts() {
        this.productService.findAll().subscribe(products => {
            this.products = products;
            console.log(this.products);
        });
    }

}