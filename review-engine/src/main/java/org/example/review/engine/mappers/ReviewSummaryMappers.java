package org.example.review.engine.mappers;

import org.example.review.engine.models.ReviewSummary;
import org.springframework.stereotype.Component;

@Component
public class ReviewSummaryMappers {

    public ReviewSummary reviewSummaryRecordToReviewSummaryModel(org.example.review.engine.dao.models.ReviewSummary reviewSummaryRecord){
        return new ReviewSummary(
                reviewSummaryRecord.getProductId(),
                reviewSummaryRecord.getOverallRating(),
                reviewSummaryRecord.getTotalReviews(),
                reviewSummaryRecord.getReviewRegion(),
                reviewSummaryRecord.getUpvotes(),
                reviewSummaryRecord.getDownvotes()
        );
    }
}
