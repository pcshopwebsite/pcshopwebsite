package org.stevenguyendev.pcshopwebsite.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "order", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order extends BaseAuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_city")
    private String shippingCity;

    @Column(name = "shipping_postal_code")
    private String shippingPostalCode;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_phone")
    private String receiverPhone;

    @Column(name = "additional_note")
    private String additionalNote;

    // Subtotal
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @OneToMany(
            mappedBy = "orderProduct.order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<OrderItem> orderItems;

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", shippingCity='" + shippingCity + '\'' +
                ", shippingPostalCode='" + shippingPostalCode + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", additionalNote='" + additionalNote + '\'' +
                ", totalAmount=" + totalAmount +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderItems=" + orderItems +
                "} " + super.toString();
    }
}
