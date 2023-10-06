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
@Relation(itemRelation = "category", collectionRelation = "categories")
public class CategoryDto extends RepresentationModel<AppDto> {
    private final Integer id;
    private final String audienceType;
    private final String appCategoryName;
    private final String appCategoryDescription;

}
