package CS340.PetPal.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Dto.ReviewCreateDto;
import CS340.PetPal.Dto.ReviewRespondDto;
import CS340.PetPal.Dto.ReviewEditCommentDto;
import CS340.PetPal.Dto.ReviewEditResponseDto;
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
        Optional<Customer> customerO = this.customerRepostiroy.findById(dto.getCustomerId());
        Optional<Provider> providerO = this.providerRepository.findById(dto.getProviderId());
        if (customerO.isEmpty() && providerO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no customer with id " + dto.getCustomerId()
                    + " and no provider with id " + dto.getProviderId() + ".");
        }
        if (customerO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no customer with id " + dto.getCustomerId() + ".");
        }
        if (providerO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no provider with id " + dto.getProviderId() + ".");
        }
        Provider provider = providerO.get();
        Customer customer = customerO.get();
        Review review = new Review(
                dto.getRecommended(),
                dto.getCustomerComment(),
                null,
                LocalDateTime.now(),
                null,
                null,
                null,
                customer,
                provider);
        return this.reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return this.reviewRepository.findAll();
    }

    public Review getReviewById(Long reviewId) {
        Optional<Review> reviewO = this.reviewRepository.findById(reviewId);
        if (reviewO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no review with id " + reviewId + ".");
        }
        Review review = reviewO.get();
        return review;
    }

    public Review editReviewComment(Long reviewId, ReviewEditCommentDto dto) {
        Optional<Review> reviewO = this.reviewRepository.findById(reviewId);
        if (reviewO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no review with id " + reviewId + ".");
        }
        Review review = reviewO.get();
        review.setCommentEditedAt(LocalDateTime.now());
        review.setRecommended(dto.getRecommended());
        review.setCustomerComment(dto.getCustomerComment());
        return this.reviewRepository.save(review);
    }

    public Review respondReview(Long reviewId, ReviewRespondDto dto) {
        Optional<Review> reviewO = this.reviewRepository.findById(reviewId);
        if (reviewO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no review with id " + reviewId + ".");
        }
        Review review = reviewO.get();
        if (review.getHasResponse()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "review has no response.");
        }
        review.setProviderResponse(dto.getProviderResponse());
        review.setRespondedAt(LocalDateTime.now());
        return this.reviewRepository.save(review);
    }

    public Review editReviewResponse(Long reviewId, ReviewEditResponseDto dto) {
        Optional<Review> reviewO = this.reviewRepository.findById(reviewId);
        if (reviewO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no review with id " + reviewId + ".");
        }
        Review review = reviewO.get();
        if (!review.getHasResponse()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "review has no response.");
        }
        review.setProviderResponse(dto.getProviderResponse());
        review.setResponseEditedAt(LocalDateTime.now());
        return this.reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        Optional<Review> reviewO = this.reviewRepository.findById(reviewId);
        if (reviewO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no review with id " + reviewId + ".");
        }
        Review review = reviewO.get();
        this.reviewRepository.delete(review);
    }
}
