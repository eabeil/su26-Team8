package CS340.PetPal.Service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import CS340.PetPal.Dto.CustomerSignupDto;
import CS340.PetPal.Entity.Customer;
import CS340.PetPal.Repository.CustomerRepository;

@Service
public class CustomerAuthService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationService validationService;

    public CustomerAuthService(CustomerRepository customerRepository,
            PasswordEncoder passwordEncoder,
            ValidationService validationService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
    }

    public Customer registerCustomer(CustomerSignupDto dto) {
        String name = clean(dto.getName());
        String email = clean(dto.getEmail()).toLowerCase();
        String phone = clean(dto.getPhone());
        String imageUrl = clean(dto.getImageUrl());

        validateSignup(dto, name, email, phone);

        Customer customer = new Customer(name, email, phone, imageUrl);
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        return customerRepository.save(customer);
    }

    public Optional<Customer> authenticateCustomer(String email, String rawPassword) {
        if (email == null || rawPassword == null) {
            return Optional.empty();
        }

        return customerRepository.findByEmailIgnoreCase(email.trim())
                .filter(customer -> customer.getPassword() != null)
                .filter(customer -> passwordEncoder.matches(rawPassword, customer.getPassword()));
    }

    private void validateSignup(CustomerSignupDto dto, String name, String email, String phone) {
        if (name.isEmpty() || !validationService.getIsValidString(name)) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (!validationService.getIsValidEmail(email)) {
            throw new IllegalArgumentException("Enter a valid email address.");
        }
        if (customerRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("An account already uses that email address.");
        }
        if (!validationService.getIsValidPhoneNumber(phone)) {
            throw new IllegalArgumentException("Use the phone format (123) 456-7890.");
        }
        if (dto.getPassword() == null
                || !validationService.getIsValidPassword(dto.getPassword())) {
            throw new IllegalArgumentException("Password must be longer than 8 characters.");
        }
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
