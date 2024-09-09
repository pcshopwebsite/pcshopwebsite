package org.stevenguyendev.pcshopwebsite.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    Pending("Pending"),
    Preparing("Preparing"),
    Shipping("Shipping"),
    Delivered("Delivered"),
    Returned("Returned"),
    Received("Received"),
    Cancelled("Cancelled");
    private final String value;
    OrderStatus(String value) {
        this.value = value;
    }
}
