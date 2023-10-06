package com.lovushkina.store.controller;

import com.lovushkina.store.domain.App;
import com.lovushkina.store.domain.Download;
import com.lovushkina.store.dto.AppDto;
import com.lovushkina.store.dto.DownloadDto;
import com.lovushkina.store.dto.assembler.AppDtoAssembler;
import com.lovushkina.store.dto.assembler.DownloadDtoAssembler;
import com.lovushkina.store.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "downloads")
public class DownloadController {
    @Autowired
    private DownloadService downloadService;
    @Autowired
    private DownloadDtoAssembler downloadDtoAssembler;
    @Autowired
    private AppDtoAssembler appDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<DownloadDto>> getAllDownloads() {
        List<Download> downloads = downloadService.findAll();
        CollectionModel<DownloadDto> downloadDtos = downloadDtoAssembler.toCollectionModel(downloads);
        return new ResponseEntity<>(downloadDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DownloadDto> getDownload(@PathVariable Integer id) {
        Download download = downloadService.findById(id);
        DownloadDto downloadDto = downloadDtoAssembler.toModel(download);
        return new ResponseEntity<>(downloadDto, HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}")
    public ResponseEntity<DownloadDto> addDownload(@RequestBody Download app, @PathVariable Integer userId) {
        Download newDownload = downloadService.create(app, userId);
        DownloadDto downloadDto = downloadDtoAssembler.toModel(newDownload);
        return new ResponseEntity<>(downloadDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/{userId}")
    public ResponseEntity<?> updateDownload(@RequestBody Download download, @PathVariable Integer id,
            @PathVariable Integer userId) {
        downloadService.update(id, download, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{downloadId}/apps")
    public ResponseEntity<CollectionModel<AppDto>> findAllDownloadedApps(@PathVariable Integer downloadId) {
        List<App> downloads = downloadService.getAppsByDownloadId(downloadId);
        Link selfLink = linkTo(methodOn(CreaterController.class).findAllAppsMadeByCreater(downloadId)).withSelfRel();
        CollectionModel<AppDto> appDtos = appDtoAssembler.toCollectionModel(downloads, selfLink);
        return new ResponseEntity<>(appDtos, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteDownload(@PathVariable Integer id) {
        downloadService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
