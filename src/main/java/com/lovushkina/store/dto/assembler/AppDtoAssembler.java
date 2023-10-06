package com.lovushkina.store.dto.assembler;

import com.lovushkina.store.controller.AppController;
import com.lovushkina.store.domain.App;
import com.lovushkina.store.dto.AppDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AppDtoAssembler implements RepresentationModelAssembler<App, AppDto> {
    @Override
    public AppDto toModel(App entity) {
        AppDto appDto = AppDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .weightInMb(entity.getWeightInMb())
                .createDate(entity.getCreateDate())
                .isFree(entity.getIsFree())
                .priceInDollars(entity.getPriceInDollars())
                .hasAdvert(entity.getHasAdvert())
                .audienceType(entity.getCategory().getAudienceType())
                .build();
        Link selfLink = linkTo(methodOn(AppController.class).getApp(appDto.getId())).withSelfRel();
        appDto.add(selfLink);
        return appDto;
    }

    @Override
    public CollectionModel<AppDto> toCollectionModel(Iterable<? extends App> entities) {
        CollectionModel<AppDto> appDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(AppController.class).getAllApps()).withSelfRel();
        appDtos.add(selfLink);
        return appDtos;
    }

    public CollectionModel<AppDto> toCollectionModel(Iterable<? extends App> entities, Link link) {
        CollectionModel<AppDto> appDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        appDtos.add(link);
        return appDtos;
    }

}
