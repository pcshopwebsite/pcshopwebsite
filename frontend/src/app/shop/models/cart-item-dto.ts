import { ComputerDto } from "./computer-dto";

export interface CartItemDto {
    computer: ComputerDto,
    quantity: number,
    selected: boolean,
}