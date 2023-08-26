package reviewTest;

import org.example.review.engine.dao.*;
import org.example.review.engine.dao.models.Reviews;
import org.example.review.engine.mappers.ReviewSummaryMappers;
import org.example.review.engine.mappers.ReviewsMappers;
import org.example.review.engine.models.Review;
import org.example.review.engine.services.ReviewEngineService;
import org.example.review.engine.models.ReviewSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReviewEngineServiceTest {

    @Mock
    private ReviewsDao reviewsDao;

    @Mock
    private ReviewsMappers reviewsMappers;

    @Mock
    private ReviewSummaryDao reviewSummaryDao;

    @Mock
    private ReviewSummaryMappers reviewSummaryMappers;

    @InjectMocks
    private ReviewEngineService reviewEngineService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReviewsForProduct() {
        // Arrange
        Long productId = 1L;
        List<Reviews> reviewsList = new ArrayList<>();
        Reviews review = new Reviews();
        review.setProduct_id(productId);
        reviewsList.add(review);
        when(reviewsDao.findByProductId(productId)).thenReturn(reviewsList);

        Review expectedReview = new Review(); // Create your expected Review object

        // Mock the mapping behavior
        when(reviewsMappers.reviewsDaoModelToReviewModel(review)).thenReturn(expectedReview);

        List<Review> result = reviewEngineService.getReviewsForProduct(productId);

        assertEquals(1, result.size());
        assertEquals(expectedReview, result.get(0));

        // Verify interactions
//        verify(reviewsDao).findByProductId(productId);
//        verify(reviewsMappers).reviewsDaoModelToReviewModel(review);
    }

    @Test
    public void testGetReviewSummaryForProduct() {
        Long productId = 1L;
        org.example.review.engine.dao.models.ReviewSummary reviewSummary = new org.example.review.engine.dao.models.ReviewSummary();
        reviewSummary.setProductId(productId);
        when(reviewSummaryDao.findByProductId(productId)).thenReturn(reviewSummary);
        // Create expected ReviewSummary object
        ReviewSummary expectedReviewSummary = new ReviewSummary(
                1L,
                4.5F,
                1L,
                "INDIA",
                0,
                0
        );

        when(reviewSummaryMappers.reviewSummaryRecordToReviewSummaryModel(reviewSummary)).thenReturn(expectedReviewSummary);
        ReviewSummary result = reviewEngineService.getReviewSummaryForProduct(productId);
        assertEquals(expectedReviewSummary, result);
        // Verify interactions
        verify(reviewSummaryDao).findByProductId(productId);
        verify(reviewSummaryMappers).reviewSummaryRecordToReviewSummaryModel(reviewSummary);
    }
}

