package com.lovushkina.store.controller;

import com.lovushkina.store.domain.EmailPreferences;
import com.lovushkina.store.dto.EmailPreferencesDto;
import com.lovushkina.store.dto.assembler.EmailPreferencesDtoAssembler;
import com.lovushkina.store.service.EmailPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "emailPreferences")
public class EmailPreferencesController {
    @Autowired
    private EmailPreferencesService emailPreferencesService;
    @Autowired
    private EmailPreferencesDtoAssembler emailPreferencesDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<EmailPreferencesDto>> getAllEmailPreferences() {
        List<EmailPreferences> emailPreferences = emailPreferencesService.findAll();
        CollectionModel<EmailPreferencesDto> appDtos = emailPreferencesDtoAssembler.toCollectionModel(emailPreferences);
        return new ResponseEntity<>(appDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<EmailPreferencesDto> getEmailPreferences(@PathVariable String email) {
        EmailPreferences appCategory = emailPreferencesService.findById(email);
        EmailPreferencesDto appCategoryDto = emailPreferencesDtoAssembler.toModel(appCategory);
        return new ResponseEntity<>(appCategoryDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<EmailPreferencesDto> addEmailPreferences(@RequestBody EmailPreferences emailPreferences) {
        EmailPreferences newEmailPreferences = emailPreferencesService.create(emailPreferences);
        EmailPreferencesDto emailPreferencesDto = emailPreferencesDtoAssembler.toModel(newEmailPreferences);
        return new ResponseEntity<>(emailPreferencesDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{email}")
    public ResponseEntity<?> updateAppCategory(@RequestBody EmailPreferences appCategory, @PathVariable String email) {
        emailPreferencesService.update(email, appCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<?> deleteEmailPreferences(@PathVariable String email) {
        emailPreferencesService.delete(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
