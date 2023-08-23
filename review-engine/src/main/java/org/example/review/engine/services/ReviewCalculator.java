package org.example.review.engine.services;

import org.example.product.models.Product;
import org.example.review.engine.models.Review;
import org.example.review.engine.models.ReviewSummary;
import org.springframework.stereotype.Service;

@Service
public class ReviewCalculator {

    public ReviewSummary calculateReview(Product product, Review review, ReviewSummary reviewSummary) throws Exception {
        if (product != null) {
            Double totalRating = (review.getOverallRating() + reviewSummary.getOverallRating()) / 5; // Average of the existing and new rating
            reviewSummary.setOverallRating(totalRating);
            reviewSummary.setTotalReviews(reviewSummary.getTotalReviews()+1);
            return reviewSummary;
        } else {
            throw new Exception("Product details not present");
        }
    }
}
