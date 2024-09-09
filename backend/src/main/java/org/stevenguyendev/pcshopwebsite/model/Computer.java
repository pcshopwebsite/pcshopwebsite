package org.stevenguyendev.pcshopwebsite.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "computer", schema = "public")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    private Category category;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
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

    public Computer(
            UUID id,
            String name,
            String description,
            BigDecimal price,
            Float rating,
            String thumbnail,
            Category category,
            Brand brand,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.setId(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.thumbnail = thumbnail;
        this.category = category;
        this.brand = brand;
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    public void addMedia(Media mediaEntity) {
        medias.add(mediaEntity);
        mediaEntity.setComputer(this);
    }

    public void removeMedia(Media mediaEntity) {
        medias.remove(mediaEntity);
        mediaEntity.setComputer(null);
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
