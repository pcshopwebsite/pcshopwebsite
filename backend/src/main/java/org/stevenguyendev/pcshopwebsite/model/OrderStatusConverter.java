package org.stevenguyendev.pcshopwebsite.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(OrderStatus attribute) {
        if (attribute == null) return null;
        return attribute.getValue();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return OrderStatus.valueOf(dbData);
    }
}
