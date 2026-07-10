package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Dto.CreatePetDto;
import CS340.PetPal.Dto.UpdatePetDto;
import CS340.PetPal.Entity.Customer;
import CS340.PetPal.Entity.Pet;
import CS340.PetPal.Repository.CustomerRepository;
import CS340.PetPal.Repository.PetRepository;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Pet createPet(CreatePetDto dto) {
        Optional<Customer> customerO = this.customerRepository.findById(dto.getCustomerId());
        if (customerO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerO.get();
        Pet pet = new Pet(dto.getName(), dto.getSpeciesOrBreed(), dto.getAge(), dto.getSpecialCareInstructions(), dto.getTraits(), customer);
        return this.petRepository.save(pet);
    }

    public List<Pet> getAllPets() {
        return this.petRepository.findAll();
    }

    public Pet getPetById(Long petId) {
        Optional<Pet> petO = this.petRepository.findById(petId);
        if (petO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Pet pet = petO.get();
        return pet;
    }

    public Pet updatePet(Long petId, UpdatePetDto dto) {
        Optional<Pet> petO = this.petRepository.findById(petId);
        if (petO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Pet pet = petO.get();
        pet.setName(dto.getName());
        pet.setSpeciesOrBreed(dto.getSpeciesOrBreed());
        pet.setAge(dto.getAge());
        pet.setSpecialCareInstructions(dto.getSpecialCareInstructions());
        pet.setTraits(dto.getTraits());
        return this.petRepository.save(pet);
    }

    public void deletePet(Long petId) {
        Optional<Pet> petO = this.petRepository.findById(petId);
        if (petO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Pet pet = petO.get();
        this.petRepository.delete(pet);
    }
}
