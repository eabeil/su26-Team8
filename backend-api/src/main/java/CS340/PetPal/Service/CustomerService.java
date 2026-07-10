package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import CS340.PetPal.DTO.CustomerDto;
import CS340.PetPal.Entity.Customer;
import CS340.PetPal.Repository.CustomerRepository;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

    public CustomerDto createCustomer(Customer customer) {
      Customer savedCustomer = customerRepository.save(customer);
      return convertToDTO(savedCustomer);
    }

    public List<CustomerDto> getAllCustomers() {
      return customerRepository.findAll()
        .stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    }


  public Optional<CustomerDto> getCustomerById(Long id) {
    return customerRepository.findById(id).map(this::convertToDTO);
  }

  public CustomerDto updateCustomer(Long id, Customer updatedCustomer) {
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
          if (updatedCustomer.getPassword() != null) {
            customer.setPassword(updatedCustomer.getPassword());
          }

          Customer savedCustomer = customerRepository.save(customer);
          return convertToDTO(savedCustomer);
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

    private CustomerDto convertToDTO(Customer customer){
      CustomerDto dto = new CustomerDto();
      dto.setId(customer.getId());
      dto.setFullName(customer.getFullName());
      dto.setEmail(customer.getEmail());
      dto.setPhoneNumber(customer.getPhoneNumber());
      return dto;

    }
    

}