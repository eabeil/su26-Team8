package CS340.PetPal.Services;


import java.util.List;

import org.springframework.stereotype.Service;

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

    public Pet addPetToCustomer(Long customerId, Pet pet) {
        // First, look up the customer to ensure they exist
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
        
        // Attach the customer to the pet, then save the pet
        pet.setCustomer(customer);
        return petRepository.save(pet);
    }

    public List<Pet> getPetsByCustomerId(Long customerId) {
        return petRepository.findByCustomerId(customerId);
    }
    
    public void deletePet(Long petId) {
        petRepository.deleteById(petId);
    }
}