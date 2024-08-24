package com.nikesh.dto.order;


public record OrderItemDto(ProductDto product, Integer quantity) {
}
