package CS340.PetPal.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Dto.CreateCustomerDto;
import CS340.PetPal.Dto.UpdateCustomerDto;
import CS340.PetPal.Entity.Customer;
import CS340.PetPal.Entity.Pet;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Repository.CustomerRepository;
import CS340.PetPal.Repository.PetRepository;
import CS340.PetPal.Repository.ReviewRepository;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final ReviewRepository reviewRepository;
  private final PetRepository petRepository;

  public CustomerService(CustomerRepository customerRepository, ReviewRepository reviewRepository,
      PetRepository petRepository) {
    this.customerRepository = customerRepository;
    this.reviewRepository = reviewRepository;
    this.petRepository = petRepository;
  }

  public Customer createCustomer(CreateCustomerDto dto) {
    Customer customer = new Customer(dto.getName(), dto.getEmail(), dto.getPhone(), dto.getPassword(),
        Collections.emptyList(), Collections.emptyList());
    return this.customerRepository.save(customer);
  }

  public List<Customer> getAllCustomers() {
    return this.customerRepository.findAll();
  }

  public Customer getCustomerById(Long customerId) {
    Optional<Customer> customerO = this.customerRepository.findById(customerId);
    if (customerO.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no customer with id " + customerId + ".");
    }
    Customer customer = customerO.get();
    return customer;
  }

  public List<Review> getCustomerReviews(Long customerId) {
    return this.reviewRepository.findByCustomerId(customerId);
  }

  public List<Pet> getCustomerPets(Long customerId) {
    return this.petRepository.findByCustomerId(customerId);
  }

  public Customer updateCustomer(Long customerId, UpdateCustomerDto dto) {
    Optional<Customer> customerO = this.customerRepository.findById(customerId);
    if (customerO.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no customer with id " + customerId + ".");
    }
    Customer customer = customerO.get();
    customer.setName(dto.getName());
    customer.setEmail(dto.getEmail());
    customer.setPhone(dto.getPhone());
    customer.setPassword(dto.getPassword());
    return this.customerRepository.save(customer);
  }

  public void deleteCustomer(Long customerId) throws ResponseStatusException {
    Optional<Customer> customerO = this.customerRepository.findById(customerId);
    if (customerO.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no customer with id " + customerId + ".");
    }
    Customer customer = customerO.get();
    this.customerRepository.delete(customer);
  }
}