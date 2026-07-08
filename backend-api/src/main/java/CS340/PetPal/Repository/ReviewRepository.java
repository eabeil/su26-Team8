package CS340.PetPal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CS340.PetPal.Entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    
    List<Review> findByCustomerId(Long customerId);

    List<Review> findByProviderId(Long providerId);
}