package CS340.PetPal.Controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import CS340.PetPal.Dto.CustomerCreateDto;
import CS340.PetPal.Dto.CustomerLoginDto;
import CS340.PetPal.Dto.CustomerResponseDto;
import CS340.PetPal.Service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class CustomerAuthController {
    private final CustomerService customerService;
    private final CustomerSessionService customerSessionService;

    public CustomerAuthController(CustomerService customerService, CustomerSessionService customerSessionService) {
        this.customerService = customerService;
        this.customerSessionService = customerSessionService;
    }

    @PostMapping("/signup")
    public ResponseEntity<CustomerResponseDto> signup(@RequestBody CustomerCreateDto dto,
            HttpServletRequest request) {
        CustomerResponseDto customer = customerService.createCustomer(dto);
        customerSessionService.login(request, customer.id());
        return ResponseEntity.created(URI.create("/api/customers/" + customer.id())).body(customer);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerResponseDto> login(@RequestBody CustomerLoginDto dto,
            HttpServletRequest request) {
        CustomerResponseDto customer = customerService.authenticate(dto);
        customerSessionService.login(request, customer.id());
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        customerSessionService.logout(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<CustomerResponseDto> currentCustomer(HttpServletRequest request) {
        Long customerId = customerSessionService.getCustomerId(request);
        if (customerId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Log in to continue.");
        }
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }
}
