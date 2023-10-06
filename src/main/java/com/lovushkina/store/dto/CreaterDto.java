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
@Relation(itemRelation = "creater", collectionRelation = "creaters")
public class CreaterDto extends RepresentationModel<AppDto> {
    private final Integer id;
    private final String fullName;
    private final String workBranch;
    private final String email;
    private final Integer createdAppAmount;

}