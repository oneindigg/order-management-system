package com.nikesh.dto.product;

public record ProductDTO(
        Long id,
        String name,
        String productImageUrl,
        Integer price,
        Integer inventory
) {
}
