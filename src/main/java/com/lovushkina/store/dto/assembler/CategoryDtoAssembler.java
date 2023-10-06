package com.lovushkina.store.dto.assembler;

import com.lovushkina.store.controller.CategoryController;
import com.lovushkina.store.domain.Category;
import com.lovushkina.store.dto.CategoryDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoryDtoAssembler implements RepresentationModelAssembler<Category, CategoryDto> {
    @Override
    public CategoryDto toModel(Category entity) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(entity.getId())
                .audienceType(entity.getAudienceType())
                .appCategoryName(entity.getAppCategory().getName())
                .appCategoryDescription(entity.getAppCategory().getDescription())
                .build();
        Link selfLink = linkTo(methodOn(CategoryController.class).getCategory(categoryDto.getId())).withSelfRel();
        categoryDto.add(selfLink);
        return categoryDto;
    }

    @Override
    public CollectionModel<CategoryDto> toCollectionModel(Iterable<? extends Category> entities) {
        CollectionModel<CategoryDto> categoryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CategoryController.class).getAllCategories()).withSelfRel();
        categoryDtos.add(selfLink);
        return categoryDtos;
    }

    public CollectionModel<CategoryDto> toCollectionModel(Iterable<? extends Category> entities, Link link) {
        CollectionModel<CategoryDto> categoryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        categoryDtos.add(link);
        return categoryDtos;
    }
}
