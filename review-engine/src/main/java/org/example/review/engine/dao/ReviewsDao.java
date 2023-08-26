package org.example.review.engine.dao;

import org.example.review.engine.dao.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsDao extends JpaRepository<Reviews, Long> {
    List<Reviews> findByProductId(Long productId);
}
