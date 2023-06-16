package org.stevenguyendev.pcshopwebsite.computer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "computer")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column
    private Float rating;
    @Column(name = "thumbnail")
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(columnDefinition = "TEXT")
    private String specification;
    @OneToMany
    @JoinColumn(name = "computer_id")
    private List<Media> mediaList;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;

    public Computer() {
    }


    public Computer(
            String name,
            String description,
            BigDecimal price,
            Float rating,
            String thumbnail,
            Category category,
            Brand brand,
            String specification
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.thumbnail = thumbnail;
        this.category = category;
        this.brand = brand;
        this.specification = specification;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Computer(
            String name,
            String description,
            BigDecimal price,
            Float rating,
            String thumbnail,
            Category category,
            Brand brand,
            String specification,
            List<Media> mediaList,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.thumbnail = thumbnail;
        this.category = category;
        this.brand = brand;
        this.specification = specification;
        this.mediaList = mediaList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Computer(
            Long id,
            String name,
            String description,
            BigDecimal price,
            Float rating,
            String thumbnail,
            Category category,
            Brand brand,
            String specification,
            List<Media> mediaList,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.thumbnail = thumbnail;
        this.category = category;
        this.brand = brand;
        this.specification = specification;
        this.mediaList = mediaList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Computer(
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
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.thumbnail = thumbnail;
        this.category = category;
        this.brand = brand;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return Objects.equals(id, computer.id) && Objects.equals(name, computer.name) && Objects.equals(description, computer.description) && Objects.equals(price, computer.price) && Objects.equals(rating, computer.rating) && Objects.equals(thumbnail, computer.thumbnail) && Objects.equals(category, computer.category) && Objects.equals(brand, computer.brand) && Objects.equals(specification, computer.specification) && Objects.equals(mediaList, computer.mediaList) && Objects.equals(createdAt, computer.createdAt) && Objects.equals(updatedAt, computer.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, rating, thumbnail, category, brand, specification, mediaList, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Computer{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price=" + price + ", rating=" + rating + ", thumbnail='" + thumbnail + '\'' + ", category=" + category + ", brand=" + brand + ", specification='" + specification + '\'' + ", mediaList=" + mediaList + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
