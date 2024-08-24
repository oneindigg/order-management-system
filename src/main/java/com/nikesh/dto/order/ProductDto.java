package com.nikesh.dto.order;

public record ProductDto(
        Long id,
        String name,
        String productImageUrl,
        Integer price
) {

}
