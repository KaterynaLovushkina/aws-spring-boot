package com.lovushkina.store.controller;

import com.lovushkina.store.domain.App;
import com.lovushkina.store.domain.Creater;
import com.lovushkina.store.dto.AppDto;
import com.lovushkina.store.dto.CreaterDto;
import com.lovushkina.store.dto.assembler.AppDtoAssembler;
import com.lovushkina.store.dto.assembler.CreaterDtoAssembler;
import com.lovushkina.store.service.CreaterService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "creaters")
public class CreaterController {
    @Autowired
    private CreaterService createrService;
    @Autowired
    private CreaterDtoAssembler createrDtoAssembler;
    @Autowired
    private AppDtoAssembler appDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CreaterDto>> getAllCreaters() {
        List<Creater> creaters = createrService.findAll();
        CollectionModel<CreaterDto> createrDtos = createrDtoAssembler.toCollectionModel(creaters);
        return new ResponseEntity<>(createrDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CreaterDto> getCreater(@PathVariable Integer id) {
        Creater creater = createrService.findById(id);
        CreaterDto createrDto = createrDtoAssembler.toModel(creater);
        return new ResponseEntity<>(createrDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CreaterDto> addCreater(@RequestBody Creater appCategory) {
        Creater newCreater = createrService.create(appCategory);
        CreaterDto createrDto = createrDtoAssembler.toModel(newCreater);
        return new ResponseEntity<>(createrDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCreater(@RequestBody Creater creater, @PathVariable Integer id) {
        createrService.update(id, creater);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{createrId}/apps")
    public ResponseEntity<CollectionModel<AppDto>> findAllAppsMadeByCreater(@PathVariable Integer createrId) {
        List<App> apps = createrService.getAppsByCreaterId(createrId);
        Link selfLink = linkTo(methodOn(CreaterController.class).findAllAppsMadeByCreater(createrId)).withSelfRel();
        CollectionModel<AppDto> appDtos = appDtoAssembler.toCollectionModel(apps, selfLink);
        return new ResponseEntity<>(appDtos, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCreater(@PathVariable Integer id) {
        createrService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/insertWithProcedure")
    public ResponseEntity<CreaterDto> insertCreaterWithProcedures(@RequestBody Creater creater) {
        Creater newCreater = createrService.insertCreaterWithProcedures(
                creater.getFullName(),
                creater.getWorkBranch(),
                creater.getEmail(),
                creater.getCreatedAppAmount());
        CreaterDto createrDto = createrDtoAssembler.toModel(newCreater);
        return new ResponseEntity<>(createrDto, HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(value = "/manyToManyRelationship")
    public ResponseEntity<?> addManyToManyRelationShip(@RequestBody JSONObject jsonObject) {
        createrService.addManyToManyRelationShip(jsonObject.getAsString("app_name"),
                jsonObject.getAsString("creater_full_name"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/maxAppCount")
    public ResponseEntity<Integer> findMaxAppCount() {
        Integer maxCount = createrService.findMaxAppCount();
        return new ResponseEntity<>(maxCount, HttpStatus.OK);
    }
}
