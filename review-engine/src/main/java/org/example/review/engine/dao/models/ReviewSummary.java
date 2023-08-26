package org.example.review.engine.dao.models;

import jakarta.persistence.*;

@Entity
@Table(name = "review_summary", indexes = {
        @Index(name = "idx_review_summary_product_id", columnList = "product_id")
})
public class ReviewSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "overall_rating")
    private Float overallRating;
    @Column(name = "total_reviews")
    private Long totalReviews;

    //    private HashMap<String, Double> categoryRating;
    @Column(name = "review_region")
    private String reviewRegion; // Optional since user region is already stored. Can be used to calculate rating
    private int upvotes;
    private int downvotes;

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Float getOverallRating() {
        return overallRating;
    }

    public Long getTotalReviews() {
        return totalReviews;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setOverallRating(Float overallRating) {
        this.overallRating = overallRating;
    }

    public void setTotalReviews(Long totalReviews) {
        this.totalReviews = totalReviews;
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
