package org.example.review.engine.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.review.engine.dao.*;
import org.example.review.engine.dao.models.Reviews;
import org.example.review.engine.mappers.ReviewSummaryMappers;
import org.example.review.engine.mappers.ReviewsMappers;
import org.example.review.engine.models.Product;
import org.example.review.engine.models.Review;
import org.example.review.engine.models.ReviewSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

import static org.example.review.engine.constants.Endpoints.productServiceEndpoint;

@Service
public class ReviewEngineService {

    static final Logger logger = LoggerFactory.getLogger(String.valueOf(ReviewEngineService.class));
    private final ReviewDao reviewDao;
    private final UserReviewDao userReviewDao;
    private final ProductReviewDao productReviewDao;

    private final ReviewCalculator reviewCalculator;

    private final ReviewsDao reviewsDao;

    private final ReviewSummaryDao reviewSummaryDao;

    private final ReviewsMappers reviewsMappers;
    private final ReviewSummaryMappers reviewSummaryMappers;

    public ReviewEngineService(ReviewDao reviewDao, UserReviewDao userReviewDao, ProductReviewDao productReviewDao, ReviewCalculator reviewCalculator, ReviewsDao reviewsDao, ReviewSummaryDao reviewSummaryDao, ReviewsMappers reviewsMappers, ReviewSummaryMappers reviewSummaryMappers) {
        this.reviewDao = reviewDao;
        this.userReviewDao = userReviewDao;
        this.productReviewDao = productReviewDao;
        this.reviewCalculator = reviewCalculator;
        this.reviewsDao = reviewsDao;
        this.reviewSummaryDao = reviewSummaryDao;
        this.reviewsMappers = reviewsMappers;
        this.reviewSummaryMappers = reviewSummaryMappers;
    }

    public List<Review> getReviewsForProduct(Long productId) {
        /**
         * TODO: get review from cache else DB
         */
        logger.info(reviewsDao.findByProductId(productId).toString());
        List<Reviews> reviews = reviewsDao.findByProductId(productId);
        List<Review> response = new ArrayList<>();
        for (Reviews review: reviews) {
            response.add(reviewsMappers.reviewsDaoModelToReviewModel(review));
        }
        return response;
    }

    public String addReview(Review review) {
        try {
//            String reviewId = UUID.randomUUID().toString();
//            review.setId(reviewId);
//            productReviewDao.addProductReview(review);
//            reviewDao.addReview(review);
//            userReviewDao.addReview(review);
            Long productId = review.getProductId();

            logger.info("Adding review for product " + review.getProductId()+ " by " + review.getUserEmailId() + " :"  +review);

            try {
                Reviews reviewsRecord = reviewsMappers.reviewToReviewsDaoModel(review); // new Reviews();
//                reviewsRecord.setProduct_id(productId);
//                reviewsRecord.setUser_email_id(review.getUserEmailId());
//                reviewsRecord.setRating((review.getOverallRating()));
//                reviewsRecord.setMetadata(review.getTextContent());

                reviewsDao.save(reviewsRecord);
                logger.info("Review added in db " + reviewsRecord);
            } catch (Exception exx) {
                logger.error("Exception in adding entry to the db due to " + exx.getMessage());
                throw exx;
            }

            // call product module to get product details
            Product product = null;
            try {

                logger.info("Trying to connect to product service");

                WebClient webClient = WebClient.builder().build();

                String productServiceEndpointUri = productServiceEndpoint;

                String productResponseString = webClient.get()
                        .uri("http://localhost:8082/product/v1/" + productId)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
                logger.info(productResponseString);

                ObjectMapper objectMapper = new ObjectMapper();
                product = objectMapper.readValue(productResponseString, Product.class);
                logger.info("Product is " + product);

            } catch (Exception ex) {
                logger.info("Failed to get product with id " + productId + "due to " + ex.getMessage());
            }

//            ReviewSummary reviewSummary1 = productReviewDao.getProductReviewSummary(productId);
            org.example.review.engine.dao.models.ReviewSummary reviewSummary = reviewSummaryDao.findByProductId(productId);
            if (reviewSummary == null) {
                addFirstReviewSummary(review);
//                productReviewDao.addFirstReviewSummary(product, review);
            } else {
                org.example.review.engine.dao.models.ReviewSummary revisedReviewSummary = reviewCalculator.calculateReview(product, review, reviewSummary);
                reviewSummaryDao.save(reviewSummary);
//                productReviewDao.addOrUpdateProductReviewSummary(productId, revisedReviewSummary);
            }
            // TODO: Refresh Cache with this value
            return "Thank you for your review!";
        } catch (Exception ex) {
            logger.error("due to " + ex.getMessage());
            return "Something went wrong. Please try again!";
        }
    }

    private org.example.review.engine.dao.models.ReviewSummary addFirstReviewSummary(Review review) {
        org.example.review.engine.dao.models.ReviewSummary reviewSummary = new org.example.review.engine.dao.models.ReviewSummary();
        reviewSummary.setProductId(review.getProductId());
        reviewSummary.setTotalReviews(1L);
        reviewSummary.setReviewRegion(review.getReviewRegion());
        reviewSummary.setOverallRating(review.getOverallRating());
        reviewSummaryDao.save(reviewSummary);
        return reviewSummary;
    }

    public String updateProductReview(String reviewId, Review updatedReview) {
        try {
//            reviewDao.updateReview(reviewId, updatedReview);
//            productReviewDao.updateReview(reviewId, updatedReview);
            Reviews reviewsRecord = reviewsMappers.reviewToReviewsDaoModel(updatedReview);
            reviewsDao.save(reviewsRecord);
            return "Thank you for your review!";
        } catch (Exception ex) {
            return "Something went wrong. Please try again!";
        }

    }

    public List<Review> getReviewsForUser(String userEmailId) {
        return userReviewDao.getReviews(userEmailId);
    }

    public ReviewSummary getReviewSummaryForProduct(Long productId) {
        org.example.review.engine.dao.models.ReviewSummary reviewSummaryRecord = reviewSummaryDao.findByProductId(productId);
        return reviewSummaryMappers.reviewSummaryRecordToReviewSummaryModel(reviewSummaryRecord);
//        return productReviewDao.getProductReviewSummary(productId);
    }

    public List<Review> getAllReviews() {
        List<Reviews> reviewRecords = reviewsDao.findAll();
        List<Review> reviewList = new ArrayList<>();
        for (Reviews review: reviewRecords) {
            reviewList.add(reviewsMappers.reviewsDaoModelToReviewModel(review));
        }
        return reviewList;
    }
}
