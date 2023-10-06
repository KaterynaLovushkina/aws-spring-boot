package com.lovushkina.store.service.impl;

import com.lovushkina.store.domain.AppCategory;
import com.lovushkina.store.domain.Category;
import com.lovushkina.store.exception.DataNotFoundException;
import com.lovushkina.store.repository.AppCategoryRepository;
import com.lovushkina.store.repository.CategoryRepository;
import com.lovushkina.store.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    final CategoryRepository categoryRepository;

    @Autowired
    final AppCategoryRepository appCategoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("AppCategory", id));
    }

    @Override
    public Category create(Category entity) {
        categoryRepository.save(entity);
        return entity;
    }

    @Override
    public Category create(Category category, Integer appCategoryId) {
        AppCategory appCategory = appCategoryRepository.findById(appCategoryId)
                .orElseThrow(() -> new DataNotFoundException("AppCategory", appCategoryId));
        category.setAppCategory(appCategory);
        return categoryRepository.save(category);
    }

    @Override
    public void update(Integer id, Category entity) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category", id));
        category.setAudienceType(entity.getAudienceType());
        categoryRepository.save(category);
    }

    @Override
    public void update(Integer categoryId, Category entity, Integer appCategoryId) {
        AppCategory app = appCategoryRepository.findById(appCategoryId)
                .orElseThrow(() -> new DataNotFoundException("AppCategory", categoryId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new DataNotFoundException("Category", appCategoryId));
        category.setAppCategory(app);
        category.setAudienceType(entity.getAudienceType());
        categoryRepository.save(category);

    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

}
