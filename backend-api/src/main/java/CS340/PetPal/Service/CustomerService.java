package CS340.PetPal.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Dto.CustomerCreateDto;
import CS340.PetPal.Dto.CustomerLoginDto;
import CS340.PetPal.Dto.CustomerResponseDto;
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
  private final PasswordHasher passwordHasher;


  public CustomerService(CustomerRepository customerRepository, ReviewRepository reviewRepository,
      PetRepository petRepository, PasswordHasher passwordHasher) {
    this.customerRepository = customerRepository;
    this.reviewRepository = reviewRepository;
    this.petRepository = petRepository;
    this.passwordHasher = passwordHasher;
  }

  public CustomerResponseDto createCustomer(CustomerCreateDto dto) {
    validateRegistration(dto);
    String email = normalizeEmail(dto.getEmail());

    if (customerRepository.findByEmail(email).isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "An account already exists for that email.");
    }

    Customer customer = new Customer();
      customer.setName(dto.getName().trim());
      customer.setEmail(email);
      customer.setPhone(normalizePhone(dto.getPhone()));
      customer.setPassword(passwordHasher.hash(dto.getPassword()));
    return toResponse(customerRepository.save(customer));
  }
  public CustomerResponseDto authenticate(CustomerLoginDto dto) {
    if (dto == null || isBlank(dto.getEmail()) || isBlank(dto.getPassword())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email and password are required.");
    }
    Customer customer = customerRepository.findByEmail(normalizeEmail(dto.getEmail()))
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password."));

    if (!passwordHasher.matches(dto.getPassword(), customer.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password.");
    }

    if (passwordHasher.needsUpgrade(customer.getPassword())) {
      customer.setPassword(passwordHasher.hash(dto.getPassword()));
      customerRepository.save(customer);
    }

    return toResponse(customer);
  }


  public List<CustomerResponseDto> getAllCustomers() {
    return this.customerRepository.findAll().stream().map(this::toResponse).toList();
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

  public CustomerResponseDto updateCustomer(Long customerId, CustomerUpdateDto dto) {
    if (dto == null || isBlank(dto.getName()) || isBlank(dto.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name and email are required.");
    }

    Customer customer = findCustomer(customerId);
    String email = normalizeEmail(dto.getEmail());
    customerRepository.findByEmail(email)
        .filter(existing -> !existing.getId().equals(customerId))
        .ifPresent(existing -> {
          throw new ResponseStatusException(HttpStatus.CONFLICT, "An account already exists for that email.");
        });

    customer.setName(dto.getName().trim());
    customer.setEmail(email);
    customer.setPhone(normalizePhone(dto.getPhone()));
    if (!isBlank(dto.getPassword())) {
      validatePassword(dto.getPassword());
      customer.setPassword(passwordHasher.hash(dto.getPassword()));
    }
    return toResponse(customerRepository.save(customer));
  }

  public void deleteCustomer(Long customerId) {
    customerRepository.delete(findCustomer(customerId));
  }
  private Customer findCustomer(Long customerId) {
    return customerRepository.findById(customerId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "No customer with id " + customerId + "."));
  }

  private void ensureCustomerExists(Long customerId) {
    if (!customerRepository.existsById(customerId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No customer with id " + customerId + ".");
    }
  }

  private void validateRegistration(CustomerCreateDto dto) {
    if (dto == null || isBlank(dto.getName()) || isBlank(dto.getEmail()) || isBlank(dto.getPassword())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name, email, and password are required.");
    }
    if (!normalizeEmail(dto.getEmail()).matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enter a valid email address.");
    }
    validatePassword(dto.getPassword());
  }

  private void validatePassword(String password) {
    if (password.length() < 8) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be at least 8 characters.");
    }
  }

  private String normalizeEmail(String email) {
    return email.trim().toLowerCase(java.util.Locale.ROOT);
  }

  private String normalizePhone(String phone) {
    return phone == null ? "" : phone.trim();
  }

  private boolean isBlank(String value) {
    return value == null || value.isBlank();
  }

  private CustomerResponseDto toResponse(Customer customer) {
    return new CustomerResponseDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone());
  }
}