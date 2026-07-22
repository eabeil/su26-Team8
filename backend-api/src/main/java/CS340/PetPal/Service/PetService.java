package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CS340.PetPal.Dto.PetCreateDto;
import CS340.PetPal.Dto.PetUpdateDto;
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

    public Pet createPet(PetCreateDto dto) {
        validatePet(dto.getName(), dto.getSpeciesOrBreed(), dto.getAge(),
            dto.getSpecialCareInstructions(), dto.getTraits());

        Optional<Customer> customer = customerRepository.findById(dto.getCustomerId());
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no customer with id " + dto.getCustomerId() + ".");
        }
        Pet pet = new Pet(dto.getName(), dto.getSpeciesOrBreed(), dto.getAge(), dto.getImageUrl(), dto.getSpecialCareInstructions(),
                dto.getTraits(), customer.get());
        return this.petRepository.save(pet);
    }

    public List<Pet> getAllPets() {
        return this.petRepository.findAll();
    }

    public Pet getPetById(Long petId) {
        Optional<Pet> pet = this.petRepository.findById(petId);
        if (pet.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no pet with id " + petId + ".");
        }
        return pet.get();
    }

    public Pet updatePet(Long petId, PetUpdateDto dto) {
        Pet pet = getPetById(petId);
        return savePetUpdates(pet, dto);
    }

    public Pet updateCustomerPet(Long customerId, Long petId, PetUpdateDto dto) {
        Pet pet = getCustomerPet(customerId, petId);
        return savePetUpdates(pet, dto);
    }

    public Pet savePetUpdates(Pet pet, PetUpdateDto dto) {
        validatePet(dto.getName(), dto.getSpeciesOrBreed(), dto.getAge(),
            dto.getSpecialCareInstructions(), dto.getTraits());

        pet.setName(dto.getName());
        pet.setSpeciesOrBreed(dto.getSpeciesOrBreed());
        pet.setAge(dto.getAge());
        pet.setImageUrl(dto.getImageUrl());
        pet.setSpecialCareInstructions(dto.getSpecialCareInstructions());
        pet.setTraits(dto.getTraits());
        return petRepository.save(pet);
    }

    public Pet getCustomerPet(Long customerId, Long petId) {
        Pet pet = getPetById(petId);
        if (!pet.getCustomer().getId().equals(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No pet with id " + petId + " belongs to customer " + customerId + ".");
        }

        return pet;
    }


    public void deletePet(Long petId) {
        Pet pet = getPetById(petId);
        this.petRepository.delete(pet);
    }

    public void deleteCustomerPet(Long customerId, Long petId) {
        Pet pet = getCustomerPet(customerId, petId);
        petRepository.delete(pet);
    }

    private void validatePet(String name, String speciesOrBreed, Integer age,
            String specialCareInstructions, String traits) {
        if (isBlank(name) || isBlank(speciesOrBreed) || isBlank(specialCareInstructions) || isBlank(traits)) {
            throw new IllegalArgumentException("Complete all required pet fields.");
        }
        if (age != null && (age < 0 || age > 100)) {
            throw new IllegalArgumentException("Pet age must be between 0 and 100.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
