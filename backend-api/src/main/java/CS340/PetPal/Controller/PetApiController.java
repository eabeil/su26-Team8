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

import CS340.PetPal.Service.PetService;
import CS340.PetPal.Entity.Pet;
import CS340.PetPal.Dto.CreatePetDto;
import CS340.PetPal.Dto.UpdatePetDto;

@RestController
@RequestMapping("/api/pets")
public class PetApiController {
    private final PetService petService;

    public PetApiController(PetService petService) {
        this.petService = petService;
    }

    // get pets
    @GetMapping("/")
    public ResponseEntity<List<Pet>> getAllPets() {
        try {
            List<Pet> pets = this.petService.getAllPets();
            return ResponseEntity.ok(pets);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // get pet
    @GetMapping("/{id}/")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") Long petId) {
        try {
            Pet pet = this.petService.getPetById(petId);
            return ResponseEntity.ok(pet);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // create pet
    @PostMapping("/")
    public ResponseEntity<Pet> createPet(@RequestBody CreatePetDto dto) {
        try {
            Pet pet = this.petService.createPet(dto);
            URI location = URI.create("/api/customers/" + pet.getId());
            return ResponseEntity.created(location).body(pet);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // update pet
    @PutMapping("/{id}/")
    public ResponseEntity<Pet> updateCustomer(@PathVariable("id") Long petId,
            @RequestBody UpdatePetDto dto) {
        try {
            Pet pet = this.petService.updatePet(petId, dto);
            return ResponseEntity.ok(pet);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    // delete pet
    @DeleteMapping("/{id}/")
    public ResponseEntity<Void> deletePet(@PathVariable("id") Long petId) {
        try {
            this.petService.deletePet(petId);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
}