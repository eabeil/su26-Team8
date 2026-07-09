package CS340.PetPal.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CS340.PetPal.Entity.Pet;
import CS340.PetPal.Service.PetService;

@RestController
@RequestMapping("/api/customers/{customerId}/pets")
public class PetApiController {

    private final PetService petService;

    public PetApiController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<Pet> addPet(@PathVariable Long customerId, @RequestBody Pet pet) {
        try {
            return ResponseEntity.ok(petService.addPetToCustomer(customerId, pet));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getCustomerPets(@PathVariable Long customerId) {
        return ResponseEntity.ok(petService.getPetsByCustomerId(customerId));
    }
}