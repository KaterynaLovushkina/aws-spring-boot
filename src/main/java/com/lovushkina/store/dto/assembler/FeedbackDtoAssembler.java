package com.lovushkina.store.dto.assembler;

import com.lovushkina.store.controller.FeedbackController;
import com.lovushkina.store.domain.Feedback;
import com.lovushkina.store.dto.FeedbackDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FeedbackDtoAssembler implements RepresentationModelAssembler<Feedback, FeedbackDto> {
    @Override
    public FeedbackDto toModel(Feedback entity) {
        FeedbackDto feedbackDto = FeedbackDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .createDate(entity.getCreatedDate())
                .rate(entity.getRate())
                .userPasswordHash(entity.getUser().getPasswordHash())
                .userFullName(entity.getUser().getPasswordHash())
                .userCountry(entity.getUser().getCountry())
                .appName(entity.getApp().getName())
                .appDescription(entity.getApp().getDescription())
                .appWeightInMb(entity.getApp().getWeightInMb())
                .appCreateDate(entity.getApp().getCreateDate())
                .appIsFree(entity.getApp().getIsFree())
                .appPriceInDollars(entity.getApp().getPriceInDollars())
                .appHasAdvert(entity.getApp().getHasAdvert())
                .appAudienceType(entity.getApp().getCategory().getAudienceType())
                .build();
        Link selfLink = linkTo(methodOn(FeedbackController.class).getFeedback(feedbackDto.getId())).withSelfRel();
        feedbackDto.add(selfLink);
        return feedbackDto;
    }

    @Override
    public CollectionModel<FeedbackDto> toCollectionModel(Iterable<? extends Feedback> entities) {
        CollectionModel<FeedbackDto> feedbackDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(FeedbackController.class).getAllFeedbacks()).withSelfRel();
        feedbackDtos.add(selfLink);
        return feedbackDtos;
    }

    public CollectionModel<FeedbackDto> toCollectionModel(Iterable<? extends Feedback> entities, Link link) {
        CollectionModel<FeedbackDto> feedbackDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        feedbackDtos.add(link);
        return feedbackDtos;
    }

}
