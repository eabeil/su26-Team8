package CS340.PetPal.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Dto.ReviewCreateDto;
import CS340.PetPal.Dto.ReviewEditCommentDto;
import CS340.PetPal.Dto.ReviewEditResponseDto;
import CS340.PetPal.Dto.ReviewRespondDto;
import CS340.PetPal.Entity.Customer;
import CS340.PetPal.Entity.Provider;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Repository.CustomerRepository;
import CS340.PetPal.Repository.ProviderRepository;
import CS340.PetPal.Repository.ReviewRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepostiroy;
    private final ProviderRepository providerRepository;

    public ReviewService(ReviewRepository reviewRepository, CustomerRepository customerRepository,
            ProviderRepository providerRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepostiroy = customerRepository;
        this.providerRepository = providerRepository;
    }

    public Review createReview(ReviewCreateDto dto) {
        Optional<Customer> customer = this.customerRepostiroy.findById(dto.getCustomerId());
        Optional<Provider> provider = this.providerRepository.findById(dto.getProviderId());
        if (customer.isEmpty() && provider.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no customer with id " + dto.getCustomerId()
                    + " and no provider with id " + dto.getProviderId() + ".");
        }
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no customer with id " + dto.getCustomerId() + ".");
        }
        if (provider.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no provider with id " + dto.getProviderId() + ".");
        }
        Review review = new Review(dto.getRecommended(), dto.getCustomerComment(), null,
                LocalDateTime.now(), customer.get(), provider.get());
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no review with id " + reviewId + ".");
        }
        return review.get();
    }

    public Review editReviewComment(Long reviewId, ReviewEditCommentDto dto) {
        Review review = getReviewById(reviewId);
        review.setRecommended(dto.getRecommended());
        review.setCustomerComment(dto.getCustomerComment());
        return reviewRepository.save(review);
    }

    public Review respondReview(Long reviewId, ReviewRespondDto dto) {
        Review review = getReviewById(reviewId);
        if (review.getHasResponse()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This review already has a response.");
        }
        review.setProviderResponse(dto.getProviderResponse());
        return reviewRepository.save(review);
    }

    public Review editReviewResponse(Long reviewId, ReviewEditResponseDto dto) {
        Review review = getReviewById(reviewId);
        if (!review.getHasResponse()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "This review does not have a response yet.");
        }
        
        review.setProviderResponse(dto.getProviderResponse());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        Review review = getReviewById(reviewId);
        reviewRepository.delete(review);
    }
}
