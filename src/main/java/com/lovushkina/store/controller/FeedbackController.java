package com.lovushkina.store.controller;

import com.lovushkina.store.domain.Feedback;
import com.lovushkina.store.dto.FeedbackDto;
import com.lovushkina.store.dto.assembler.FeedbackDtoAssembler;
import com.lovushkina.store.service.FeedbackService;
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
@RequestMapping(value = "feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FeedbackDtoAssembler feedbackDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<FeedbackDto>> getAllFeedbacks() {
        List<Feedback> apps = feedbackService.findAll();
        CollectionModel<FeedbackDto> appDtos = feedbackDtoAssembler.toCollectionModel(apps);
        return new ResponseEntity<>(appDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FeedbackDto> getFeedback(@PathVariable Integer id) {
        Feedback app = feedbackService.findById(id);
        FeedbackDto appDto = feedbackDtoAssembler.toModel(app);
        return new ResponseEntity<>(appDto, HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}/{appId}")
    public ResponseEntity<FeedbackDto> addFeedback(@RequestBody Feedback feedback, @PathVariable Integer userId,
            @PathVariable Integer appId) {
        Feedback newApp = feedbackService.create(feedback, userId, appId);
        FeedbackDto appDto = feedbackDtoAssembler.toModel(newApp);
        return new ResponseEntity<>(appDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/{userId}/{appId}")
    public ResponseEntity<?> updateFeedback(@RequestBody Feedback feedback, @PathVariable Integer id,
            @PathVariable Integer userId, @PathVariable Integer appId) {
        feedbackService.update(id, feedback, userId, appId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Integer id) {
        feedbackService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "users/{userId}")
    public ResponseEntity<CollectionModel<FeedbackDto>> findFeedbacksByUserId(@PathVariable Integer userId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByUserId(userId);
        Link selfLink = linkTo(methodOn(FeedbackController.class).findFeedbacksByUserId(userId)).withSelfRel();
        CollectionModel<FeedbackDto> feedbackDtos = feedbackDtoAssembler.toCollectionModel(feedbacks, selfLink);
        return new ResponseEntity<>(feedbackDtos, HttpStatus.OK);
    }

    @GetMapping(value = "apps/{appId}")
    public ResponseEntity<CollectionModel<FeedbackDto>> findFeedbacksByAppId(@PathVariable Integer appId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByAppId(appId);
        Link selfLink = linkTo(methodOn(FeedbackController.class).findFeedbacksByAppId(appId)).withSelfRel();
        CollectionModel<FeedbackDto> feedbackDtos = feedbackDtoAssembler.toCollectionModel(feedbacks, selfLink);
        return new ResponseEntity<>(feedbackDtos, HttpStatus.OK);
    }

    @GetMapping(value = "rating/{rate}")
    public ResponseEntity<CollectionModel<FeedbackDto>> findFeedbacksWithRatingOrMore(@PathVariable Float rate) {
        List<Feedback> feedbacks = feedbackService.getAllFeedbackWithRatingOrMore(rate);
        Link selfLink = linkTo(methodOn(FeedbackController.class).findFeedbacksWithRatingOrMore(rate)).withSelfRel();
        CollectionModel<FeedbackDto> feedbackDtos = feedbackDtoAssembler.toCollectionModel(feedbacks, selfLink);
        return new ResponseEntity<>(feedbackDtos, HttpStatus.OK);
    }

}
