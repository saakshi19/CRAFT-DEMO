package org.example.services;

import org.example.dao.ProductReviewDao;
import org.example.dao.ReviewDao;
import org.example.dao.UserReviewDao;
import org.example.product.models.Product;
import org.example.models.Review;
import org.example.models.ReviewSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewEngineService {

    static final Logger logger = LoggerFactory.getLogger(String.valueOf(ReviewEngineService.class));
    private final ReviewDao reviewDao;
    private final UserReviewDao userReviewDao;
    private final ProductReviewDao productReviewDao;

    private final ReviewCalculator reviewCalculator;

    public ReviewEngineService(ReviewDao reviewDao, UserReviewDao userReviewDao, ProductReviewDao productReviewDao, ReviewCalculator reviewCalculator) {
        this.reviewDao = reviewDao;
        this.userReviewDao = userReviewDao;
        this.productReviewDao = productReviewDao;
        this.reviewCalculator = reviewCalculator;
    }

    public List<Review> getReviewsForProduct(Long productId) {
        /**
         * TODO
         * get review from cache else DB
         */
        return productReviewDao.getReviewsForProduct(productId);
    }

    public String addReview(Review review) {
        try {
            /**
             * TODO
             * 1. Add review in reviews table
             * 2. get product details from product module
             * 3. call reviewCalculator to calculate review
             * 4. Store review
             * 5. refresh cache
             */
            String reviewId = UUID.randomUUID().toString();
            review.setId(reviewId);
            productReviewDao.addProductReview(review);
            reviewDao.addReview(review);
            userReviewDao.addReview(review);
            Long productId = review.getProductId();

            // TODO call product module to get product details
            Product product = null;
            try {

                logger.info("Trying to connect to product service");

                WebClient webClient = WebClient.builder().build();
                product = webClient.get()
                        .uri("http://localhost:8082/product/v1/" + productId)
                        .retrieve()
                        .bodyToMono(Product.class)
                        .block();
                logger.info("Got product " + product);

            } catch (Exception ex) {
                logger.info("Failed to get product with id " + productId);
            }

            ReviewSummary reviewSummary = productReviewDao.getProductReviewSummary(productId);
            ReviewSummary revisedReviewSummary = reviewCalculator.calculateReview(product, review, reviewSummary);
            productReviewDao.addOrUpdateProductReviewSummary(productId, revisedReviewSummary);
            // TODO: Refresh Cache with this value
            return "Thank you for your review!";
        } catch (Exception ex) {
            return "Something went wrong. Please try again!";
        }
    }

    public String updateProductReview(String reviewId, Review updatedReview) {
        try {
            reviewDao.updateReview(reviewId, updatedReview);
            productReviewDao.updateReview(reviewId, updatedReview);
            return "Thank you for your review!";
        } catch (Exception ex) {
            return "Something went wrong. Please try again!";
        }

    }

    public List<Review> getReviewsForUser(String userEmailId) {
        return userReviewDao.getReviews(userEmailId);
    }
}
