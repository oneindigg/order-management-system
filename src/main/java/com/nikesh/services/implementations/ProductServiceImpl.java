package com.nikesh.services.implementations;

import com.nikesh.dto.product.ProductDTO;
import com.nikesh.dto.product.ProductDTOMapper;
import com.nikesh.exceptions.ResourceNotFoundException;
import com.nikesh.models.Category;
import com.nikesh.models.Product;
import com.nikesh.repositories.CategoryRepository;
import com.nikesh.repositories.ProductRepository;
import com.nikesh.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

   private final ProductRepository productRepository;
   private final CategoryRepository categoryRepository;
   private final ProductDTOMapper productDTOMapper;

    @Override
    public List<ProductDTO> list() {
        return productRepository.findAll()
                .stream()
                .map(productDTOMapper).collect(Collectors.toList());
    }

    @Override
    public Product create(Product product) {
        Set<Category> categories = new HashSet<>();
        for(Category category: product.getCategories()){
            categories.add(categoryRepository.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("No such category found")));
        }
        product.setCategories(categories);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such product found!"));
    }

    @Override
    public Product update(Long id, Product product) {
        Product exProduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No such product found!"));
        Set<Category> categories = new HashSet<>();
        for(Category category: product.getCategories()){
                categories.add(categoryRepository.findById(category.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No such category found")));
            }
        exProduct.setId(id);
        exProduct.setName(product.getName());
        exProduct.setDescription(product.getDescription());
        exProduct.setProductSku(product.getProductSku());
        exProduct.setSize(product.getSize());
        exProduct.setProductImageUrl(product.getProductImageUrl());
        exProduct.setSellingPrice(product.getSellingPrice());
        exProduct.setCostPerItem(product.getCostPerItem());
        exProduct.setQuantity(product.getQuantity());
        exProduct.setSellingPrice(product.getSellingPrice());
        exProduct.setCategories(categories);

        return productRepository.save(exProduct);
    }

    @Override
    public void delete(Long id){
        Product product = this.getById(id);
        for (Category category: product.getCategories()){
            product.removeProduct(category);
        }
        productRepository.deleteById(id);
    }
}
