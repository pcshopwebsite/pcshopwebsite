package org.stevenguyendev.pcshopwebsite.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "cart_item", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class CartItem {

    // Composite key
    @EmbeddedId
    private CartProductPK cartProductPK;

    private Integer quantity;

    // Modified all args constructor
    public CartItem(
            Cart cart,
            Computer computer,
            Integer quantity
    ) {
        this.cartProductPK = new CartProductPK(cart, computer);
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem cartItem)) return false;
        return Objects.equals(cartProductPK, cartItem.cartProductPK) && Objects.equals(quantity, cartItem.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartProductPK, quantity);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartProductPK=" + cartProductPK +
                ", quantity=" + quantity +
                '}';
    }

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CartProductPK {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "cart_id")
        private Cart cart;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "computer_id")
        private Computer computer;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CartProductPK that)) return false;
            return Objects.equals(cart, that.cart) && Objects.equals(computer, that.computer);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cart, computer);
        }

        @Override
        public String toString() {
            return "CartProductPK{" +
                    "cart=" + cart +
                    ", computer=" + computer +
                    '}';
        }
    }
}
