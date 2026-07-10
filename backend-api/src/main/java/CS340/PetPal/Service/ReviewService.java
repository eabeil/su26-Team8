package CS340.PetPal.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Dto.CreateReviewDto;
import CS340.PetPal.Dto.RespondReviewDto;
import CS340.PetPal.Dto.EditCommentReviewDto;
import CS340.PetPal.Dto.EditResponseReviewDto;
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

    public Review createReview(CreateReviewDto dto) {
        Optional<Customer> customerO = this.customerRepostiroy.findById(dto.getCustomerId());
        if (customerO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerO.get();
        Optional<Provider> providerO = this.providerRepository.findById(dto.getProviderId());
        if (providerO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Provider provider = providerO.get();
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Review review = reviewO.get();
        return review;
    }

    public Review editReviewComment(Long reviewId, EditCommentReviewDto dto) {
        Optional<Review> reviewO = this.reviewRepository.findById(reviewId);
        if (reviewO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Review review = reviewO.get();
        review.setCommentEditedAt(LocalDateTime.now());
        review.setRecommended(dto.getRecommended());
        review.setCustomerComment(dto.getCustomerComment());
        return this.reviewRepository.save(review);
    }

    public Review respondReview(Long reviewId, RespondReviewDto dto) {
        Optional<Review> reviewO = this.reviewRepository.findById(reviewId);
        if (reviewO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Review review = reviewO.get();
        if (review.getHasResponse()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        review.setProviderResponse(dto.getProviderResponse());
        review.setRespondedAt(LocalDateTime.now());
        return this.reviewRepository.save(review);
    }

    public Review editReviewResponse(Long reviewId, EditResponseReviewDto dto) {
        Optional<Review> reviewO = this.reviewRepository.findById(reviewId);
        if (reviewO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Review review = reviewO.get();
        if (!review.getHasResponse()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        review.setProviderResponse(dto.getProviderResponse());
        review.setResponseEditedAt(LocalDateTime.now());
        return this.reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        Optional<Review> reviewO = this.reviewRepository.findById(reviewId);
        if (reviewO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Review review = reviewO.get();
        this.reviewRepository.delete(review);
    }
}
