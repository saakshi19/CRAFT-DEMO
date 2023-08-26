package org.example.review.engine.services;

import org.example.review.engine.dao.models.ReviewSummary;
import org.example.review.engine.models.Product;
import org.example.review.engine.models.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewCalculator {

    public ReviewSummary calculateReview(Product product, Review review, ReviewSummary reviewSummary) throws Exception {
        if (product != null) {
            Float totalRating = ((review.getOverallRating()*reviewSummary.getTotalReviews()) + reviewSummary.getOverallRating()) / (reviewSummary.getTotalReviews()+1); // Average of the existing and new rating
            reviewSummary.setOverallRating(totalRating);
            reviewSummary.setTotalReviews(reviewSummary.getTotalReviews()+1);
            return reviewSummary;
        } else {
            throw new Exception("Product details not present");
        }
    }
}
