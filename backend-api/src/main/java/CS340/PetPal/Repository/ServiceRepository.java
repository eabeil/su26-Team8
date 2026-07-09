package CS340.PetPal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CS340.PetPal.Entity.PetService;

@Repository
public interface ServiceRepository extends JpaRepository<PetService, Long> {
    List<PetService> findByProviderId(Long providerId);
}
