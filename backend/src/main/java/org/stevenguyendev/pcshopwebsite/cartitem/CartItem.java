package org.stevenguyendev.pcshopwebsite.cartitem;

import jakarta.persistence.*;
import org.stevenguyendev.pcshopwebsite.cart.Cart;
import org.stevenguyendev.pcshopwebsite.computer.entity.Computer;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "computer_id")
    private Computer computer;

    private Integer quantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public CartItem() {
    }

    public CartItem(Cart cart, Computer computer, Integer quantity, LocalDateTime createdAt) {
        this.cart = cart;
        this.computer = computer;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public CartItem(Long id, Cart cart, Computer computer, Integer quantity, LocalDateTime createdAt) {
        this.id = id;
        this.cart = cart;
        this.computer = computer;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return quantity == cartItem.quantity && Objects.equals(id, cartItem.id) && Objects.equals(cart, cartItem.cart) && Objects.equals(computer, cartItem.computer) && Objects.equals(createdAt, cartItem.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cart, computer, quantity, createdAt);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", cart=" + cart +
                ", computer=" + computer +
                ", quantity=" + quantity +
                ", createdAt=" + createdAt +
                '}';
    }
}
