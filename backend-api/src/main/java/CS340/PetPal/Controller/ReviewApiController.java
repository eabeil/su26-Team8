package CS340.PetPal.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CS340.PetPal.Service.ReviewService;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Dto.ReviewCreateDto;
import CS340.PetPal.Dto.ReviewEditCommentDto;
import CS340.PetPal.Dto.ReviewEditResponseDto;
import CS340.PetPal.Dto.ReviewRespondDto;

@RestController
@RequestMapping("/api/reviews")
public class ReviewApiController {

    private final ReviewService reviewService;

    public ReviewApiController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // get reviews
    @GetMapping({ "/", "" })
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = this.reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    // get review
    @GetMapping({ "/{id}/", "/{id}" })
    public ResponseEntity<Review> getReviewById(@PathVariable("id") Long reviewId) {
        Review review = this.reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(review);
    }

    // create review
    @PostMapping({ "/", "" })
    public ResponseEntity<Review> createReview(@RequestBody ReviewCreateDto dto) {
        Review review = this.reviewService.createReview(dto);
        URI location = URI.create("/api/reviews/" + review.getId());
        return ResponseEntity.created(location).body(review);
    }

    // edit review comment
    @PutMapping({ "/{id}/", "/{id}" })
    public ResponseEntity<Review> editReviewComment(@PathVariable("id") Long reviewId,
            @RequestBody ReviewEditCommentDto dto) {
        Review review = this.reviewService.editReviewComment(reviewId, dto);
        return ResponseEntity.ok(review);
    }

    // post review response
    @PostMapping({ "/{id}/response/", "/{id}/response" })
    public ResponseEntity<Review> respondReview(@PathVariable("id") Long reviewId,
            @RequestBody ReviewRespondDto dto) {
        Review review = this.reviewService.respondReview(reviewId, dto);
        return ResponseEntity.ok(review);
    }

    // edit review response
    @PutMapping({ "/{id}/response/", "/{id}/response" })
    public ResponseEntity<Review> editReviewResponse(@PathVariable("id") Long reviewId,
            @RequestBody ReviewEditResponseDto dto) {
        Review review = this.reviewService.editReviewResponse(reviewId, dto);
        return ResponseEntity.ok(review);
    }

    // delete review
    @DeleteMapping({ "/{id}/", "/{id}" })
    public ResponseEntity<Void> deleteReview(@PathVariable("id") Long reviewId) {
        this.reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}