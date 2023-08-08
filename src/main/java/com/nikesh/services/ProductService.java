package com.nikesh.services;

import com.nikesh.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> list();
    Product create(Product product);
    Product update(Long id, Product product);
    Product getById(Long id);
    void delete(Long id);

}
