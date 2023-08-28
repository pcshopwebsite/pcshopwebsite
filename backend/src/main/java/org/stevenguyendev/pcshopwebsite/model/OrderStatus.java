package org.stevenguyendev.pcshopwebsite.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("Pending"),
    PREPARING("Preparing"),
    SHIPPING("Shipping"),
    DELIVERED("Delivered"),
    RETURNED("Returned"),
    RECEIVED("Received"),
    CANCELLED("Cancelled");
    private final String value;
    private OrderStatus(String value) {
        this.value = value;
    }
}
