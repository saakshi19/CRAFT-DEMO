package org.example.review.engine.dao;

import org.example.review.engine.models.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserReviewDao {
    private final Map<String, List<Review>> userReviews = new HashMap<>();
    public List<Review> getReviews(String userEmailId) {
        return userReviews.get(userEmailId);
    }

    public void addReview(Review review) {
        String userId = review.getUserEmailId();
        List<Review> existingReviews = userReviews.getOrDefault(userId, new ArrayList<>());
        // Add the new review to the existing list
        existingReviews.add(review);
        // Update the map with the new list of reviews
        userReviews.put(userId, existingReviews);
    }

    public void updateReview(Review updatedReview) {
        List<Review> reviews = userReviews.get(updatedReview.getUserEmailId());
        for(Review review: reviews) {
            if (review.getId().equals(updatedReview.getId())) {
                reviews.remove(review);
                reviews.add(updatedReview);
            }
        }
    }
}
