package com.lovushkina.store.dto.assembler;

import com.lovushkina.store.controller.DownloadController;
import com.lovushkina.store.domain.Download;
import com.lovushkina.store.dto.DownloadDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DownloadDtoAssembler implements RepresentationModelAssembler<Download, DownloadDto> {
    @Override
    public DownloadDto toModel(Download entity) {
        DownloadDto appDto = DownloadDto.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .totalPrice(entity.getTotalPrice())
                .userId(entity.getUser().getId())
                .build();
        Link selfLink = linkTo(methodOn(DownloadController.class).getDownload(appDto.getId())).withSelfRel();
        appDto.add(selfLink);
        return appDto;
    }

    @Override
    public CollectionModel<DownloadDto> toCollectionModel(Iterable<? extends Download> entities) {
        CollectionModel<DownloadDto> downloadDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DownloadController.class).getAllDownloads()).withSelfRel();
        downloadDtos.add(selfLink);
        return downloadDtos;
    }

    public CollectionModel<DownloadDto> toCollectionModel(Iterable<? extends Download> entities, Link link) {
        CollectionModel<DownloadDto> downloadDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        downloadDtos.add(link);
        return downloadDtos;
    }

}
