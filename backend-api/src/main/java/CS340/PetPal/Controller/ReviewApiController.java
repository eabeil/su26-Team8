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
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Service.ReviewService;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Dto.CreateReviewDto;
import CS340.PetPal.Dto.EditCommentReviewDto;
import CS340.PetPal.Dto.EditResponseReviewDto;
import CS340.PetPal.Dto.RespondReviewDto;

@RestController
@RequestMapping("/api/reviews")
public class ReviewApiController {

    private final ReviewService reviewService;

    public ReviewApiController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // get reviews
    @GetMapping("/")
    public ResponseEntity<List<Review>> getAllReviews() {
        try {
            List<Review> reviews = this.reviewService.getAllReviews();
            return ResponseEntity.ok(reviews);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // get review
    @GetMapping("/{id}/")
    public ResponseEntity<Review> getReviewById(@PathVariable("id") Long reviewId) {
        try {
            Review review = this.reviewService.getReviewById(reviewId);
            return ResponseEntity.ok(review);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // create review
    @PostMapping("/")
    public ResponseEntity<Review> createReview(@RequestBody CreateReviewDto dto) {
        try {
            Review review = this.reviewService.createReview(dto);
            URI location = URI.create("/api/reviews/" + review.getId());
            return ResponseEntity.created(location).body(review);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // edit review comment
    @PutMapping("/{id}/")
    public ResponseEntity<Review> editReviewComment(@PathVariable("id") Long reviewId,
            @RequestBody EditCommentReviewDto dto) {
        try {
            Review review = this.reviewService.editReviewComment(reviewId, dto);
            return ResponseEntity.ok(review);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // post review response
    @PostMapping("/{id}/response/")
    public ResponseEntity<Review> respondReview(@PathVariable("id") Long reviewId,
            @RequestBody RespondReviewDto dto) {
        try {
            Review review = this.reviewService.respondReview(reviewId, dto);
            return ResponseEntity.ok(review);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // edit review response
    @PutMapping("/{id}/response/")
    public ResponseEntity<Review> editReviewResponse(@PathVariable("id") Long reviewId,
            @RequestBody EditResponseReviewDto dto) {
        try {
            Review review = this.reviewService.editReviewResponse(reviewId, dto);
            return ResponseEntity.ok(review);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // delete review
    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") Long reviewId) {
        try {
            this.reviewService.deleteReview(reviewId);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
}