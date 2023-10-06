package com.lovushkina.store.repository;

import com.lovushkina.store.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    @Query("SELECT f FROM Feedback f WHERE f.rate>=?1")
    List<Feedback> getAllFeedbackWithRatingOrMore(Float rate);

    List<Feedback> getFeedbacksByUserId(Integer userId);

    List<Feedback> getFeedbacksByAppId(Integer userId);
}
