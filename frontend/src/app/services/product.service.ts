import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { ProductDto } from "../models/product-dto";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ProductService {
    private readonly productUrl = `${environment.api.baseUrl}/${environment.api.productUrl}`;
    constructor(private http: HttpClient) { }

    findAll() : Observable<ProductDto[]> {
        return this.http.get<ProductDto[]>(this.productUrl);
    }

}