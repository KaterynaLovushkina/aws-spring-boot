package com.lovushkina.store.controller;

import com.lovushkina.store.domain.Category;
import com.lovushkina.store.dto.AppDto;
import com.lovushkina.store.dto.CategoryDto;
import com.lovushkina.store.dto.assembler.AppDtoAssembler;
import com.lovushkina.store.dto.assembler.CategoryDtoAssembler;
import com.lovushkina.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryDtoAssembler categoryDtoAssembler;
    @Autowired
    private AppDtoAssembler appDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        CollectionModel<CategoryDto> categoryDtos = categoryDtoAssembler.toCollectionModel(categories);
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        CategoryDto categoryDto = categoryDtoAssembler.toModel(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @PostMapping(value = "/{appCategoryId}")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody Category app, @PathVariable Integer appCategoryId) {
        Category newApp = categoryService.create(app, appCategoryId);
        CategoryDto appDto = categoryDtoAssembler.toModel(newApp);
        return new ResponseEntity<>(appDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/{appCategoryId}")
    public ResponseEntity<?> updateCategory(@RequestBody Category app, @PathVariable Integer id,
            @PathVariable Integer appCategoryId) {
        categoryService.update(id, app, appCategoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteApp(@PathVariable Integer id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/apps")
    public ResponseEntity<CollectionModel<AppDto>> getPlaylistsById(@PathVariable Integer id) {
        return new ResponseEntity<>(appDtoAssembler.toCollectionModel(categoryService.findById(id).getApps()),
                HttpStatus.OK);
    }

}
