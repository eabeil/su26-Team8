package CS340.PetPal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<CS340.PetPal.Entity.Service, Long> {
    List<CS340.PetPal.Entity.Service> findByProviderId(Long providerId);
}
