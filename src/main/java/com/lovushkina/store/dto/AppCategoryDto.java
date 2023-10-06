package com.lovushkina.store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "appCategory", collectionRelation = "appCategories")
public class AppCategoryDto extends RepresentationModel<AppDto> {
    private final Integer id;
    private final String name;
    private final String description;

}
