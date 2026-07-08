package CS340.PetPal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CS340.PetPal.Entity.Update;
@Repository
public interface UpdateRepository extends JpaRepository<Update, Long> {
    List<CS340.PetPal.Entity.Service> findByProviderId(Long providerId);
}
