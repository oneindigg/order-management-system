package com.nikesh.services;

import com.nikesh.models.Category;

import java.util.List;

public interface CategoryService {

     List<Category> list();
     Category create(Category category);
     Category update(Long id, Category category);
     Category getById(Long id);
     void delete(Long id);

}
