import { OrderItemDto } from "./order-item-dto";

export interface OrderDto {
    id?: string,
    userId?: string,
    shippingAddress?: string,
    shippingCity?: string,
    shippingPostalCode?: string,
    receiverName?: string,
    receiverPhone?: string,
    additionalNote?: string,
    totalAmount?: number,
    orderStatus?: string,
    orderItems?: OrderItemDto[]
}