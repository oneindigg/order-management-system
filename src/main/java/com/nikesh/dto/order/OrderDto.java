package com.nikesh.dto.order;

public record OrderDto(
        Long id,
        String customerName,
        String customerEmail,
        String customerAddress,
        Integer deliveryCharge,
        Integer productTotalPrice,
        Integer productTotalQuantity,
        String status
        ) {
}
