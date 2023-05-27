package org.pcshopwebsite.product;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(
        name = "product"
)
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_id_seq",
            sequenceName = "product_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_seq"
    )
    private Integer id;

    @Column(
            nullable = false
    )
    private String name;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    @Column
    private Float rating;
    @Column(
            name = "thumbnail",
            nullable = false)
    private String thumbnail;
    @Column(
            nullable = false)
    private Date createdAt;
    @Column(
            nullable = false)
    private Date updatedAt;

    public Product() {
    }

    public Product(
            Integer id,
            String name,
            String description,
            String thumbnail
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Product(
            String name,
            String description,
            String thumbnail
    ) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Product(
            Integer id,
            String name,
            String description,
            BigDecimal price,
            Float rating,
            String thumbnail
    ) {
        this(id, name, description, thumbnail);
        this.price = price;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(rating, product.rating) && Objects.equals(thumbnail, product.thumbnail) && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, rating, thumbnail, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", thumbnail='" + thumbnail + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
