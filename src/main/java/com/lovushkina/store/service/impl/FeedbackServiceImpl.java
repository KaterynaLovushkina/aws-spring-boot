package com.lovushkina.store.service.impl;

import com.lovushkina.store.domain.*;
import com.lovushkina.store.domain.Feedback;
import com.lovushkina.store.exception.DataNotFoundException;
import com.lovushkina.store.repository.AppRepository;
import com.lovushkina.store.repository.FeedbackRepository;
import com.lovushkina.store.repository.UserRepository;
import com.lovushkina.store.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    final FeedbackRepository feedbackRepository;

    @Autowired
    final UserRepository userRepository;

    @Autowired
    final AppRepository appRepository;

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback findById(Integer id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new DataNotFoundException("AppCategory", id));
    }

    @Override
    public Feedback create(Feedback entity) {
        feedbackRepository.save(entity);
        return entity;
    }

    @Override
    public Feedback create(Feedback feedback, Integer userId, Integer appId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User", userId));
        App app = appRepository.findById(appId).orElseThrow(() -> new DataNotFoundException("App", appId));
        feedback.setUser(user);
        feedback.setApp(app);
        return feedbackRepository.save(feedback);
    }

    @Override
    public void update(Integer id, Feedback entity) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Feedback", id));
        feedback.setDescription(entity.getDescription());
        feedback.setCreatedDate(entity.getCreatedDate());
        feedback.setRate(entity.getRate());

        feedbackRepository.save(feedback);
    }

    @Override
    public void update(Integer feedbackId, Feedback entity, Integer userId, Integer appId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new DataNotFoundException("Feedback", feedbackId));
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User", userId));
        App app = appRepository.findById(appId).orElseThrow(() -> new DataNotFoundException("App", appId));

        feedback.setDescription(entity.getDescription());
        feedback.setCreatedDate(entity.getCreatedDate());
        feedback.setRate(entity.getRate());
        feedback.setUser(user);
        feedback.setApp(app);

        feedbackRepository.save(feedback);

    }

    @Override
    public List<Feedback> getFeedbacksByUserId(Integer userId) {
        return feedbackRepository.getFeedbacksByUserId(userId);
    }

    @Override
    public List<Feedback> getFeedbacksByAppId(Integer appId) {
        return feedbackRepository.getFeedbacksByAppId(appId);
    }

    @Override
    public List<Feedback> getAllFeedbackWithRatingOrMore(Float rate) {
        return feedbackRepository.getAllFeedbackWithRatingOrMore(rate);
    }

    @Override
    public void delete(Integer id) {
        feedbackRepository.deleteById(id);
    }

}
