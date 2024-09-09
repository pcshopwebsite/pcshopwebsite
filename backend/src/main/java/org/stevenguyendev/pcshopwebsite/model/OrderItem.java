package org.stevenguyendev.pcshopwebsite.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_item", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @EmbeddedId
    private OrderProductPK orderProduct;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    public OrderItem(
            Order order,
            Computer product,
            Integer quantity,
            BigDecimal price
    ) {
        OrderProductPK pk = new OrderProductPK();
        pk.setOrder(order);
        pk.setProduct(product);
        this.orderProduct = pk;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem orderItem)) return false;
        return Objects.equals(orderProduct, orderItem.orderProduct) && Objects.equals(quantity, orderItem.quantity) && Objects.equals(price, orderItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderProduct, quantity, price);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderProduct=" + orderProduct +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Embeddable
    @Getter
    @Setter
    public static class OrderProductPK {

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "order_id")
        private Order order;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "computer_id")
        private Computer product;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof OrderProductPK that)) return false;
            return Objects.equals(order, that.order) && Objects.equals(product, that.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(order, product);
        }
    }
}
