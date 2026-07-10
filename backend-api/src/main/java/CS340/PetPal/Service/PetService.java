package CS340.PetPal.Service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import CS340.PetPal.Dto.PetDto;
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

    public PetDto addPetToCustomer(Long customerId, Pet pet) {
        // First, look up the customer to ensure they exist
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
        
        // Attach the customer to the pet, then save the pet
        pet.setCustomer(customer);
        Pet savedPet = petRepository.save(pet);
        return convertToDTO(savedPet);
    }

    public List<PetDto> getPetsByCustomerId(Long customerId) {
        return petRepository.findByCustomerId(customerId)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public void deletePet(Long petId) {
        petRepository.deleteById(petId);
    }
    
    private PetDto convertToDTO(Pet pet) {
        PetDto dto = new PetDto();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setSpeciesOrBreed(pet.getSpeciesOrBreed());
        dto.setAge(pet.getAge());
        dto.setSpecialCareInstructions(pet.getSpecialCareInstructions());
        dto.setTraits(pet.getTraits());
        dto.setCustomerId(pet.getCustomer().getId());
        return dto;
    }
}
