package com.lovushkina.store.service;

import com.lovushkina.store.domain.Feedback;

import java.util.List;

public interface FeedbackService extends GeneralService<Feedback, Integer> {
    Feedback create(Feedback feedback, Integer userId, Integer appId);

    void update(Integer feedbackId, Feedback feedback, Integer userId, Integer appId);

    List<Feedback> getFeedbacksByUserId(Integer userId);

    List<Feedback> getFeedbacksByAppId(Integer userId);

    List<Feedback> getAllFeedbackWithRatingOrMore(Float rate);
}
