package org.stevenguyendev.pcshopwebsite.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "computer", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Computer extends BaseAuditableEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private Float rating;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Category category;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Brand brand;

    @Column(columnDefinition = "TEXT")
    private String specification;

    @OneToMany(
            mappedBy = "computer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final Set<Media> medias = new HashSet<>();

    @OneToMany(
            mappedBy = "computer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final Set<CartItem> cartItems = new HashSet<>();

    public void addMedia(Media mediaEntity) {
        medias.add(mediaEntity);
        mediaEntity.setComputer(this);
    }

    public void removeMedia(Media mediaEntity) {
        medias.remove(mediaEntity);
        mediaEntity.setComputer(null);
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setComputer(this);
    }

    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        cartItem.setComputer(null);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", thumbnail='" + thumbnail + '\'' +
                ", specification='" + specification + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Computer computer)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(name, computer.name) && Objects.equals(description, computer.description) && Objects.equals(price, computer.price) && Objects.equals(rating, computer.rating) && Objects.equals(thumbnail, computer.thumbnail) && Objects.equals(specification, computer.specification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, price, rating, thumbnail, specification);
    }
}
