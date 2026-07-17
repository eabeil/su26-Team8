package CS340.PetPal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CS340.PetPal.Entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    
    boolean existsByEmail(String email);
}
