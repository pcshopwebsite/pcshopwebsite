package org.stevenguyendev.pcshopwebsite.computer.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "computer_id", nullable = false, insertable = false, updatable = false)
    private Computer product;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Media() {
    }

    public Media(Computer product, String filePath, String fileType, LocalDateTime createdAt) {
        this.product = product;
        this.filePath = filePath;
        this.fileType = fileType;
        this.createdAt = createdAt;
    }

    public Media(Long id, Computer product, String filePath, String fileType, LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.filePath = filePath;
        this.fileType = fileType;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Computer getProduct() {
        return product;
    }

    public void setProduct(Computer product) {
        this.product = product;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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
        Media media = (Media) o;
        return Objects.equals(id, media.id) && Objects.equals(product, media.product) && Objects.equals(filePath, media.filePath) && Objects.equals(fileType, media.fileType) && Objects.equals(createdAt, media.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, filePath, fileType, createdAt);
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", product=" + product +
                ", filePath='" + filePath + '\'' +
                ", fileType='" + fileType + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
