package com.nikesh.services.implementations;

import com.nikesh.exception.ResourceNotFoundException;
import com.nikesh.models.Category;
import com.nikesh.models.Product;
import com.nikesh.repositories.CategoryRepository;
import com.nikesh.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Category oldCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No such category found!"));
        oldCategory.setName(category.getName());
        oldCategory.setImage_url(category.getImage_url());

        return categoryRepository.save(oldCategory);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such category found!"));
    }

    @Override
    public void delete(Long id) {
        Category category = this.getById(id);
        for (Product product :
                category.getProducts()) {
            category.removeProduct(product);
        }
        categoryRepository.deleteById(id);
    }
}
