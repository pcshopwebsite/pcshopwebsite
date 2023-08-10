package org.stevenguyendev.pcshopwebsite.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand", schema = "public")
public class Brand extends BaseAuditableEntity {

    private String name;

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
