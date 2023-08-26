package org.example.review.engine.dao.models;

import jakarta.persistence.*;
import jdk.jfr.Category;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews", indexes = {
        @Index(name = "idx_product_id", columnList = "product_id"),
        @Index(name = "idx_user_email_id", columnList = "user_email_id")
})
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_email_id;
    @Column(name = "product_id")
    private Long productId;
    private float rating;
//    private ReviewMetadata metadata;
//    private CategoryRating category_rating;

    private String metadata;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getUser_email_id() {
        return user_email_id;
    }

    public Long getProduct_id() {
        return productId;
    }

    public float getRating() {
        return rating;
    }

    public String getMetadata() {
        return metadata;
    }

//    public CategoryRating getCategory_rating() {
//        return category_rating;
//    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser_email_id(String user_email_id) {
        this.user_email_id = user_email_id;
    }

    public void setProduct_id(Long product_id) {
        this.productId = product_id;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

//    public void setCategory_rating(CategoryRating category_rating) {
//        this.category_rating = category_rating;
//    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }
}

