import { ComputerDto } from "./computer-dto";

export interface OrderItemDto {
    product: ComputerDto,
    quantity: number
}