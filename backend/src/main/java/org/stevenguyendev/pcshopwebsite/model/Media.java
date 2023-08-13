package org.stevenguyendev.pcshopwebsite.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "media", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Media extends BaseAuditableEntity {

    /** Owning side of the relationship. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "computer_id"
    )
    private Computer computer;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_type")
    private String fileType;

    @Override
    public String toString() {
        return "Media{" +
                "filePath='" + filePath + '\'' +
                ", fileType='" + fileType + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Media media)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(filePath, media.filePath) && Objects.equals(fileType, media.fileType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), filePath, fileType);
    }
}
