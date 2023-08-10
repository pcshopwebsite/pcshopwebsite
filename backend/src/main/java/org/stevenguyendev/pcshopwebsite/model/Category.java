package org.stevenguyendev.pcshopwebsite.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "category", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category extends BaseAuditableEntity {

    private String name;

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
