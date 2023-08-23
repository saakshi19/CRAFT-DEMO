package org.example.dao;

import org.example.models.Review;
import org.example.models.ReviewSummary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductReviewDao {
    private final Map<Long, List<Review>> productReviews = new HashMap<>();
    private final Map<Long, ReviewSummary> productReviewSummary = new HashMap<>();

    public List<Review> getReviewsForProduct(Long productId) {
        return productReviews.get(productId);
    }

    public void addProductReview(Review productReview) {
        Long productId = productReview.getProductId();
        List<Review> existingReviews = productReviews.getOrDefault(productId, new ArrayList<>());
        // Add the new review to the existing list
        existingReviews.add(productReview);
        // Update the map with the new list of reviews
        productReviews.put(productId, existingReviews);
    }

    public void updateReview(String reviewId, Review updatedReview) {
        List<Review> reviews = productReviews.get(updatedReview.getProductId());
        for(Review review: reviews) {
            if (review.getId().equals(reviewId)) {
                reviews.remove(review);
                reviews.add(updatedReview);
            }
        }
    }

    public ReviewSummary getProductReviewSummary(Long productId) {
        return productReviewSummary.get(productId);
    }

    public void addOrUpdateProductReviewSummary(Long productId, ReviewSummary reviewSummary) {
        productReviewSummary.put(productId, reviewSummary);
    }
}
