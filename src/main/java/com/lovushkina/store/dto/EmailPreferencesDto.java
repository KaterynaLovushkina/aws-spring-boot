package com.lovushkina.store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lovushkina.store.domain.EmailPreferences;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "emailPreferences", collectionRelation = "emailPreferencess")
public class EmailPreferencesDto extends RepresentationModel<EmailPreferences> {
    private String email;
    private Boolean keepWithUpToDateNews;
    private Boolean receiveReplyNotification;
    private Integer userId;
}
