package org.example.controllers;

import org.example.models.Review;
import org.example.services.ReviewEngineService;
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
    ResponseEntity<String> updateProduct(
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

    // TODO: Add upvotes and downvotes to reviews
}
