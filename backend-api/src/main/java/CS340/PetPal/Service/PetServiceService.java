package CS340.PetPal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import CS340.PetPal.Repository.ServiceRepository;
import CS340.PetPal.Entity.PetService;

@Service
public class PetServiceService {
    private final ServiceRepository serviceRepository;

    public PetServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<PetService> getAllServices() {
        return this.serviceRepository.findAll();
    }

    public Optional<PetService> getServiceById(Long serviceId) {
        return this.serviceRepository.findById(serviceId);
    }

    public PetService createService(PetService provider) {
        return this.serviceRepository.save(provider);
    }

    public PetService updateService(Long serviceId, PetService service) {
        PetService existingService = this.serviceRepository.findById(serviceId).orElse(null);
        if (existingService == null) {
            throw new RuntimeException("SErvice not found with id: " + serviceId);
        }
        if (!service.getName().isEmpty()) {
            existingService.setName(service.getName());
        }
        if (service.getTime() != null) {
            existingService.setTime(service.getTime());
        }
        if (!service.getDuration().isEmpty()) {
            existingService.setDuration(service.getDuration());
        }
        if (service.getPrice() != 0) {
            existingService.setPrice(service.getPrice());
        }
        return this.serviceRepository.save(existingService);
    }
}
