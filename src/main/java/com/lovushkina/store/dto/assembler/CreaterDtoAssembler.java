package com.lovushkina.store.dto.assembler;

import com.lovushkina.store.controller.CreaterController;
import com.lovushkina.store.domain.Creater;
import com.lovushkina.store.dto.CreaterDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CreaterDtoAssembler implements RepresentationModelAssembler<Creater, CreaterDto> {
    @Override
    public CreaterDto toModel(Creater entity) {
        CreaterDto createrDto = CreaterDto.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .workBranch(entity.getWorkBranch())
                .email(entity.getEmail())
                .createdAppAmount(entity.getCreatedAppAmount())
                .build();
        Link selfLink = linkTo(methodOn(CreaterController.class).getCreater(createrDto.getId())).withSelfRel();
        createrDto.add(selfLink);
        return createrDto;
    }

    @Override
    public CollectionModel<CreaterDto> toCollectionModel(Iterable<? extends Creater> entities) {
        CollectionModel<CreaterDto> createrDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CreaterController.class).getAllCreaters()).withSelfRel();
        createrDtos.add(selfLink);
        return createrDtos;
    }
}
