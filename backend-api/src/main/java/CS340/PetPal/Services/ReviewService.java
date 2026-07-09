package CS340.PetPal.Services;


import java.util.List;

import org.springframework.stereotype.Service;

import CS340.PetPal.Entity.Review;
import CS340.PetPal.Repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Customer side 
    public Review createReview(Review review) {
        review.setProviderResponse(null);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByCustomerId(Long customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    // Provider side
    public Review respondToReview(Long reviewId, String responseText) {
        return reviewRepository.findById(reviewId).map(existingReview -> {
            
            // Attach the provider's reply to the existing review
            existingReview.setProviderResponse(responseText);
            
            // Save it back to the database. 
            // Since the ID already exists, JPA knows to UPDATE instead of INSERT.
            return reviewRepository.save(existingReview);
            
        }).orElseThrow(() -> new RuntimeException("Review not found with ID: " + reviewId));
    }

    public List<Review> getReviewsByProviderId(Long providerId) {
        return reviewRepository.findByProviderId(providerId);
    }
}