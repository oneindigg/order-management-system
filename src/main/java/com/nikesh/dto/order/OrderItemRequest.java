package com.nikesh.dto.order;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private int quantity;
}
