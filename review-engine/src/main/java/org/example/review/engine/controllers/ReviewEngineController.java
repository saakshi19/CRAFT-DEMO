package org.example.review.engine.controllers;

import org.example.review.engine.models.Review;
import org.example.review.engine.models.ReviewSummary;
import org.example.review.engine.services.ReviewEngineService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/review-engine/v1/")
public class ReviewEngineController {
    private final ReviewEngineService reviewEngineService;
    public ReviewEngineController(ReviewEngineService reviewEngineService) {
        this.reviewEngineService = reviewEngineService;
    }

    /**
     * Most frequently used API
     * @param productId
     * @return
     */
    @RequestMapping(
            value = "/{productId}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    ResponseEntity<List<Review>> getReviews(
            @PathVariable("productId") final Long productId
    ) {
        return ResponseEntity.ok(reviewEngineService.getReviewsForProduct(productId));
    }

    @RequestMapping(
            value = "add/{productId}",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    ResponseEntity<String> addProduct(
            @PathVariable("productId") final Long productId,
            @RequestBody Review request) {
        return ResponseEntity.ok(reviewEngineService.addReview(request));
    }

    @RequestMapping(
            value = "update/{reviewId}",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    ResponseEntity<String> updateReview(
            @PathVariable("reviewId") final String reviewId,
            @RequestBody Review request) {
        try {
            return ResponseEntity.ok(reviewEngineService.updateProductReview(reviewId, request));
        } catch (Exception ex) {
            // TODO: add logger
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(
            value = "/user/{userEmailId}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    ResponseEntity<List<Review>> getReviewsForUser(
            @PathVariable("userEmailId") final String userEmailId
    ) {
        return ResponseEntity.ok(reviewEngineService.getReviewsForUser(userEmailId));
    }

    @RequestMapping(
            value = "/reviewSummary/{productId}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    ResponseEntity<ReviewSummary> getReviewsForUser(
            @PathVariable("productId") final Long productId
    ) {
        return ResponseEntity.ok(reviewEngineService.getReviewSummaryForProduct(productId));
    }

    @RequestMapping(
            value = "all",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewEngineService.getAllReviews());
    }

    // TODO: Add upvotes and downvotes to reviews
}
