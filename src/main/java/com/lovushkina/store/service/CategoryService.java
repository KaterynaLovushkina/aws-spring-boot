package com.lovushkina.store.service;

import com.lovushkina.store.domain.Category;

public interface CategoryService extends GeneralService<Category, Integer> {
    Category create(Category category, Integer appCategoryId);

    void update(Integer categoryId, Category category, Integer appCategoryId);

}
