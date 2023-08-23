package org.example.review.engine.dao;

import org.example.review.engine.models.Review;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ReviewDao {
    private final Map<String, Review> reviews = new HashMap<>();
    public Review getReview(String reviewId) {
        return reviews.get(reviewId);
    }

    public Review addReview(Review review) {
        return reviews.put(review.getId(), review);
    }

    public void updateReview(String reviewId, Review review) {
        reviews.put(reviewId, review);
    }
}
