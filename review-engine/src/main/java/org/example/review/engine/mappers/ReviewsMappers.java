package org.example.review.engine.mappers;

import org.example.review.engine.dao.models.Reviews;
import org.example.review.engine.models.Review;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ReviewsMappers {

    public Reviews reviewToReviewsDaoModel(Review review) {
        Reviews reviewsRecord = new Reviews();
        if (review.getId() != null) {
            reviewsRecord.setId(review.getId());
        }
        reviewsRecord.setProduct_id(review.getProductId());
        reviewsRecord.setUser_email_id(review.getUserEmailId());
        reviewsRecord.setRating((review.getOverallRating()));
        String metadata = reviewMetadataToReviewsDaoMetadata(review);
        reviewsRecord.setMetadata(metadata);
        return reviewsRecord;
    }

    public String reviewMetadataToReviewsDaoMetadata(Review review) {
        String reviewMetadata = review.getTextContent();
        if (review.getFileContext() != null) {
            reviewMetadata += "#|#" + getFileContentS3PresignedLink(review.getFileContext());
        }
        return reviewMetadata;
    }

    public String getFileContentS3PresignedLink(File file) {
        // TODO: Not yet implemented
        return file.getAbsolutePath();
    }

    public Review reviewsDaoModelToReviewModel(Reviews reviews) {
        Review review = new Review();
        review.setId(reviews.getId());
        review.setProductId(reviews.getProduct_id());
        review.setUserEmailId(reviews.getUser_email_id());
        review.setTextContent(reviews.getMetadata());
        review.setOverallRating(reviews.getRating());
        return review;
    }
}
