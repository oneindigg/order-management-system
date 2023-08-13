package com.nikesh.dto.product;

import com.nikesh.models.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getProductImageUrl(),
                product.getSellingPrice(),
                product.getQuantity()
        );
    }
}
