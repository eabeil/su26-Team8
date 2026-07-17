package CS340.PetPal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CS340.PetPal.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}