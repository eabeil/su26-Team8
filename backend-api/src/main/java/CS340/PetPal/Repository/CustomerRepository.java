package CS340.PetPal.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CS340.PetPal.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContainingIgnoreCase(String name);
    
    Optional<Customer> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);
}