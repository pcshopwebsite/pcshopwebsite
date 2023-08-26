import { ComputerDto } from "./computer-dto";

export interface CartItemDto {
    id: string,
    computer: ComputerDto,
    quantity: number
    subTotal: number
}