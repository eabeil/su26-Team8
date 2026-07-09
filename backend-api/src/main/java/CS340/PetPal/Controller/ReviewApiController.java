
package CS340.PetPal.Controller;

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
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.createReview(review));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Review>> getCustomerReviews(@PathVariable Long customerId) {
        return ResponseEntity.ok(reviewService.getReviewsByCustomerId(customerId));
    }

    // Provider side
    @PutMapping("/{reviewId}/respond")
    public ResponseEntity<Review> respondToReview(
            @PathVariable Long reviewId, 
            @RequestBody Map<String, String> payload) {
        try {
            // Extract the string from the JSON payload
            String responseText = payload.get("response");
            
            // Send it to the Service layer
            Review updatedReview = reviewService.respondToReview(reviewId, responseText);
            return ResponseEntity.ok(updatedReview);
            
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<Review>> getProviderReviews(@PathVariable Long providerId) {
        return ResponseEntity.ok(reviewService.getReviewsByProviderId(providerId));
    }
}