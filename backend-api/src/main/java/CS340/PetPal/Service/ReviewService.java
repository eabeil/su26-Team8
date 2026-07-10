package CS340.PetPal.Service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import CS340.PetPal.Dto.ReviewDto;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Customer side 
    public ReviewDto createReview(Review review) {
        Review savedReview = reviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    public List<ReviewDto> getReviewsByCustomerId(Long customerId) {
        return reviewRepository.findByCustomerId(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    

    // Provider side
    public ReviewDto respondToReview(Long reviewId, String responseText) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));
        
        existingReview.setProviderResponse(responseText);
        Review updatedReview = reviewRepository.save(existingReview);
        
        return convertToDTO(updatedReview);
    }

    public List<ReviewDto> getReviewsByProviderId(Long providerId) {
        return reviewRepository.findByProviderId(providerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private ReviewDto convertToDTO(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setRecommended(review.getRecommended());
        dto.setCustomerComment(review.getCustomerComment());
        dto.setProviderResponse(review.getProviderResponse());
        dto.setCreatedAt(review.getCreatedAt());
        
        // Flatten the relationships! Just grab the IDs.
        if (review.getCustomer() != null) {
            dto.setCustomerId(review.getCustomer().getId());
        }
        if (review.getProvider() != null) {
            dto.setProviderId(review.getProvider().getId());
        }
        
        return dto;
    }
}    

