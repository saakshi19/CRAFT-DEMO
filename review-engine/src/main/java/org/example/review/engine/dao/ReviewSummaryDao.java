package org.example.review.engine.dao;

import org.example.review.engine.dao.models.ReviewSummary;
import org.example.review.engine.dao.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewSummaryDao  extends JpaRepository<ReviewSummary, Long> {
    ReviewSummary findByProductId(Long productId);
}