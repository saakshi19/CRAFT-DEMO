package org.example.review.engine.models;

import lombok.AllArgsConstructor;

import java.io.File;
import java.util.HashMap;

@AllArgsConstructor
public class ReviewSummary {
    private Long productId;
    private Double overallRating;
    private Long totalReviews;
    private HashMap<String, Double> categoryRating;
    private String reviewRegion; // Optional since user region is already stored. Can be used to calculate rating
    private int upvotes;
    private int downvotes;

    public Long getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(Long totalReviews) {
        this.totalReviews = totalReviews;
    }

    public Long getProductId() {
        return productId;
    }

    public Double getOverallRating() {
        return overallRating;
    }

    public HashMap<String, Double> getCategoryRating() {
        return categoryRating;
    }

    public String getReviewRegion() {
        return reviewRegion;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setOverallRating(Double overallRating) {
        this.overallRating = overallRating;
    }

    public void setCategoryRating(HashMap<String, Double> categoryRating) {
        this.categoryRating = categoryRating;
    }

    public void setReviewRegion(String reviewRegion) {
        this.reviewRegion = reviewRegion;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
}
