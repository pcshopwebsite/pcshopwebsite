import { CartItemDto } from "./cart-item-dto";

export interface CartDto {
    id: string,
    cartItems: CartItemDto[],  
}