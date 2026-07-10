package CS340.PetPal.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Service.CustomerService;
import CS340.PetPal.Entity.Customer;
import CS340.PetPal.Entity.Pet;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Dto.CreateCustomerDto;
import CS340.PetPal.Dto.UpdateCustomerDto;

@RestController
@RequestMapping("/api/customers")
public class CustomerApiController {

    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // get customers
    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> customers = this.customerService.getAllCustomers();
            return ResponseEntity.ok(customers);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // get customer
    @GetMapping("/{id}/")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long customerId) {
        try {
            return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // get customer pets
    @GetMapping("/{id}/pets/")
    public ResponseEntity<List<Pet>> getCustomerPets(@PathVariable("id") Long customerId) {
        try {
            List<Pet> pets = this.customerService.getCustomerPets(customerId);
            return ResponseEntity.ok(pets);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // get customer reviews
    @GetMapping("/{id}/reviews/")
    public ResponseEntity<List<Review>> getCustomerReviews(@PathVariable("id") Long customerId) {
        try {
            List<Review> reviews = this.customerService.getCustomerReviews(customerId);
            return ResponseEntity.ok(reviews);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // create customer
    @PostMapping("/")
    public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerDto dto) {
        try {
            Customer customer = this.customerService.createCustomer(dto);
            URI location = URI.create("/api/customers/" + customer.getId());
            return ResponseEntity.created(location).body(customer);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // update customer
    @PutMapping("/{id}/")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long customerId,
            @RequestBody UpdateCustomerDto dto) {
        try {
            Customer customer = this.customerService.updateCustomer(customerId, dto);
            return ResponseEntity.ok(customer);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // delete customer
    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long customerId) {
        try {
            this.customerService.deleteCustomer(customerId);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
}