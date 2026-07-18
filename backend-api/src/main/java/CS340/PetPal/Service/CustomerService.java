package CS340.PetPal.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Dto.CustomerCreateDto;
import CS340.PetPal.Dto.CustomerUpdateDto;
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


  public List<Customer> getAllCustomers() {
    return this.customerRepository.findAll();
  }


  public Customer createCustomer(CustomerCreateDto dto){
    Customer customer = new Customer(dto.getName(), dto.getImageUrl(), dto.getLocation(), dto.getEmail(), dto.getPhone());
    return this.customerRepository.save(customer);
  }


  public Customer getCustomerById(Long customerId) {
    Optional<Customer> customer = customerRepository.findById(customerId);
    if (customer.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no customer with id " + customerId + ".");
    }
    return customer.get();
  }

  public List<Review> getCustomerReviews(Long customerId) {
    return this.reviewRepository.findByCustomerId(customerId);
  }

  public List<Pet> getCustomerPets(Long customerId) {
    return this.petRepository.findByCustomerId(customerId);
  }

  public Customer updateCustomer(long customerId, CustomerUpdateDto dto) {
    Customer customer = getCustomerById(customerId);
    customer.setName(dto.getName());
    customer.setImageUrl(dto.getImageUrl());
    customer.setLocation(dto.getLocation());
    customer.setEmail(dto.getEmail());
    customer.setPhone(dto.getPhone());
    return this.customerRepository.save(customer);
  }


  public void deleteCustomer(Long customerId) {
    Customer customer = getCustomerById(customerId);
    this.customerRepository.delete(customer);
  }

}