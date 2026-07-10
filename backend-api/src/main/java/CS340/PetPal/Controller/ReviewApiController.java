
package CS340.PetPal.Controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CS340.PetPal.Dto.ReviewDto;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewApiController {

    private final ReviewService reviewService;

    public ReviewApiController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Customer side
    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody Review review) {
        ReviewDto createdReview = reviewService.createReview(review);
        URI location = URI.create("/api/reviews/" + createdReview.getId());
        return ResponseEntity.created(location).body(createdReview);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReviewDto>> getCustomerReviews(@PathVariable Long customerId) {
        return ResponseEntity.ok(reviewService.getReviewsByCustomerId(customerId));
    }
    // Provider side
    @PutMapping("/{reviewId}/respond")
    public ResponseEntity<ReviewDto> respondToReview(
            @PathVariable Long reviewId, 
            @RequestBody Map<String, String> payload) {
        try {
            // Extract the string from the JSON payload
            String responseText = payload.get("response");
            
            // Send it to the Service layer (which now returns a DTO)
            ReviewDto updatedReview = reviewService.respondToReview(reviewId, responseText);
            return ResponseEntity.ok(updatedReview);
            
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<ReviewDto>> getProviderReviews(@PathVariable Long providerId) {
        return ResponseEntity.ok(reviewService.getReviewsByProviderId(providerId));
    }
}