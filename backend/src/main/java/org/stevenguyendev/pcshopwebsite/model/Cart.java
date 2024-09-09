package org.stevenguyendev.pcshopwebsite.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cart", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Cart {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private User user;

    /** Non-owning side of the relationship. */
    @OneToMany(
            mappedBy = "cartProductPK.cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CartItem> cartItems;

    @Override
    public String toString() {
        return "Cart{" +
                "user=" + user +
                ", cartItems=" + cartItems +
                "} " + super.toString();
    }
}
