package com.lovushkina.store.controller;

import com.lovushkina.store.domain.App;
import com.lovushkina.store.dto.AppDto;
import com.lovushkina.store.dto.CategoryDto;
import com.lovushkina.store.dto.assembler.AppDtoAssembler;
import com.lovushkina.store.service.AppService;
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
@RequestMapping(value = "apps")
public class AppController {
    @Autowired
    private AppService appService;
    @Autowired
    private AppDtoAssembler appDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<AppDto>> getAllApps() {
        List<App> apps = appService.findAll();
        CollectionModel<AppDto> appDtos = appDtoAssembler.toCollectionModel(apps);
        return new ResponseEntity<>(appDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AppDto> getApp(@PathVariable Integer id) {
        App app = appService.findById(id);
        AppDto appDto = appDtoAssembler.toModel(app);
        return new ResponseEntity<>(appDto, HttpStatus.OK);
    }

    @PostMapping(value = "/{categoryId}")
    public ResponseEntity<AppDto> addApp(@RequestBody App app, @PathVariable Integer categoryId) {
        App newApp = appService.create(app, categoryId);
        AppDto appDto = appDtoAssembler.toModel(newApp);
        return new ResponseEntity<>(appDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/{categoryId}")
    public ResponseEntity<?> updateApp(@RequestBody App app, @PathVariable Integer id,
            @PathVariable Integer categoryId) {
        appService.update(id, app, categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteApp(@PathVariable Integer id) {
        appService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "categories/{categoryId}")
    public ResponseEntity<CollectionModel<AppDto>> findAppsByCategoryId(@PathVariable Integer categoryId) {
        List<App> apps = appService.getAppsByCategoryId(categoryId);
        Link selfLink = linkTo(methodOn(AppController.class).findAppsByCategoryId(categoryId)).withSelfRel();
        CollectionModel<AppDto> appDtos = appDtoAssembler.toCollectionModel(apps, selfLink);
        return new ResponseEntity<>(appDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/freeApps")
    public ResponseEntity<CollectionModel<AppDto>> findAllFreeApps() {
        List<App> apps = appService.getAllFreeApps();
        Link selfLink = linkTo(methodOn(AppController.class).findAllFreeApps()).withSelfRel();
        CollectionModel<AppDto> appDtos = appDtoAssembler.toCollectionModel(apps, selfLink);
        return new ResponseEntity<>(appDtos, HttpStatus.OK);
    }

}
