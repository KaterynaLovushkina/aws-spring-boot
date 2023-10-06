package com.lovushkina.store.service;

import com.lovushkina.store.domain.AppCategory;

public interface AppCategoryService extends GeneralService<AppCategory, Integer> {
    void insert10Rows(String name, String description);
}
