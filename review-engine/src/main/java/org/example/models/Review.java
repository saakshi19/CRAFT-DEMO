package org.example.models;

import lombok.AllArgsConstructor;

import java.io.File;
import java.util.HashMap;

@AllArgsConstructor
public class Review {
    private String id; // Random UUID
    private Long productId;
    private String userEmailId;
    private String textContent;
    private File fileContext;
    private Double overallRating;
    private HashMap<String, Double> categoryRating;
    private String reviewRegion; // Optional since user region is already stored. Can be used to calculate rating
    private int upvotes;
    private int downvotes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Long getProductId() {
        return productId;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public String getTextContent() {
        return textContent;
    }

    public File getFileContext() {
        return fileContext;
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


    public void setFileContext(File fileContext) {
        this.fileContext = fileContext;
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

