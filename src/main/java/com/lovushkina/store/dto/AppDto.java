package com.lovushkina.store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "app", collectionRelation = "apps")
public class AppDto extends RepresentationModel<AppDto> {
    private final Integer id;
    private final String name;
    private final String description;
    private final Integer weightInMb;
    private final Date createDate;
    private final Boolean isFree;
    private final BigDecimal priceInDollars;
    private final Boolean hasAdvert;
    private final String audienceType;
}
