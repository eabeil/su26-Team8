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

import CS340.PetPal.Dto.PetCreateDto;
import CS340.PetPal.Dto.PetUpdateDto;
import CS340.PetPal.Entity.Pet;
import CS340.PetPal.Service.PetService;

@RestController
@RequestMapping("/api/pets")
public class PetApiController {
    private final PetService petService;

    public PetApiController(PetService petService) {
        this.petService = petService;
    }

    // get pets
    @GetMapping({ "/", "" })
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = this.petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    // get pet
    @GetMapping({ "/{id}/", "/{id}" })
    public ResponseEntity<Pet> getPetById(@PathVariable("id") Long petId) {
        Pet pet = this.petService.getPetById(petId);
        return ResponseEntity.ok(pet);
    }

    // create pet
    @PostMapping({ "/", "" })
    public ResponseEntity<Pet> createPet(@RequestBody PetCreateDto dto) {
        Pet pet = this.petService.createPet(dto);
        URI location = URI.create("/api/pets/" + pet.getId());
        return ResponseEntity.created(location).body(pet);
    }

    // update pet
    @PutMapping({ "/{id}/", "/{id}" })
    public ResponseEntity<Pet> updateCustomer(@PathVariable("id") Long petId,
            @RequestBody PetUpdateDto dto) {
        Pet pet = this.petService.updatePet(petId, dto);
        return ResponseEntity.ok(pet);
    }

    // delete pet
    @DeleteMapping({ "/{id}/", "/{id}" })
    public ResponseEntity<Void> deletePet(@PathVariable("id") Long petId) {
        this.petService.deletePet(petId);
        return ResponseEntity.noContent().build();
    }
}