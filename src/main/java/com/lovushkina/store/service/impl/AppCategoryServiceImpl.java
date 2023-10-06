package com.lovushkina.store.service.impl;

import com.lovushkina.store.domain.AppCategory;
import com.lovushkina.store.exception.DataNotFoundException;
import com.lovushkina.store.repository.AppCategoryRepository;
import com.lovushkina.store.service.AppCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppCategoryServiceImpl implements AppCategoryService {
    @Autowired
    final AppCategoryRepository repository;

    @Override
    public List<AppCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public AppCategory findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("AppCategory", id));
    }

    @Override
    public AppCategory create(AppCategory entity) {
        repository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, AppCategory entity) {
        AppCategory result = repository.findById(id).orElseThrow(() -> new DataNotFoundException("AppCategory", id));
        result.setName(entity.getName());
        result.setDescription(entity.getDescription());
        repository.save(result);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void insert10Rows(String name, String description) {
        repository.insert10Rows(name, description);
    }
}
