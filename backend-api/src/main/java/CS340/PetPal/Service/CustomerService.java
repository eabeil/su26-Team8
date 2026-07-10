package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import CS340.PetPal.Entity.Customer;
import CS340.PetPal.Repository.CustomerRepository;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer createCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Optional<Customer> getCustomerById(Long id) {
    return customerRepository.findById(id);
  }

  public Customer updateCustomer(Long id, Customer updatedCustomer) {
    Optional<Customer> existingCustomer = customerRepository.findById(id);
    if (existingCustomer.isPresent()) {
      Customer customer = existingCustomer.get();
      customer.setEmail(updatedCustomer.getEmail());
      customer.setPassword(updatedCustomer.getPassword());
      return customerRepository.save(customer);
    } else {
      throw new RuntimeException("Customer not found with id: " + id);
    }
  }

  public Customer updatePersonalInfo(Long id, Customer updatedCustomer) {
    Optional<Customer> existingCustomer = customerRepository.findById(id);
    if (existingCustomer.isPresent()) {
      Customer customer = existingCustomer.get();
      if (updatedCustomer.getFullName() != null) {
        customer.setFullName(updatedCustomer.getFullName());
      }
      if (updatedCustomer.getPhoneNumber() != null) {
        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
      }
      if (updatedCustomer.getEmail() != null) {
        customer.setEmail(updatedCustomer.getEmail());
      }
      return customerRepository.save(customer);
    } else {
      throw new RuntimeException("Customer not found with id: " + id);
    }
  }

  public void deleteCustomer(Long id) {
    // Check if they exist first so we can throw a clean error if they don't
    if (!customerRepository.existsById(id)) {
      throw new RuntimeException("Customer not found with ID: " + id);
    }
    customerRepository.deleteById(id);
  }
}