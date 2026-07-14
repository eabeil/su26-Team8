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

import CS340.PetPal.Dto.CustomerCreateDto;
import CS340.PetPal.Dto.CustomerResponseDto;
import CS340.PetPal.Dto.CustomerUpdateDto;
import CS340.PetPal.Entity.Customer;
import CS340.PetPal.Entity.Pet;
import CS340.PetPal.Entity.Review;
import CS340.PetPal.Service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerApiController {

    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // get customers
    @GetMapping({ "/", "" })
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        List<CustomerResponseDto> customers = this.customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // get customer
    @GetMapping({ "/{id}/", "/{id}" })
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long customerId) {
        return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
    }

    // get customer pets
    @GetMapping({ "/{id}/pets/", "/{id}/pets" })
    public ResponseEntity<List<Pet>> getCustomerPets(@PathVariable("id") Long customerId) {
        List<Pet> pets = this.customerService.getCustomerPets(customerId);
        return ResponseEntity.ok(pets);
    }

    // get customer reviews
    @GetMapping({ "/{id}/reviews/", "/{id}/reviews" })
    public ResponseEntity<List<Review>> getCustomerReviews(@PathVariable("id") Long customerId) {
        List<Review> reviews = this.customerService.getCustomerReviews(customerId);
        return ResponseEntity.ok(reviews);
    }

    // create customer
    @PostMapping({ "/", "" })
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerCreateDto dto) {
        CustomerResponseDto customer = this.customerService.createCustomer(dto);
        URI location = URI.create("/api/customers/" + customer.id());
        return ResponseEntity.created(location).body(customer);
    }

    // update customer
    @PutMapping({ "/{id}/", "/{id}" })
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable("id") Long customerId,
            @RequestBody CustomerUpdateDto dto) {
        CustomerResponseDto customer = this.customerService.updateCustomer(customerId, dto);
        return ResponseEntity.ok(customer);
    }

    // delete customer
    @DeleteMapping({ "/{id}/", "/{id}" })
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long customerId) {
        this.customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}