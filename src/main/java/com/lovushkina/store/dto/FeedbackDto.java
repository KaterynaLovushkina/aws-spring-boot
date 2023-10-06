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
@Relation(itemRelation = "feedback", collectionRelation = "feedbacks")
public class FeedbackDto extends RepresentationModel<AppDto> {
    private final Integer id;
    private final String description;
    private final Date createDate;
    private final Float rate;

    private final String userPasswordHash;
    private final String userFullName;
    private final String userCountry;

    private final String appName;
    private final String appDescription;
    private final Integer appWeightInMb;
    private final Date appCreateDate;
    private final Boolean appIsFree;
    private final BigDecimal appPriceInDollars;
    private final Boolean appHasAdvert;
    private final String appAudienceType;
}