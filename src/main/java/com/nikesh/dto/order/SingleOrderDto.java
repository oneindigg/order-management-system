package com.nikesh.dto.order;

import com.nikesh.models.Customer;

import java.util.List;

public record SingleOrderDto(
        Long id,
        Integer deliveryCharge,
        Customer customer,
        String paymentMethod,
        List<OrderItemDto> items,
        Integer productTotalPrice,
        Integer productTotalQuantity,
        String status) {
}
